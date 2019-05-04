package com.filho.profiles.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.util.Pair;

@Data
@Builder
public class ProfileFilter {
    private Boolean hasPhoto;
    private Boolean hasContact;
    private Boolean isFavorite;
    private Pair<Integer, Integer> compatibilityScoreRange;
    private Pair<Integer, Integer> heightRange;
    private Integer distanceRadiusInKm;

    /**
     *
     * @param hasPhoto
     * @param hasContact
     * @param isFavorite
     * @param compatibilityScoreRange
     * @param heightRange
     * @param distance
     * @return
     */
    public static ProfileFilter fromRequestParams(
            Boolean hasPhoto,
            Boolean hasContact,
            Boolean isFavorite,
            String compatibilityScoreRange,
            String heightRange,
            String distance) {

        final ProfileFilter.ProfileFilterBuilder builder = builder()
                .hasPhoto(hasPhoto)
                .hasContact(hasContact)
                .isFavorite(isFavorite);

        if (distance != null) {
            builder.distanceRadiusInKm(Integer.parseInt(distance));
        }
        if (compatibilityScoreRange != null) {
            builder.compatibilityScoreRange(parseRange(compatibilityScoreRange));
        }
        if (heightRange != null) {
            builder.heightRange(parseRange(heightRange));
        }

        return builder.build();
    }

    private static Pair<Integer, Integer> parseRange(String range) {
        final String[] values = range
                .replace("[", "")
                .replace("]", "")
                .split(",");

        try {
            return Pair.of(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
