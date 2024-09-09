package hexlet.code.formatters;

import hexlet.code.DiffEntry;
import java.util.List;
import java.util.Map;

public class PlainFormatter {

    public static String format(final List<DiffEntry> diffEntries) {
        StringBuilder result = new StringBuilder();

        for (DiffEntry entry : diffEntries) {
            String key = entry.getKey();
            Object oldValue = entry.getOldValue();
            Object newValue = entry.getNewValue();
            String status = entry.getStatus();

            switch (status) {
                case "added" -> result.append("Property '").append(key)
                        .append("' was added with value: ")
                        .append(formatValue(newValue))
                        .append("\n");
                case "removed" -> result.append("Property '").append(key)
                        .append("' was removed\n");
                case "updated", "changed" -> result.append("Property '").append(key)
                        .append("' was updated. From ")
                        .append(formatValue(oldValue))
                        .append(" to ")
                        .append(formatValue(newValue))
                        .append("\n");
                case "unchanged" -> {
                    // Do nothing for unchanged properties
                }
                default -> throw new IllegalStateException(
                        "Unexpected value: " + status
                );
            }
        }
        return result.toString().trim();
    }

    private static String formatValue(final Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        } else if (value instanceof Boolean || value instanceof Number) {
            return value.toString();
        } else {
            return value.toString();
        }
    }
}
