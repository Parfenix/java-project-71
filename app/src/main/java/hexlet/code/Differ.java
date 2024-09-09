package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, Formatter.FORMAT_STYLISH);
    }

    public static List<DiffEntry> generateDiff(final Map<String, Object> map1, final Map<String, Object> map2) {
        Set<String> allKeys = getAllKeys(map1, map2);
        List<DiffEntry> diffs = new ArrayList<>();

        for (String key : allKeys) {
            diffs.add(createDiffEntry(key, map1, map2));
        }

        return diffs;
    }

    private static Set<String> getAllKeys(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());
        return allKeys;
    }

    private static DiffEntry createDiffEntry(String key, Map<String, Object> map1, Map<String, Object> map2) {
        Object value1 = map1.get(key);
        Object value2 = map2.get(key);

        if (!map1.containsKey(key)) {
            return new DiffEntry(key, null, value2, "added");
        } else if (!map2.containsKey(key)) {
            return new DiffEntry(key, value1, null, "removed");
        } else if (!Objects.equals(value1, value2)) {
            return new DiffEntry(key, value1, value2, "changed");
        } else {
            return new DiffEntry(key, value1, value2, "unchanged");
        }
    }

    public static String generate(final String filepath1, final String filepath2, final String format)
            throws Exception {

        Map<String, Object> map1 = Parser.parse(filepath1);
        Map<String, Object> map2 = Parser.parse(filepath2);

        List<DiffEntry> diffs = generateDiff(map1, map2);
        return Formatter.format(diffs, format);
    }
}

