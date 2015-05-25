package core.complex.arraystrings;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by jhansen on 5/23/2015.
 */
public class Combinatorics
{
    private StringBuilder out = new StringBuilder();
    boolean found[];
    private String      in;
    private Set<String> set;

    public void Init( String str ) {
        in = str;
        found = new boolean[ str.length() ];
        set = new LinkedHashSet<>();
    }

    public int StringPermutations( int k ) {

        int count = 0;

        if ( out.length() == k ) {
            //System.out.print( out + " " );
            set.add( out.toString() );
            return 1;
        }

        for ( int i = 0; i < in.length(); i++ ) {
            if ( found[ i ] ) {
                continue;
            }
            out.append( in.charAt( i ) );
            found[ i ] = true;
            count += StringPermutations( k );
            found[ i ] = false;
            out.setLength( out.length() - 1 );
        }

        return count;
    }

    public int StringCombinations( int start, int k ) {

        int count = 0;

        if ( out.length() == k ) {
            //System.out.print( out + " " );
            set.add( out.toString() );
            return 1;
        }

        for ( int i = start; i < in.length(); i++ ) {
            out.append( in.charAt( i ) );
            count += StringCombinations( i + 1, k );
            out.setLength( out.length() - 1 );
        }

        return count;
    }

    public void PrintSet()
    {
        for ( String s : set ) {
            System.out.print( s + " " );
        }
        System.out.println();
    }
}