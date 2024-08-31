package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The {@code Parser} class is responsible for parsing configuration files
 * in either JSON or YAML format.
 * It supports parsing into a map of key-value pairs for further processing.
 */
public final class Parser {
  // Private constructor to prevent instantiation of the class
  private Parser() {
    // This is a utility class and cannot be instantiated
  }

  /**
   * Parses the specified file and converts its contents into a {@code Map}.
   *
   * @param filePath the path to the file that should be parsed.
   * @return a map representing the key-value pairs from the parsed file.
   * @throws Exception if an error occurs during file reading or parsing.
   */
  public static Map<String, Object> parse(final String filePath)
          throws Exception {
    // Determine the format based on the file extension
    String format = getFileExtension(filePath);
    ObjectMapper objectMapper;

    if ("yaml".equalsIgnoreCase(format) || "yml".equalsIgnoreCase(format)) {
      objectMapper = new ObjectMapper(new YAMLFactory());
    } else if ("json".equalsIgnoreCase(format)) {
      objectMapper = new ObjectMapper();
    } else {
      throw new IllegalArgumentException("Unsupported file format: " + format);
    }

    return objectMapper.readValue(Files.readString(Paths.get(filePath)),
            LinkedHashMap.class);
  }

  /**
   * Extracts the file extension from the file path.
   *
   * @param filePath the path to the file.
   * @return the file extension.
   */
  private static String getFileExtension(final String filePath) {
    return filePath.substring(filePath.lastIndexOf('.') + 1);
  }
}
