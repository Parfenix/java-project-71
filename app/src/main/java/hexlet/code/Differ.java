package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class Differ {

    private Differ() {
        // Private constructor to prevent instantiation
    }

    /**
     * Generates a list of differences between two maps.
     *
     * @param map1 the first map to compare.
     * @param map2 the second map to compare.
     * @return a list of differences.
     */
    public static List<DiffEntry> generateDiff(final Map<String, Object> map1,
                                               final Map<String, Object> map2) {
        Set<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<DiffEntry> diffs = new ArrayList<>();

        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (!map1.containsKey(key)) {
                diffs.add(new DiffEntry(key, null, value2, "added"));
            } else if (!map2.containsKey(key)) {
                diffs.add(new DiffEntry(key, value1, null, "removed"));
            } else if (!Objects.equals(value1, value2)) {
                diffs.add(new DiffEntry(key, value1, value2, "changed"));
            } else {
                diffs.add(new DiffEntry(key, value1, value2, "unchanged"));
            }
        }

        return diffs;
    }

    /**
     * Generates a formatted string showing the differences between two files.
     *
     * @param filepath1 the path to the first file.
     * @param filepath2 the path to the second file.
     * @return a formatted string of differences.
     * @throws Exception if an error occurs during parsing or comparison.
     */
    public static String generate(final String filepath1,
                                  final String filepath2) throws Exception {
        String format1 = getFileExtension(filepath1);
        String format2 = getFileExtension(filepath2);

        // Assuming both files have the same format,
        // otherwise some additional check might be needed
        Map<String, Object> map1 = Parser.parse(filepath1);
        Map<String, Object> map2 = Parser.parse(filepath2);

        List<DiffEntry> diffs = generateDiff(map1, map2);
        return StylishFormatter.format(diffs);
    }

    private static String getFileExtension(final String filepath) {
        return filepath.substring(filepath.lastIndexOf(".") + 1);
    }
}
