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
  public void testCompareYAMLFilesStylishFormat() throws Exception {
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

    String result = Differ.generate(filePath1, filePath2, "stylish");
    assertEquals(expected, result);
  }

  /**
   * Tests the comparison of two YAML files
   * and checks the output in plain format.
   * This test verifies that the {@link Differ#generate(String, String, String)}
   * method produces the correct output
   * when comparing two YAML files and formatting the result
   * @throws Exception
   */
  @Test
  public void testCompareYAMLFilesPlainFormat() throws Exception {
    String filePath1 = "src/test/resources/file1.yml";
    String filePath2 = "src/test/resources/file2.yml";

    String expected = """
      Property 'chars2' was updated. From [complex value] to false
      Property 'checked' was updated. From false to true
      Property 'default' was updated. From null to [complex value]
      Property 'id' was updated. From 45 to null
      Property 'key1' was removed
      Property 'key2' was added with value: 'value2'
      Property 'numbers2' was updated. From [complex value] to [complex value]
      Property 'numbers3' was removed
      Property 'numbers4' was added with value: [complex value]
      Property 'obj1' was added with value: [complex value]
      Property 'setting1' was updated. From 'Some value' to 'Another value'
      Property 'setting2' was updated. From 200 to 300
      Property 'setting3' was updated. From true to 'none'
      """;

    // Выполнение сравнения файлов с выводом в формате plain
    String result = Differ.generate(filePath1, filePath2, "plain");

    // Сравнение результата с ожидаемым значением
    assertEquals(expected.trim(), result.trim());
  }
}


