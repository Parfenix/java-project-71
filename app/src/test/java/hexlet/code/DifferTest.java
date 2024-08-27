package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

  @Test
  void testCompareJSONFiles() throws Exception {
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

  /**
   * Tests the comparison of two YAML files to verify
   * that the differences are correctly identified and formatted.
   *
   * @throws Exception if an error occurs during file reading or parsing.
   */
  @Test
  public void testCompareYAMLFiles() throws Exception {
    String filePath1 = "src/test/resources/file1.yml";
    String filePath2 = "src/test/resources/file2.yml";

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


