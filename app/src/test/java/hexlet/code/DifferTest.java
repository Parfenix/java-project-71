package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

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
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";

    String result = Differ.generate(filePath1, filePath2);
    assertEquals(expected, result);
  }
}


