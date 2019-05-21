package com.filho.profiles.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.util.Pair;

import static com.filho.util.RangeUtil.parseRange;

@Data
@Builder
public class ProfileFilter {
    private Boolean hasPhoto;
    private Boolean hasContact;
    private Boolean isFavorite;
    private Pair<Integer, Integer> compatibilityScoreRange;
    private Pair<Integer, Integer> heightRange;
    private Pair<Integer, Integer> ageRange;
    private Integer distanceRadiusInKm;
    private String religion;

    /**
     * Creates an object of filters from request parameters.
     * @param hasPhoto                the "photos" parameter.
     * @param hasContact              the "contact" parameter.
     * @param isFavorite              the "favorite" parameter.
     * @param compatibilityScoreRange the "compatScore" parameter.
     * @param heightRange             the "height" parameter.
     * @param distance                the "distance" parameter.
     * @return
     */
    public static ProfileFilter fromRequestParams(
            Boolean hasPhoto,
            Boolean hasContact,
            Boolean isFavorite,
            String compatibilityScoreRange,
            String ageRange,
            String heightRange,
            String distance,
            String religion) {

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
        if (ageRange != null) {
            builder.ageRange(parseRange(ageRange));
        }
        if (heightRange != null) {
            builder.heightRange(parseRange(heightRange));
        }
        if (religion != null) {
            builder.religion(religion);
        }

        return builder.build();
    }


}
