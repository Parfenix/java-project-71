package hexlet.code.formatters;

import hexlet.code.DiffEntry;
import java.util.List;
import java.util.Map;

public final class PlainFormatter {

  /**
   * A formatter for generating plain text representations
   * of differences between two data versions.
   * <p>
   * This class formats differences in a human-readable
   * plain text format, showing the changes
   * made between two versions of data.
   * </p>
   */
  private PlainFormatter() {
  }

  /**
   * Formats a list of differences into a plain text format.
   * <p>
   * This method generates a human-readable string
   * that describes the differences between
   * two versions of data. Each difference is described
   * in terms of its key and its change
   * status, such as added, removed, or updated.
   * </p>
   *
   * @param diffEntries the list of differences to be formatted.
   *                    Each {@code DiffEntry} object
   *                    contains the key of the property,
   *                    its old value, its new value,
   *                    and the status of the change (added,
   *                    removed, updated, unchanged).
   * @return a string representing the formatted differences in plain text.
   *         The string describes each difference
   *         in a readable format, with each
   *         property change on a new line.
   * @throws IllegalStateException if the {@code status} field
   * in any {@code DiffEntry}
   *                                object contains an unexpected value.
   */
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

  /**
   * Formats a value for inclusion in the output.
   * <p>
   * This method converts different types of values
   * into a string format suitable for
   * the plain text output. Complex values
   * such as lists and maps are represented as
   * "[complex value]", while strings are enclosed in single quotes.
   * </p>
   *
   * @param value the value to be formatted.
   * @return the formatted string representation of the value.
   */
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
