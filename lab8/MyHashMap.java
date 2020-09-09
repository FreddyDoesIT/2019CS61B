import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * It is important to figure out how to store some Entry at a specified index.
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
  private class Entry<K, V> {
    private int hash;
    private K key;
    private V value;
    private Entry next;

    Entry(int hash, K key, V value, Entry next) {
      this.hash = hash;
      this.key = key;
      this.value = value;
      this.next = next;
    }

    void setKey(K key) {
      this.key = key;
    }

    K getKey() {
      return this.key;
    }

    void setHash(int hash) {
      this.hash = hash;
    }

    int getHash() {
      return this.hash;
    }

    void setValue(V value) {
      this.value = value;
    }

    V getValue() {
      return this.value;
    }

    void setNext(Entry next) {
      this.next = next;
    }

    Entry getNext() {
      return this.next;
    }

    boolean isSameKey(K key) {
      return this.key.equals(key);
    }

   }

  private static final int INITIAL_SIZE = 16;
  private static final double LOAD_FACTOR = 0.75;
  private Entry[] buckets;
  private Set<K> keySet = new HashSet<>();
  private double threshold;
  private int size;

  public MyHashMap() {
    threshold = INITIAL_SIZE * LOAD_FACTOR;
    buckets = new Entry[INITIAL_SIZE];
    size = 0;
  }

  public MyHashMap(int initialSize) {
    if (initialSize < 1) {
      throw new IllegalArgumentException("Initial Size should be larger than 1.");
    }
    threshold = initialSize * LOAD_FACTOR;
    buckets = new Entry[initialSize];
    size = 0;
  }

  public MyHashMap(int initialSize, double loadFactor) {
    if (initialSize < 1) {
      throw new IllegalArgumentException("Initial Size should be larger than 1.");
    }
    if (loadFactor <= 0.0) {
      throw new IllegalArgumentException("Load Factor should be larger than 0.0");
    }
    threshold = initialSize * loadFactor;
    buckets = new Entry[initialSize];
    size = 0;
  }

  /**
   * Removes all of the mappings from this map.
   */
  @Override
  public void clear() {
    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = null;
    }

    keySet.clear();
    size = 0;
  }

  @Override
  public boolean containsKey(K key) {
    validate(key);

    return keySet.contains(key);
  }

  @Override
  public V get(K key) {
    validate(key);

    if(!containsKey(key)) {
      return null;
    }

    int bucketIndex = hash(key, buckets.length);
    Entry<K, V> lookup = buckets[bucketIndex];
    while (lookup != null) {
      if (lookup.getHash() == bucketIndex && lookup.isSameKey(key)) {
        return lookup.getValue();
      }
      lookup = lookup.getNext();
    }
    return null;
  }

  private void validate (K key) {
    if (key == null) {
      throw new IllegalArgumentException("Invalid key.");
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void put(K key, V value) {
    int bucketIndex = hash(key, buckets.length);
    Entry<K, V> entry = buckets[bucketIndex];
    while(entry != null) {
      if (entry.isSameKey(key)) {
        entry.setValue(value);
        return;
      }
      entry = entry.getNext();
    }
    // each time chaining happens, the new entry becomes the first Entry in the current bucket
    entry = new Entry(bucketIndex, key, value, buckets[bucketIndex]);
    buckets[bucketIndex] = entry;

    keySet.add(key);
    size += 1;

    if (size > this.threshold) {
      sizeUp(buckets.length * 2);
    }
  }

  private void sizeUp(int capacity) {
    Entry<K, V>[] newEntry = new Entry[capacity];
    for (int i = 0; i < buckets.length; i++) {
      Entry<K, V> entry = buckets[i];
      while (entry != null) {
        Entry oldNext = entry.getNext();
        int newIndex = hash(entry.getKey(), newEntry.length);
        entry.setNext(newEntry[newIndex]);
        entry.setHash(newIndex);
        newEntry[newIndex] = entry;
        entry = oldNext;
      }
    }
    this.threshold *= 2;
    buckets = newEntry;
  }

  /*
  Rewrite hashcode for this class.
  The constant 0x7FFFFFFF is a 32-bit integer in hexadecimal with all but the highest bit set.
  Despite the name, this method isn't getting the hashCode, rather looking for which bucket
  the key should appear in for a hash set or map.
  When you use % on negative value, you get a negative value.
  There are no negative buckets so to avoid this you can remove the sign bit (the highest bit)
  and one way of doing this is to use a mask e.g. x & 0x7FFFFFFF
  which keeps all the bits except the top one.
   */
  private int hash(K k, int len) {
    if (k == null) {
      throw new IllegalArgumentException("Key cannot be null.");
    }

    // It returns the bucket index.
    return (k.hashCode() & 0x7fffffff) % len;
  }

  @Override
  public Set<K> keySet() {
    return keySet;
  }

  @Override
  public V remove(K key) {
    validate(key);
    if (!containsKey(key)) {
      return null;
    }

    int bucketIndex = hash(key, buckets.length);
    Entry<K, V> entry = buckets[bucketIndex];
    while (entry != null) {
      if (bucketIndex == entry.hash && entry.isSameKey(key)) {
        if (entry.getNext() != null) {
          Entry<K, V> rest = entry.getNext();
          Entry<K, V> restStart = rest;
          while (rest.getNext() != null) {
            rest = rest.getNext();
          }
          rest.setNext(buckets[bucketIndex]);
          buckets[bucketIndex] = restStart;
        }
        V result = entry.getValue();
        entry = null;
        size--;
        keySet.remove(key);
        return result;
      }
      entry = entry.getNext();
    }

    return null;
  }

  @Override
  public V remove(K key, V value) {
    validate(key);
    if (!containsKey(key)) {
      return null;
    }
    if (!get(key).equals(value)) {
      return null;
    }

    return remove(key);
  }

  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<K> iterator() {
    return keySet().iterator();
  }
}
