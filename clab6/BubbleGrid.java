/*
    Four cases need to be discussed:
    1. The hit is 0. Nothing changes.
    2. The hit is 1. But it cannot connect to the ceiling, so nothing changes.
    3. The hit is 1. Though it can connect to the ceiling, its neighboring bubbles can connect
       to the ceiling via other bubbles, so nothing changes.
    4. The hit is 1. It can connect to the ceiling and it is the only way that its neighboring
       bubbles connect to the ceiling, so those bubbles drop.
 */
public class BubbleGrid {
    private int[][] grid;
    private int rowNum;
    private int colNum;
    private static final int CEILING = 0;
                                        //up    //down  //left   //right
    private int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        rowNum = grid.length;
        colNum = grid[0].length;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // TODO
        // the 0th element will be ceiling
        UnionFind uf = new UnionFind(rowNum * colNum + 1);
        // if there is a dart that will hit 1, we change it to 2
        for (int[] dart: darts) {
            if (grid[dart[0]][dart[1]] == 1) {
                grid[dart[0]][dart[1]] = 2;
            }
        }

        // start union find and connect available bubbles to ceiling
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (grid[i][j] == 1) {
                    unionNeighbors(i, j, grid, uf);
                }
            }
        }

        // init result
        int[] res = new int[darts.length];
        // find the number of bubbles that will not drop
        int count = uf.sizeOf(CEILING);

        // Put 1 back to the position where the dart hits
        for (int i = darts.length - 1; i >= 0; i--) {
            int row = darts[i][0];
            int col = darts[i][1];
            if (grid[row][col] == 2) {
                // the following sequence matters
                // we find the final result in a reverse order
                unionNeighbors(row, col, grid, uf);
                grid[row][col] = 1;
            }

            int newCount = uf.sizeOf(CEILING);
            res[i] = newCount > count ? newCount - count - 1 : 0;

            count = newCount;
        }

        return res;
    }

    private void unionNeighbors(int row, int col, int[][] grid, UnionFind uf) {
        if (row == 0) {
            uf.union(convertCoordinateToIndex(row, col), CEILING);
        }
        for (int[] dir: dirs) {
            int adjRow = row + dir[0];
            int adjCol = col + dir[1];
            if (adjRow < 0 || adjRow == rowNum || adjCol < 0 || adjCol == colNum) {
                continue;
            }
            if (grid[adjRow][adjCol] != 1) {
                continue;
            }
            uf.union(convertCoordinateToIndex(row, col),
                    convertCoordinateToIndex(adjRow, adjCol));
        }
    }

    // turn a 2d coordinate to 1d array index
    private int convertCoordinateToIndex(int row, int col) {
        return row * colNum + col + 1;
    }
}
