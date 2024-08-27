package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

  @Test
  void testGenerate() throws Exception {
    String filePath1 = "src/test/resources/file1.json";
    String filePath2 = "src/test/resources/file2.json";

    String expected = """
      {
        - follow: false
          host: hexlet.io
        - proxy: 123.234.53.22
        - timeout: 50
        + timeout: 20
        + verbose: true
      }""";

    String result = Differ.generate(filePath1, filePath2);
    assertEquals(expected, result);
  }
}


