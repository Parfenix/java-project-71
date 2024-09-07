package hexlet.code.formatters;

import hexlet.code.DiffEntry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public final class JsonFormatter {
  private JsonFormatter() {
  }

  /**
   * Formats a list of differences into a JSON format.
   *
   * This method generates a JSON string that represents the differences between
   * two versions of data. Each difference is described with its key, old value,
   * new value, and the status of the change.
   *
   * @param diffEntries the list of differences to be formatted.
   *                    Each {@code DiffEntry} object
   *                    contains the key of the property,
   *                    its old value, its new value,
   *                    and the status of the change (added,
   *                    removed, updated, unchanged).
   * @return a string representing the formatted differences in JSON format.
   * @throws Exception if there is an error during JSON serialization.
   */
  public static String format(final List<DiffEntry> diffEntries)
          throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayNode arrayNode = objectMapper.createArrayNode();

    for (DiffEntry entry : diffEntries) {
      ObjectNode objectNode = objectMapper.createObjectNode();
      objectNode.put("key", entry.getKey());
      objectNode.put("status", entry.getStatus());

      if (!"unchanged".equals(entry.getStatus())) {
        objectNode.set("oldValue",
                toJsonNode(entry.getOldValue(), objectMapper));
        objectNode.set("newValue",
                toJsonNode(entry.getNewValue(), objectMapper));
      }

      arrayNode.add(objectNode);
    }

    return objectMapper.writerWithDefaultPrettyPrinter()
            .writeValueAsString(arrayNode);
  }

  /**
   * Converts an object to its corresponding JSON node representation.
   *
   * @param value the object to be converted.
   * @param objectMapper the ObjectMapper instance for conversion.
   * @return the corresponding JSON node.
   */
  private static com.fasterxml.jackson.databind.JsonNode toJsonNode(
          final Object value, final ObjectMapper objectMapper) {
    if (value == null) {
      return objectMapper.nullNode();
    }
    return objectMapper.valueToTree(value);
  }
}
