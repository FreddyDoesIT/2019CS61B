import org.junit.Test;

import static org.junit.Assert.*;

public class UnionFind2Test {
  UnionFind2 uf = new UnionFind2(7);

  @Test
  public void test(){

    uf.union(0,1);
    System.out.println(uf.sizeOf(0));
    System.out.println(uf.sizeOf(1));
  }
}