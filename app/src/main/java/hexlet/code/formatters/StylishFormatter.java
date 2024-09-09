package hexlet.code.formatters;

import hexlet.code.DiffEntry;

import java.util.List;

public class StylishFormatter {

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
                        .append(key)
                        .append(": ")
                        .append(oldValue)
                        .append("\n");
                default -> throw new IllegalStateException(
                        "Unexpected value: " + status
                );
            }
        }

        result.append("}");
        return result.toString();
    }
}
