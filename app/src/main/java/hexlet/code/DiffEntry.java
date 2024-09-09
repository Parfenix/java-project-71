package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiffEntry {

    private final String key;
    private final Object oldValue;
    private final Object newValue;
    private final String status;

}
