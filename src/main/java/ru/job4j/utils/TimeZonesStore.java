package ru.job4j.utils;


import java.util.HashMap;
import java.util.Map;

public final class TimeZonesStore {

    private static final String[] TIME_ZONES = {
            "ACST центральноавстралийское время", "UTC+9:30",
            "AEST	восточноавстралийское время", "UTC+10",
            "AKST	аляскинское время", "UTC−9",
            "AST	атлантическое время", "UTC−4",
            "AWST	западноавстралийское время", "UTC+8",
            "CAT	центральноафриканское время", "UTC+2",
            "CET	центральноевропейское время", "UTC+1",
            "CST	центральноамериканское время", "UTC−6",
            "EAT	восточноафриканское время", "UTC+3",
            "EET	восточноевропейское время", "UTC+2",
            "EST	североамериканское восточное время", "UTC−5",
            "GMT	среднее время по Гринвичу", "UTC",
            "HAST	гавайско-алеутское время", "UTC−10",
            "MSK	московское время", "UTC+3",
            "MST	горное время", "UTC−7",
            "NST	ньюфаундлендское время", "UTC−3:30",
            "PST	североамериканское тихоокеанское время", "UTC−8",
            "UTC	всемирное координированное время", "UTC",
            "WAT	западноафриканское время", "UTC+1",
            "WET	западноевропейское время", "UTC"
    };

    private TimeZonesStore() {
    }

    public static Map<String, String> mapOfTZ() {
        Map<String, String> rsl = new HashMap<>();
        for (int i = 0; i < TIME_ZONES.length; i++) {
            if (i % 2 == 0) {
                rsl.put(TIME_ZONES[i], TIME_ZONES[i + 1]);
            }
        }
        return rsl;
    }

}
