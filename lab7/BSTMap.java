import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

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
  private Set<K> keySet;

  public BSTMap() {
    keySet = new HashSet<>();
  }
  /**
   * Removes all of the mappings from this map.
   */
  @Override
  public void clear() {
    root = null;
  }

  /** Returns true if this map contains a mapping for the specified key. */
  @Override
  public boolean containsKey(K key) {
    if (key == null) {
      throw new IllegalArgumentException("Key cannot be null.");
    }
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
    if (key == null) {
      throw new IllegalArgumentException("Key cannot be null.");
    }

    if (rootCopy == null) {
      return null;
    }

    int cmp = key.compareTo(rootCopy.key);
    if (cmp == 0) {
      return rootCopy.value;
    } else if (cmp < 0) {
      return find(key, rootCopy.left);
    } else {
      return find(key, rootCopy.right);
    }
  }

  /** Returns the number of key-value mappings in this map. */
  @Override
  public int size() {
    return size(root);
  }

  private int size(Node node) {
    return node == null ? 0 : node.size + 1;
  }

  /** Associates the specified value with the specified key in this map. */
  @Override
  public void put(K key, V value) {
    if (key == null) {
      return;
    }

    Node node = root;
    root = onPut(node, key, value);
    keySet.add(key);
//    Node toPut = new Node(key, value, 0);
//    if (root == null) {
//      root = toPut;
//      return;
//    }
//
//    if (key.compareTo(root.key) == 0) {
//      throw new IllegalArgumentException("This item has existed.");
//    }
//
//    Node node = root;
//    onPut(toPut, node);
//    root.size++;
  }

  private Node onPut(Node x, K key, V value) {
    if (x == null) {
      return new Node(key, value, 0);
    }

    int cmp = key.compareTo(x.key);
    if (cmp == 0) {
      // update x's value
      x.value = value;
    } else if (cmp < 0) {
      x.left = onPut(x.left, key, value);
    } else {
      x.right = onPut(x.right, key, value);
    }

    x.size = size(x.left) + size(x.right);
    return x;
  }

//  private void onPut(Node node, Node rootCopy) {
//    if (node.key.compareTo(rootCopy.key) < 0) {
//      if (rootCopy.left == null) {
//        rootCopy.left = node;
//        return;
//      }
//      onPut(node, rootCopy.left);
//    } else {
//      if (rootCopy.right == null) {
//        rootCopy.right = node;
//        return;
//      }
//      onPut(node, rootCopy.right);
//    }
//  }

  /** Print the BSTMap (key, vale) pairs in increasing of order of key. */
  public void printInOrder() {
    printInOrder(root);
  }

  private void printInOrder(Node root) {
    if (root == null) {
      return;
    }

    printInOrder(root.left);
    System.out.println("[" + root.key + " : " + root.value + "]");
    printInOrder(root.right);
  }

  /** Returns a Set view of the keys contained in this map. */
  @Override
  public Set<K> keySet() {
    if (root == null) {
      return null;
    }

    return keySet;
  }

  /** Removes the mapping for the specified key from this map if present.
   * Not required for Lab 8. If you don't implement this, throw an
   * UnsupportedOperationException. */
  @Override
  public V remove(K key) {
    if (key == null) {
      throw new IllegalArgumentException("Key cannot be null.");
    }

    V result = null;
    Node copy = root;
    if (containsKey(key)) {
      keySet.remove(key);
      result = remove(key, copy);
    }
    root.size--;
    return result;
  }

  private V remove (K key, Node node) {
    int cmp = key.compareTo(node.key);
    if (cmp == 0) {
      V result = node.value;
      Node temp = node.left;
      if (node.right != null) {
        node = node.right;
      }
      node.left = temp;
      return result;
    } else if (cmp < 0) {
      return remove(key, node.left);
    } else {
      return remove(key, node.right);
    }
  }

  /** Removes the entry for the specified key only if it is currently mapped to
   * the specified value. Not required for Lab 8. If you don't implement this,
   * throw an UnsupportedOperationException.*/
  @Override
  public V remove(K key, V value) {
    if (key == null) {
      throw new IllegalArgumentException("Key cannot be null.");
    }

    Node copy = root;
    if (containsKey(key)) {
      keySet.remove(key);
      if (remove(key, value, copy)) {
        root.size --;
        return value;
      } else {
        return null;
      }
    }
    return null;
  }

  private boolean remove (K key, V value, Node node) {
    int cmp = key.compareTo(node.key);
    if (cmp == 0) {
      V result = node.value;
      if (result.equals(value)) {
        Node temp = node.left;
        if (node.right != null) {
          node = node.right;
        }
        node.left = temp;
        return true;
      }
      return false;
    } else if (cmp < 0) {
      return remove(key, value, node.left);
    } else {
      return remove(key, value, node.right);
    }
  }

  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<K> iterator() {
    return new BSTMapIterator(root);
  }

  private class BSTMapIterator implements Iterator<K>{
    private Stack<Node> stack;

    // first push and root and all its left nodes to the stack
    BSTMapIterator(Node node) {
      stack = new Stack<>();
      while (node != null) {
        stack.push(node);
        node = node.left;
      }
    }

    public boolean hasNext() {
      return !stack.isEmpty();
    }

    public K next() {
      Node cur = stack.pop();

      if (cur.right != null) {
        Node temp = cur.right;
        //push all left nodes of this current node to stack
        while (temp != null) {
          stack.push(temp);
          temp = temp.left;
        }
      }

      return cur.key;
    }
  }

  public static void main(String[] args) {
    BSTMap<String, Integer> bstMap = new BSTMap<>();
    for (int i = 0; i < 10; i++) {
      bstMap.put("hi" + i, 1 + i);
    }

    bstMap.printInOrder();

    Iterator<String> itr = bstMap.iterator();
    while (itr.hasNext()) {
      System.out.println(itr.next());
    }
  }
}
