package hexlet.code;

/**
 * Represents a single difference entry between two data structures.
 */
public final class DiffEntry {
  /**
   * the key associated with this difference.
   */
  private final String key;
  /**
   * the old value associated with this key
   * (can be null if the key is newly added).
   */
  private final Object oldValue;
  /**
   *  the new value associated with this key
   *  (can be null if the key was removed).
   */
  private final Object newValue;
  /**
   * the status of the difference
   * (e.g., "added", "removed", "changed", "unchanged").
   */
  private final String status;

  /**
   * Constructs a DiffEntry object to represent a difference.
   *
   * @param diffKey      the key associated with this difference
   * @param diffOldValue the old value associated with this key
   *                     (can be null if the key is newly added)
   * @param diffNewValue the new value associated with this key
   *                     (can be null if the key was removed)
   * @param diffStatus   the status of the difference
   *                     (e.g., "added", "removed", "changed", "unchanged")
   */
  public DiffEntry(final String diffKey,
                   final Object diffOldValue,
                   final Object diffNewValue,
                   final String diffStatus) {
    this.key = diffKey;
    this.oldValue = diffOldValue;
    this.newValue = diffNewValue;
    this.status = diffStatus;
  }

  /**
   * Returns the key associated with this difference.
   *
   * @return the key associated with this difference
   */
  public String getKey() {
    return key;
  }

  /**
   * Returns the old value associated with this key.
   *
   * @return the old value, or null if the key is newly added
   */
  public Object getOldValue() {
    return oldValue;
  }

  /**
   * Returns the new value associated with this key.
   *
   * @return the new value, or null if the key was removed
   */
  public Object getNewValue() {
    return newValue;
  }

  /**
   * Returns the status of this difference.
   *
   * @return the status of the difference
   * (e.g., "added", "removed", "changed", "unchanged")
   */
  public String getStatus() {
    return status;
  }
}
