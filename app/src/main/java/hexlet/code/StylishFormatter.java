package hexlet.code;

import java.util.List;

/**
 *  The {@code StylishFormatter} class provides methods for formatting the
 *  differences between two data structures in a stylish format.
*/
public final class StylishFormatter {

    /**
     * Private constructor to prevent instantiation.
     */
    private StylishFormatter() {

    }

    /**
     * Formats the list of differences into a stylish string.
     *
     * @param diffEntries the list of differences to format.
     * @return the formatted string representing the differences.
     */
    public static String format(final List<DiffEntry> diffEntries) {
        StringBuilder result = new StringBuilder("{\n");

        for (DiffEntry entry : diffEntries) {
            String key = entry.getKey();
            Object oldValue = entry.getOldValue();
            Object newValue = entry.getNewValue();
            String status = entry.getStatus();

            switch (status) {
                case "added" -> result.append("  + ")
                        .append(key)
                        .append(": ")
                        .append(newValue)
                        .append("\n");
                case "removed" -> result.append("  - ")
                        .append(key)
                        .append(": ")
                        .append(oldValue)
                        .append("\n");
                case "changed" -> {
                    result.append("  - ")
                            .append(key)
                            .append(": ")
                            .append(oldValue)
                            .append("\n");
                    result.append("  + ")
                            .append(key)
                            .append(": ")
                            .append(newValue)
                            .append("\n");
                }
                case "unchanged" -> result.append("    ")
                        .append(key).append(": ")
                        .append(oldValue)
                        .append("\n");
                default -> throw new
                        IllegalStateException("Unexpected value: " + status);
            }
        }

        result.append("}");
        return result.toString();
    }
}
