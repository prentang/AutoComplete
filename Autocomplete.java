import java.util.Arrays;
import java.util.Comparator;

import javax.management.Query;

import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

public class Autocomplete {
    //array of terms
    private Term [] terms;
    // Constructs an autocomplete data structure from an array of terms.
    public Autocomplete(Term[] terms) {
        //Constructer throw NPE if terms == null
        if (terms == null) {
            throw new NullPointerException("terms is null");
        }
        
        //Init this.terms to a defensive copy- create new copy for an object to prevent mods affecting origin
        this.terms = new Term[terms.length];
        for (int i = 0; i < terms.length; i++) {
            this.terms[i] = terms[i];
        }
        //Sort this.terms in lexicographic order
        Arrays.sort(this.terms);
    }

    // Returns all terms that start with prefix, in descending order of their weights.
    public Term[] allMatches(String prefix) {
        //Methods should throw a NPE if prefix == null
        if (prefix == null) {
            throw new NullPointerException("prefix is null");
        }
        
        //Find index in first term -> terms that starts with prefix
        Term T = new Term(prefix);
        Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());

        int i = BinarySearchDeluxe.firstIndexOf(terms, T, prefixOrder);

        int j = numberOfMatches(prefix);

        //Finds number of terms -> terms that start with prefix
        Term[] matches = new Term[j];


        //construct an array matches that conatining n elements from terms, starting at index i
        for (int n = 0; n < j; n++) {
            matches[n] = terms[i + n];
        }

        Arrays.sort(matches, Term.byReverseWeightOrder());
        return matches;
    }

    // Returns the number of terms that start with prefix.
    public int numberOfMatches(String prefix) {
        //Methods should throw a NPE if prefix == null
        if (prefix == null) {
            throw new NullPointerException("prefix is null");
        }

        //Find indicies i & j of the first AND last term -> terms (starts with prefix)
        Term P = new Term(prefix);
        Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());

        int i = BinarySearchDeluxe.firstIndexOf(terms, P, prefixOrder);

        int j = BinarySearchDeluxe.lastIndexOf(terms, P, prefixOrder);

        //Use indicies: compute # of terms that start with pre, return value
        if (i == -1 || j == -1) {
            return 0;
        }
        return j - i + 1;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        Autocomplete autocomplete = new Autocomplete(terms);
        StdOut.print("Enter a prefix (or ctrl-d to quit): ");
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            String msg = " matches for \"" + prefix + "\", in descending order by weight:";
            if (results.length == 0) {
                msg = "No matches";
            } else if (results.length > k) {
                msg = "First " + k + msg;
            } else {
                msg = "All" + msg;
            }
            StdOut.printf("%s\n", msg);
            for (int i = 0; i < Math.min(k, results.length); i++) {
                StdOut.println("  " + results[i]);
            }
            StdOut.print("Enter a prefix (or ctrl-d to quit): ");
        }
    }
}
