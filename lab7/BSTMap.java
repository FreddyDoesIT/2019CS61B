import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

  private class Node {
    private K key;
    private Node left, right; // left and right subtrees
    private V value;
    private int size;

    public Node(K key, V value, int size) {
      this.key = key;      // sorted by key
      this.value = value;  // value of the key
      this.size = size;    // number of nodes in its subtree
    }
  }

  /* root of BST */
  private Node root;

  /**
   * Removes all of the mappings from this map.
   */
  @Override
  public void clear() {
    root.size = 0;
    root = null;
  }

  /** Returns true if this map contains a mapping for the specified key. */
  @Override
  public boolean containsKey(K key) {
    return get(key) != null;
  }

  /** Returns the value to which the specified key is mapped, or null if this
   * map contains no mapping for the key.
   */
  @Override
  public V get(K key) {
    Node rc = root;
    return find(key, rc);
  }

  private V find(K key, Node rootCopy) {
    if (key == null || rootCopy == null) {
      return null;
    }

    if (key.compareTo(rootCopy.key) == 0) {
      return rootCopy.value;
    } else if (key.compareTo(rootCopy.key) < 0) {
      return find(key, rootCopy.left);
    } else {
      return find(key, rootCopy.right);
    }
  }

  /** Returns the number of key-value mappings in this map. */
  @Override
  public int size() {
    return root == null ? 0 : root.size + 1;
  }

  /** Associates the specified value with the specified key in this map. */
  @Override
  public void put(K key, V value) {
    if (key == null) {
      return;
    }

    Node toPut = new Node(key, value, 0);
    if (root == null) {
      root = toPut;
      return;
    }

    if (key.compareTo(root.key) == 0) {
      throw new IllegalArgumentException("This item has existed.");
    }

    Node node = root;
    onPut(toPut, node);
    root.size++;
  }

  private void onPut(Node node, Node rootCopy) {
    if (node.key.compareTo(rootCopy.key) < 0) {
      if (rootCopy.left == null) {
        rootCopy.left = node;
        return;
      }
      onPut(node, rootCopy.left);
    } else {
      if (rootCopy.right == null) {
        rootCopy.right = node;
        return;
      }
      onPut(node, rootCopy.right);
    }
  }

  /** Returns a Set view of the keys contained in this map. */
  @Override
  public Set<K> keySet() {
    return null;
  }

  /** Removes the mapping for the specified key from this map if present.
   * Not required for Lab 8. If you don't implement this, throw an
   * UnsupportedOperationException. */
  @Override
  public V remove(K key) {
    return null;
  }

  /** Removes the entry for the specified key only if it is currently mapped to
   * the specified value. Not required for Lab 8. If you don't implement this,
   * throw an UnsupportedOperationException.*/
  @Override
  public V remove(K key, V value) {
    return null;
  }

  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<K> iterator() {
    return null;
  }
}
