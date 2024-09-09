package hexlet.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DifferTest {

    /**
     * Compares files based on their type.
     * the expected behavior and ensure that the files are correctly compared.
     *
     * @param type The type of the files to compare (e.g., json).
     * @throws Exception if an error occurs during comparison.
     */
    @ParameterizedTest
    @ValueSource(strings = {Parser.TYPE_JSON, Parser.TYPE_JSON})
    public void compareFiles(String type) throws Exception {
        var filepath1 = getFixturePath("file1." + type).toString();
        var filepath2 = getFixturePath("file2." + type).toString();
        var actual = Differ.generate(filepath1, filepath2);
        var expected = readFile(Formatter.FORMAT_STYLISH);
        assertEquals(expected, actual);
    }

    /**
     * Generates the difference between two files in the specified format.
     * This method is designed to be safely used within the final class `DifferTest`.
     *
     * @param format The format in which the difference should be generated.
     *               Possible values are "json", "plain", and "stylish".
     * @throws Exception if an error occurs during the generation of the difference.
     */
    @ParameterizedTest
    @ValueSource(strings = {Formatter.FORMAT_JSON, Formatter.FORMAT_PLAIN, Formatter.FORMAT_STYLISH})
    public void generateDiffInFormat(String format) throws Exception {
        var filepath1 = getFixturePath("file1.json").toString();
        var filepath2 = getFixturePath("file2.json").toString();
        var actual = Differ.generate(filepath1, filepath2, format);
        var expected = readFile(format);
        assertEquals(expected, actual);
    }

    private static Path getFixturePath(String filename) {
        return Paths.get("src", "test", "resources", filename);
    }

    private static String readFile(String filename) throws Exception {
        return Files.readString(getFixturePath(filename));
    }
}
