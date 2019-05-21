package com.filho.profiles.profile.data;

import com.filho.profiles.profile.Profile;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Specifications to use for composing queries to the Profile entity.
 */
public class ProfileSpecifications {

    /**
     * Builds an expression that filters profiles that have "height" withing a given range.
     * @param minimum the lower bound.
     * @param maximum the upper bound.
     * @return
     */
    public static Specification<Profile> isHeightInRange(int minimum, int maximum) {
        return isPropertyInRange("heightInCm", minimum, maximum);
    }

    /**
     * Builds an expression that filters profiles that have "age" withing a given range.
     * @param minimum the lower bound.
     * @param maximum the upper bound.
     * @return
     */
    public static Specification<Profile> isAgeInRange(int minimum, int maximum) {
        return isPropertyInRange("age", minimum, maximum);
    }

    /**
     * Builds an expression that filters profiles whose compatibility score is contained in an inclusive range.
     * Note: the range is from 0 to 100.
     * @param minimum the lower bound.
     * @param maximum the upper bound.
     * @return the expression.
     */
    public static Specification<Profile> isCompatilityInRange(int minimum, int maximum) {
        // Calculate the real value
        final BigDecimal decimalMinimum = toDecimal(minimum);
        final BigDecimal decimalMaximum = toDecimal(maximum);

        return isPropertyInRange("compatibilityScore", decimalMinimum.doubleValue(), decimalMaximum.doubleValue());
    }

    private static BigDecimal toDecimal(int minimum) {
        return BigDecimal.valueOf(minimum)
                .divide(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.DOWN);
    }

    private static Specification<Profile> isPropertyInRange(String property, double minimum, double maximum) {
        return (root, query, criteriaBuilder) -> {
            final Path<Number> field = root.get(property);

            return criteriaBuilder.and(
                    field.isNotNull(),
                    criteriaBuilder.ge(field, minimum),
                    criteriaBuilder.le(field, maximum)
            );
        };
    }

    /**
     * Builds an expression that will filter profiles based on their 'favorite' property.
     * @param filterFavorites should filter favorites or not favorites
     * @return the expression.
     */
    public static Specification<Profile> isFavorite(boolean filterFavorites) {
        return (root, query, criteriaBuilder) -> {
            final Path<Boolean> favorite = root.get("favourite");

            if (filterFavorites) {
                return criteriaBuilder.and(
                        favorite.isNotNull(),
                        criteriaBuilder.isTrue(favorite)
                );
            }

            return criteriaBuilder.or(
                    // you never know when your database will have null booleans: even when it shouldn't
                    favorite.isNull(),
                    criteriaBuilder.isFalse(favorite)
            );
        };
    }

    /**
     * Builds a expression that checks if a profile has a photo on his profile or not.
     * @param hasPhoto if the expression should filter records that have profile pictures or not.
     * @return the specification.
     */
    public static Specification<Profile> hasPhoto(boolean hasPhoto) {
        return (root, query, criteriaBuilder) ->
                hasPhoto
                        ? root.get("mainPhoto").isNotNull()
                        : root.get("mainPhoto").isNull();
    }

    /**
     * Builds an expression that filters profiles with contacts or the opposite.
     * @param shouldHaveContacts if this expression should filter profiles with contacts or otherwise.
     * @return the specification.
     */
    public static Specification<Profile> hasContacts(boolean shouldHaveContacts) {
        return (root, query, criteriaBuilder) -> {
            final Path<Number> contactsExchanged = root.get("contactsExchanged");

            if (shouldHaveContacts) {
                return criteriaBuilder.and(
                        contactsExchanged.isNotNull(),
                        criteriaBuilder.gt(contactsExchanged, 0)
                );
            }

            return criteriaBuilder.or(
                    contactsExchanged.isNull(),
                    criteriaBuilder.equal(contactsExchanged, 0)
            );
        };
    }

    /**
     * Builds an expression that filters profiles based on their distance from a central point by calling
     * a custom database function.
     * The distance from the central point is calculated based on the profile's "city" property.
     * @param kmRange       the maximum distance from the central point.
     * @param baseLatitude  the central point's latitude.
     * @param baseLongitude the central point's longitude.
     * @return the expression.
     */
    public static Specification<Profile> isInKmRange(int kmRange, BigDecimal baseLatitude, BigDecimal baseLongitude) {
        return (root, query, criteriaBuilder) -> {

            final Expression<BigDecimal> distanceInKm = criteriaBuilder
                    .function("distance_in_km",
                            BigDecimal.class,
                            criteriaBuilder.literal(baseLatitude),
                            criteriaBuilder.literal(baseLongitude),
                            root.get("city").get("lat"),
                            root.get("city").get("lon"));

            // Lower bound
            if (kmRange < 30) {
                criteriaBuilder.lt(distanceInKm, 30);
            }

            // Upper bound
            if (kmRange > 300) {
                criteriaBuilder.gt(distanceInKm, 300);
            }

            return criteriaBuilder.le(distanceInKm, kmRange);
        };
    }

    /**
     * Builds an expression that filters rows by the 'religion' column.
     * @param religion the religion to match against.
     * @return the expression.
     */
    public static Specification<Profile> filterByReligion(String religion) {
        return (root, query, criteriaBuilder) -> {

            Expression<String> upperCaseReligion = criteriaBuilder.upper(root.get("religion"));
            String upperCaseParameter = religion.toUpperCase();

            return criteriaBuilder.equal(upperCaseReligion, upperCaseParameter);
        };
    }

}
