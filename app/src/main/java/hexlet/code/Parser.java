package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;


public class Parser {

    public static final String TYPE_JSON = "json";
    public static final String TYPE_YAML = "yaml";
    public static final String TYPE_YML = "yml";

    public static Map<String, Object> parse(final String filePath) throws Exception {
        String format = getFileExtension(filePath);
        ObjectMapper objectMapper;

        if (TYPE_YAML.equalsIgnoreCase(format) || TYPE_YML.equalsIgnoreCase(format)) {
            objectMapper = new ObjectMapper(new YAMLFactory());
        } else if (TYPE_JSON.equalsIgnoreCase(format)) {
            objectMapper = new ObjectMapper();
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + format);
        }

        return objectMapper.readValue(Files.readString(Paths.get(filePath)),
                LinkedHashMap.class);
    }

    private static String getFileExtension(final String filePath) {
        return filePath.substring(filePath.lastIndexOf('.') + 1);
    }
}
