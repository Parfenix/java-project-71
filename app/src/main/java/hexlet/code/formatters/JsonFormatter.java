package hexlet.code.formatters;

import hexlet.code.DiffEntry;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonFormatter {
    public static String format(final List<DiffEntry> diffEntries) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(diffEntries);
    }
}
