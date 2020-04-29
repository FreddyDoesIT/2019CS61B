package Exercises;

public class SummingArgs {
  public static void main(String[] args) {
    int nums[] = new int[args.length];
    for (int i = 0; i < nums.length; i++) {
      nums[i] = Integer.parseInt(args[i]);
    }

    int sum = 0;
    for (int num: nums) {
      sum += num;
    }

    System.out.println(sum);
  }
}
