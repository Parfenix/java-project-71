package hexlet.code;

import java.util.Map;

/**
 * Utility class for generating a diff-like representation of the differences
 * between two JSON files.
 */
public final class Differ {

    // Private constructor to prevent instantiation
    private Differ() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    /**
     * Generates a diff-like representation
     * of the differences between two JSON files.
     *
     * @param filePath1 The path to the
     * first JSON file. Should be a valid file path.
     * @param filePath2 The path to
     * the second JSON file. Should be a valid file path.
     * @return A string representation
     * of the differences between the two files.
     * @throws Exception If an error
     * occurs while reading files or processing JSON.
     */
    public static String generate(final String filePath1,
                                  final String filePath2) throws Exception {

        String format1 = getFileExtension(filePath1);
        String format2 = getFileExtension(filePath2);

        if (!format1.equals(format2)) {
            throw new IllegalArgumentException(
                    "Files must be of the same format"
            );
        }

        Parser parser = new Parser(format1);
        Map<String, Object> map1 = parser.parse(filePath1);
        Map<String, Object> map2 = parser.parse(filePath2);

        return generateDiff(map1, map2);
    }

    private static String getFileExtension(final String filePath) {
        int index = filePath.lastIndexOf('.');
        if (index > 0 && index < filePath.length() - 1) {
            return filePath
                    .substring(index + 1)
                    .toLowerCase();
        }
        throw new IllegalArgumentException("File does not have an extension: "
                + filePath);
    }

    private static String generateDiff(final Map<String, Object> map1,
                                       final Map<String, Object> map2) {

        StringBuilder result = new StringBuilder("{\n");

        for (String key : map1.keySet()) {
            if (!map2.containsKey(key)) {
                result.append("  - ")
                        .append(key)
                        .append(": ")
                        .append(map1.get(key))
                        .append("\n");
            } else if (!map1.get(key).equals(map2.get(key))) {
                result.append("  - ")
                        .append(key)
                        .append(": ")
                        .append(map1.get(key))
                        .append("\n");
                result.append("  + ")
                        .append(key)
                        .append(": ")
                        .append(map2.get(key))
                        .append("\n");
            } else {
                result.append("    ")
                        .append(key)
                        .append(": ")
                        .append(map1.get(key))
                        .append("\n");
            }
        }

        for (String key : map2.keySet()) {
            if (!map1.containsKey(key)) {
                result.append("  + ")
                        .append(key)
                        .append(": ")
                        .append(map2.get(key))
                        .append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }
}
