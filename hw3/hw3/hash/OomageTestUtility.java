package hw3.hash;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OomageTestUtility {
    private static int numOfItems;
    private static int numOfBuckets;
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         *
         * N:     number of items
         * M:     number of buckets
         */
        numOfItems = oomages.size();
        numOfBuckets = M;
        List<List<Oomage>> buckets = new ArrayList<>(numOfBuckets);
        for (int i = 0; i < numOfBuckets; i++) {
            buckets.add(new LinkedList<>());
        }
        for (Oomage o: oomages) {
            int bucketNumber = convertHashToIndex(o.hashCode(), numOfBuckets);
            buckets.get(bucketNumber).add(o);
        }
        for (List<Oomage> list: buckets) {
            int size = list.size();
            if (size < (numOfItems / 50) || size > (numOfItems / 2.5)) {
                return false;
            }
        }

        return true;
    }

    private static int convertHashToIndex(int hash, int buckets) {
        return (hash & 0x7FFFFFFF) % buckets;
    }
}
