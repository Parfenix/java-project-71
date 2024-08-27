package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

/**
 * The {@code Parser} class is responsible for parsing configuration files
 * in either JSON or YAML format.
 * It supports parsing into a map of key-value pairs for further processing.
 */
public final class Parser {
  /**
   * An instance of {@code ObjectMapper} used for parsing JSON or YAML files.
   * This object is initialized based
   * on the file format provided during the creation
   * of the {@code Parser} instance.
   */
  private final ObjectMapper objectMapper;

  /**
   * Constructs a {@code Parser} object based on the specified file format.
   *
   * @param format the format of
   * the file to be parsed (e.g., "json", "yaml", "yml").
   * If the format is unsupported,
   * an {@code IllegalArgumentException} is thrown.
   * @throws IllegalArgumentException if the provided format is not supported.
   */
  public Parser(final String format) {
    if ("yaml".equalsIgnoreCase(format) || "yml".equalsIgnoreCase(format)) {
      this.objectMapper = new ObjectMapper(new YAMLFactory());
    } else if ("json".equalsIgnoreCase(format)) {
      this.objectMapper = new ObjectMapper();
    } else {
      throw new IllegalArgumentException("Unsupported file format: " + format);
    }
  }

  /**
   * Parses the specified file and converts its contents into a {@code Map}.
   *
   * @param filePath the path to the file that should be parsed.
   * @return a map representing the key-value pairs from the parsed file.
   * @throws Exception if an error occurs during file reading or parsing.
   */
  public Map<String, Object> parse(final String filePath) throws Exception {
    return objectMapper.readValue(Files.readString(Paths.get(filePath)),
            TreeMap.class);
  }
}
