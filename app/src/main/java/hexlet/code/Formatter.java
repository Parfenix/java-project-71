package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;

public final class Formatter {
  private Formatter() {

  }

  /**
   * Formats a list of differences based on the specified format.
   *
   * This method takes a list of {@link DiffEntry} objects
   * representing the differences between two files
   * and formats the output according to the specified format type.
   * It supports two formats:
   * - "plain": A plain text format that describes the differences.
   * - "stylish": A styled format that presents
   * the differences in a more structured way.
   *
   * @param diffs the list of differences to be formatted.
   * @param format the format in which to present the differences.
   * Valid values are "plain" and "stylish".
   * @return a string representing the formatted differences.
   * @throws Exception if the provided format is not recognized
   * or if any other error occurs during formatting.
   */
  public static String format(final List<DiffEntry> diffs,
                              final String format) throws Exception {
    return switch (format) {
      case "plain" -> PlainFormatter.format(diffs);
      case "stylish" -> StylishFormatter.format(diffs);
      case "json" -> JsonFormatter.format(diffs);
      default -> throw new Exception("Unknown format: " + format);
    };
  }
}
