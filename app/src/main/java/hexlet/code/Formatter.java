package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;

public class Formatter {
    public static final String FORMAT_JSON = "json";
    public static final String FORMAT_PLAIN = "plain";
    public static final String FORMAT_STYLISH = "stylish";

    public static String format(final List<DiffEntry> diffs, final String format) throws Exception {
        return switch (format) {
            case "plain" -> PlainFormatter.format(diffs);
            case "stylish" -> StylishFormatter.format(diffs);
            case "json" -> JsonFormatter.format(diffs);
            default -> throw new Exception("Unknown format: " + format);
        };
    }
}
