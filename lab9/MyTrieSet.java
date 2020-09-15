import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {
  private static final int R = 128;
  private TrieNode root;
  private static class TrieNode {
    private boolean isKey;
    private Map<Character, TrieNode> children;
    private char val;

    private TrieNode(char c, boolean b) {
      val = c;
      isKey = b;
      children = new HashMap<>();
    }
  }

  public MyTrieSet() {
    root = new TrieNode('&', false);
  }

  /**
   * Clears all items out of Trie
   */
  @Override
  public void clear() {
    root = null;
  }

  /**
   * Returns true if the Trie contains KEY, false otherwise
   */
  @Override
  public boolean contains(String key) {
    validateKey(key);

    if (root == null) {
      return false;
    }

    TrieNode curr = root;
    TrieNode next;

    for (int i = 0; i < key.length(); i++) {
      char c = key.charAt(i);
      next = curr.children.get(c);
      if (next == null) {
        return false;
      }
      curr = next;
    }

    return curr.isKey;
  }



  /**
   * Inserts string KEY into Trie
   */
  @Override
  public void add(String key) {
    validateKey(key);

    TrieNode curr = root;
    for (int i = 0; i < key.length(); i++) {
      char c = key.charAt(i);
      if (!curr.children.containsKey(c)) {
        curr.children.put(c, new TrieNode(c, false));
      }
      curr = curr.children.get(c);
    }
    curr.isKey = true;
  }

  /**
   * Returns a list of all words that start with PREFIX
   */
  @Override
  public List<String> keysWithPrefix(String prefix) {
    validateKey(prefix);

    TrieNode curr = root;
    for (int i = 0; i < prefix.length(); i++) {
      char c = prefix.charAt(i);
      if (curr.children.containsKey(c)) {
        curr = curr.children.get(c);
      }
    }

    List<String> result = new ArrayList<>();
    if (curr == root) {
      return result;
    }
    allKeysWithPrefix(result, curr, prefix);
    return result;
  }

  private void allKeysWithPrefix(List<String> result, TrieNode node, String prefix) {
    if (node.isKey) {
      result.add(prefix);
      // sam, same
      // without this condition check, the program stops at [sam] and ignores [same]
      if (node.children.isEmpty()) {
        return;
      }
    }

    for (char c: node.children.keySet()) {
      if (node.children.get(c) != null) {
        String temp = prefix;
        temp = temp + c;
        allKeysWithPrefix(result, node.children.get(c), temp);
      }
    }
  }

  /**
   * Returns the longest prefix of KEY that exists in the Trie Not required for Lab 9. If you don't
   * implement this, throw an UnsupportedOperationException.
   */
  @Override
  public String longestPrefixOf(String key) {
    throw new UnsupportedOperationException();
  }

  private void validateKey(String k) {
    if (k == null || k.length() == 0) {
      throw new IllegalArgumentException("Invalid input.");
    }
  }

  public static void main(String[] args) {
    MyTrieSet trie = new MyTrieSet();
    trie.add("hello");
    trie.add("hi");
    trie.add("help");
    trie.add("zebra");

    System.out.println(trie.contains("hi"));
    System.out.println(trie.contains("hello"));
    System.out.println(trie.contains("help"));
    System.out.println(trie.contains("zebra"));

    System.out.println(trie.contains("apple"));

    System.out.println(trie.keysWithPrefix("z"));

    System.out.println(trie.keysWithPrefix("he"));
    System.out.println(trie.keysWithPrefix("h"));
    System.out.println(trie.keysWithPrefix("hi"));
    System.out.println(trie.keysWithPrefix("hel"));

    List<String> test = trie.keysWithPrefix("x");
    System.out.println(test);

    System.out.println(trie.keysWithPrefix("a"));

  }
}
