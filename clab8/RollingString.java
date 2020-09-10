import java.util.ArrayList;
import java.util.List;

/**
 * A String-like class that allows users to add and remove characters in the String
 * in constant time and have a constant-time hash function. Used for the Rabin-Karp
 * string-matching algorithm.
 */
class RollingString{

    /**
     * Number of total possible int values a character can take on.
     * DO NOT CHANGE THIS.
     */
    static final int UNIQUECHARS = 128;

    /**
     * The prime base that we are using as our mod space. Happens to be 61B. :)
     * DO NOT CHANGE THIS.
     */
    static final int PRIMEBASE = 6113;
    private List<Character> chars;
    /**
     * Initializes a RollingString with a current value of String s.
     * s must be the same length as the maximum length.
     */
    public RollingString(String s, int length) {
        assert(s.length() == length);
        /* FIX ME */
        chars = new ArrayList<>(length);
        for (int i = 0; i < s.length(); i++) {
            chars.add(i, s.charAt(i));
        }
    }

    /**
     * Adds a character to the back of the stored "string" and 
     * removes the first character of the "string". 
     * Should be a constant-time operation.
     */
    public void addChar(char c) {
        /* FIX ME */
        if (length() == 0) {
            return;
        }
        chars.remove(chars.get(0));
        chars.add(chars.size(), c);
    }


    /**
     * Returns the "string" stored in this RollingString, i.e. materializes
     * the String. Should take linear time in the number of characters in
     * the string.
     */
    public String toString() {
        StringBuilder strb = new StringBuilder();
        /* FIX ME */
        for (Character c: chars) {
            strb.append(c);
        }
        return strb.toString();
    }

    /**
     * Returns the fixed length of the stored "string".
     * Should be a constant-time operation.
     */
    public int length() {
        /* FIX ME */
        return chars.size();
    }


    /**
     * Checks if two RollingStrings are equal.
     * Two RollingStrings are equal if they have the same characters in the same
     * order, i.e. their materialized strings are the same.
     */
    @Override
    public boolean equals(Object o) {
        /* FIX ME */
        if (o == null) return false;
        if (!o.getClass().equals(this.getClass())) return false;
        if (o == this) return true;

        RollingString other = (RollingString) o;
        if (this.length() != other.length()) return false;
        String s = this.toString();
        String s2 = other.toString();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the hashcode of the stored "string".
     * Should take constant time.
     */
    @Override
    public int hashCode() {
        /* FIX ME */
        String s = this.toString();
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = (hash * UNIQUECHARS + s.charAt(i)) % PRIMEBASE;
        }

        return hash;
    }
}
