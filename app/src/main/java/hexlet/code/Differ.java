package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> map1 = objectMapper.readValue(Files.readString(Paths.get(filePath1)), TreeMap.class);
        Map<String, Object> map2 = objectMapper.readValue(Files.readString(Paths.get(filePath2)), TreeMap.class);

        StringBuilder result = new StringBuilder("{\n");

        for (String key : map1.keySet()) {
            if (!map2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");
            } else if (!map1.get(key).equals(map2.get(key))) {
                result.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");
                result.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");
            } else {
                result.append("    ").append(key).append(": ").append(map1.get(key)).append("\n");
            }
        }

        for (String key : map2.keySet()) {
            if (!map1.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }
}
