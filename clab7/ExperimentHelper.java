/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    // It is defined as the average depth times the number of nodes.
    public static int optimalIPL(int N) {
        return (int)(optimalAverageDepth(N) * N);
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        if (N <= 0) {
            return 0.0;
        }

        double level = Math.floor(log2(N));
        double oad = 0.0;
        int nodes = 0;
        for (int i = 0; i < level; i++) {
            nodes += Math.pow(2, i);
            oad += Math.pow(2, i) * i;
        }
        oad += (N - nodes) * level;
        return oad / N;
    }

    private static double log2(int N) {
        return Math.log(N) / Math.log(2);
    }
}
