import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by hug.
 */
public class Experiments {

    private static BST bst;
    public static void experiment1() {
        List<Integer> treeSize = new ArrayList<>();
        List<Double> averageDepth = new ArrayList<>();
        bst = new BST();
        for (int i = 0; i < 5000; i++) {
            bst.add(RandomGenerator.getRandomInt(5000));
            treeSize.add(bst.size());
            averageDepth.add(bst.averageDepth());
        }

        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .xAxisTitle("Tree Size")
                .yAxisTitle("Average Depth")
                .build();

        chart.addSeries("Tree Size", treeSize);
        chart.addSeries("Average Depth", averageDepth);

        new SwingWrapper(chart).displayChart();
        System.out.println(
                String.format("Tree Size is: %s. Its average depth is: %s.",
                bst.size(), bst.averageDepth()));
    }

    public static void experiment2() {
        bst = new BST();
        // step 1
        for (int i = 0; i < 1000; i++) {
            bst.add(RandomGenerator.getRandomInt(500));
        }
        // starting depth
        double depth = bst.averageDepth();

        List<Double> depths = new ArrayList<>();
        List<Double> startingDepth = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            // step 2 random delete
            bst.deleteTakingSuccessor(bst.getRandomKey());
            // step 3 random add
            int key = RandomGenerator.getRandomInt(1000);
//            while (bst.contains(key)) {
//                key = RandomGenerator.getRandomInt(1000);
//            }
            bst.add(key);
            // step 4
            depths.add(bst.averageDepth());
            startingDepth.add(depth);
        }

        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .xAxisTitle("Average Depth After Random Deletion and Insertion.")
                .yAxisTitle("Starting Average Depth")
                .build();

        chart.addSeries("Starting Average Depth", startingDepth);
        chart.addSeries("Average Depth", depths);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        bst = new BST();
        // step 1
        for (int i = 0; i < 1000; i++) {
            bst.add(RandomGenerator.getRandomInt(500));
        }
        // starting depth
        double depth = bst.averageDepth();

        List<Double> depths = new ArrayList<>();
        List<Double> startingDepth = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            // step 2 random delete
            bst.deleteTakingRandom(bst.getRandomKey());
            // step 3 random add
            int key = RandomGenerator.getRandomInt(1000);
//            while (bst.contains(key)) {
//                key = RandomGenerator.getRandomInt(1000);
//            }
            bst.add(key);
            // step 4
            depths.add(bst.averageDepth());
            startingDepth.add(depth);
        }

        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .xAxisTitle("Average Depth After Random Deletion and Insertion.")
                .yAxisTitle("Starting Average Depth")
                .build();

        chart.addSeries("Starting Average Depth", startingDepth);
        chart.addSeries("Average Depth", depths);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
//        experiment1();
//        experiment2();
        experiment3();
    }
}
