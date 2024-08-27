package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

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
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> map1 = objectMapper.readValue(
                Files.readString(Paths.get(filePath1)), TreeMap.class);

        Map<String, Object> map2 = objectMapper.readValue(
                Files.readString(Paths.get(filePath2)), TreeMap.class);

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
