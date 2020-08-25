import java.util.ArrayList;
import java.util.List;

public class UnionFind {

  // TODO - Add instance variables?
  private int[] parent;

  // to hold current tree's size, index is the parent, treeSize[index] is the size
  private int[] treeSize;

  /* Creates a UnionFind data structure holding n vertices. Initially, all
     vertices are in disjoint sets. */
  public UnionFind(int n) {
    // TODO
    parent = new int[n];
    treeSize = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = -1;
      treeSize[i] = 1;
    }
  }

  /* Throws an exception if v1 is not a valid index. */
  private void validate(int vertex) {
    // TODO
    if (vertex < 0 || vertex >= parent.length) {
      throw new IllegalArgumentException("Invalid index.");
    }
  }

  /* Returns the size of the set v1 belongs to. */
  public int sizeOf(int v1) {
    // TODO
    validate(v1);
    int root = find(v1);
    return treeSize[root];
  }

  /* Returns the parent of v1. If v1 is the root of a tree, returns the
     negative size of the tree for which v1 is the root. */
  public int parent(int v1) {
    validate(v1);
    int parent = find(v1);
    if (v1 == parent) {
      return -treeSize[v1];
    }
    return parent;
  }

  /* Returns true if nodes v1 and v2 are connected. */
  public boolean connected(int v1, int v2) {
    // TODO
    validate(v1);
    validate(v2);
    return find(v1) == find(v2);
  }

  /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
     elements, and a union-by-size heuristic is used. If the sizes of the sets
     are equal, tie break by connecting v1's root to v2's root. Unioning a
     vertex with itself or vertices that are already connected should not
     change the sets but may alter the internal structure of the data. */
  public void union(int v1, int v2) {
    // TODO
    if (!connected(v1, v2)) {
      int size1 = sizeOf(v1);
      int size2 = sizeOf(v2);
      if (size1 > size2) {
        connect(v2, v1);
      } else {
        connect(v1, v2);
      }
    }
  }

  private void connect(int p, int q) {
    int i = find(p);
    int j = find(q);
    parent[i] = j;
    treeSize[j] = treeSize[i] + treeSize[j];
  }

  /* Returns the root of the set V belongs to. Path-compression is employed
     allowing for fast search-time. */
  //TODO: compression failed
  public int find(int vertex) {
    // TODO
    List<Integer> list = new ArrayList<>();
    int v = vertex;
    list.add(v);
    while (parent[v] >= 0) {
      v = parent[v];
      list.add(v);
    }
    if (list.size() > 2) {
      for (int i = list.size() - 1; i > 2; i--) {
        connect(i, v);
      }
    }
    return v;
  }
}
