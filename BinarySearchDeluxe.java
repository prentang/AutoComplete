import java.util.Arrays;
import java.util.Comparator;

import stdlib.In;
import stdlib.StdOut;

public class BinarySearchDeluxe {
    // Returns the index of the first key in a that equals the search key, or -1, according to
    // the order induced by the comparator c.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> c) {
        //if a && Key && c == null, throw NPE 
        if (a == null || key == null || c == null){
            throw new NullPointerException("a, key, or c is null");
        }
        //dec lo, hi, index
        int lo = 0;
        
        int hi = a.length - 1;
        
        int index = -1;

        //when a[mid] =~ key, index: init to -1, adjust hi
        while (lo <= hi) {
            //divide space in half
            int mid = lo + (hi - lo) / 2;
            
            int x = c.compare(key, a[mid]);
            //if x < 0, key < index[mid]: adjust hi pointer to mid -1 (lower half)
            if (x < 0){
                hi = mid - 1;
            // if x > 0, key < index[mid]: adjust hi pinter to mid +1 (upper half)
            } else if (x > 0) {
                lo = mid +1;
            //case for x = 0, match of key and index[mid], continue searching left half: adjust hi to mid-1
            } else {
                index = mid;
                hi = mid - 1;
            }
        }
        return index;
    }

    // Returns the index of the first key in a that equals the search key, or -1, according to
    // the order induced by the comparator c.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> c) {
         //if a && Key && c == null, throw NPE 
        if (a == null || key == null || c == null){
            throw new NullPointerException("a, key, or c is null");
        }
        
        //init var: lo, hi, index

        int lo = 0;
        int hi = a.length - 1;
        int index = -1;

        //value lo <= hi range of [a]
        while (lo <= hi) {
            
            //divide 
            int mid = lo + (hi - lo) / 2;
            int x = c.compare(key, a[mid]);
            
            //search space to lower half
            if (x < 0) {
                hi = mid - 1;
            //upper half
            } else if (x > 0) {
                lo = mid + 1;
            
            //search for right half: update index -> mid
            } else {
                lo = mid + 1;
                index = mid;
            }
        }

        return index;
    }

    // Unit tests the library. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        String prefix = args[1];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        Arrays.sort(terms);
        Term term = new Term(prefix);
        Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());
        int i = BinarySearchDeluxe.firstIndexOf(terms, term, prefixOrder);
        int j = BinarySearchDeluxe.lastIndexOf(terms, term, prefixOrder);
        int count = i == -1 && j == -1 ? 0 : j - i + 1;
        StdOut.println("firstIndexOf(" + prefix + ") = " + i);
        StdOut.println("lastIndexOf(" + prefix + ")  = " + j);
        StdOut.println("frequency(" + prefix + ")    = " + count);
    }
}
