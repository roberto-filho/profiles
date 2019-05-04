package com.filho.util;

import org.springframework.data.util.Pair;

public class RangeUtil {

    public static Pair<Integer, Integer> parseRange(String range) {
        final String[] values = range
                .replace("[", "")
                .replace("]", "")
                .split(",");

        try {
            final int first = Integer.parseInt(values[0]);
            final int second = Integer.parseInt(values[1]);

            if (first > second) {
                return Pair.of(second, first);
            }

            return Pair.of(first, second);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
