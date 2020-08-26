import org.junit.Test;

import static org.junit.Assert.*;

public class UnionFindTest {
  @Test
  public void TestBasics() {
    UnionFind uf = new UnionFind(10);
    uf.union(0, 1);
    uf.union(2, 3);
    uf.union(1, 2);
    assertEquals(3, uf.parent(0));

    // assertTrue(uf.connected(0, 2));
    uf.union(0, 2);
    assertEquals(3, uf.parent(0)); // Path-compression

    assertEquals(4, uf.sizeOf(0));
    assertEquals(4, uf.sizeOf(1));
    assertEquals(4, uf.sizeOf(2));
    assertEquals(4, uf.sizeOf(3));
    assertTrue(uf.connected(1, 3));

    assertEquals(3, uf.find(0));
//    try {
//      uf.sizeOf(-1);
//    } catch (IllegalArgumentException ex) {
//      //
//    }
//
//    try {
//      uf.sizeOf(12);
//    } catch (IllegalArgumentException ex) {
//      //
//    }
//
//    int expected = 0;
//    int actual = uf.find(0);
//    assertEquals(expected, actual);
//
//    expected = -1;
//    actual = uf.parent(0);
//    assertEquals(expected, actual);
//
//    assertFalse(uf.connected(0, 1));
//    assertEquals(uf.sizeOf(0), uf.sizeOf(1));
//    uf.union(0, 1);
//    assertTrue(uf.connected(0, 1));
//
//    uf.union(1,2);
//    uf.union(2,3);
//    uf.union(3,4);
//
//    uf.union(5,6);
//    uf.union(7,8);
//    uf.union(5,8);
//    uf.union(1,8);
//
//    assertTrue(uf.connected(1,8));

//    expected = 5;
//    actual = uf.sizeOf(3);
//    assertEquals(expected, actual);

//
//    expected = 1;
//    actual = uf.find(0);
//    assertEquals(expected, actual);
//
//    uf.union(2,3);
//    assertTrue(uf.connected(2,3));
//    assertFalse(uf.connected(0,2));
//    uf.union(2,4);
//    assertTrue(uf.connected(3,4));
//    assertFalse(uf.connected(0,4));
//    uf.union(1,4);
//    assertTrue(uf.connected(1,4));
//    assertTrue(uf.connected(1,3));
//
//    expected = 3;
//    actual = uf.find(1);
//    assertEquals(expected, actual);

  }

}