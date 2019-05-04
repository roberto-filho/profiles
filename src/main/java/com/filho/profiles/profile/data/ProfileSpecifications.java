package com.filho.profiles.profile.data;

import com.filho.profiles.profile.Profile;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import java.math.BigDecimal;

/**
 * Specifications to use for composing queries to the Profile entity.
 */
public class ProfileSpecifications {

    /**
     * Builds a expression that checks if a profile has a photo on his profile or not.
     * @param hasPhoto if the expression should filter records that have profile pictures or not.
     * @return the specification.
     */
    public static Specification<Profile> hasPhoto(boolean hasPhoto) {
        return hasProperty("mainPhoto", hasPhoto);
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

    private static Specification<Profile> hasProperty(String property, boolean shouldHave) {
        return (root, query, criteriaBuilder) ->
                shouldHave
                ? root.get(property).isNotNull()
                : root.get(property).isNull();
    }

    /**
     *
     * @param kmRange
     * @param baseLatitude
     * @param baseLongitude
     * @return
     */
    public static Specification<Profile> isInKmRange(int kmRange, BigDecimal baseLatitude, BigDecimal baseLongitude) {
        return (Specification<Profile>) (root, query, criteriaBuilder) -> {

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

}
