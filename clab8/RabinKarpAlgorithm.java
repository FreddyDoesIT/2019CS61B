public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        if (input == null || input.length() == 0) {
            return -1;
        }
        if (pattern == null || pattern.length() == 0 || pattern.length() > input.length()) {
            return -1;
        }

        int len = pattern.length();
        RollingString src = new RollingString(pattern, len);
        int srcHash = src.hashCode();

        RollingString tgt = new RollingString(input.substring(0, len), len);
        for (int i = len; i < input.length(); i++) {
            if (tgt.hashCode() == srcHash && tgt.equals(src)) {
                return i - len;
            }
            tgt.addChar(input.charAt(i));
        }
        return -1;
    }

}
