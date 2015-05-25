package practiceruns;

/**
 * Created by jhansen on 5/16/2015.
 */
public class ArrayStrings
{
    static public void Run( String in ) {

        String strs[] = in.split( " " );
        String set = strs[0];
        int k = Integer.parseInt( strs[1] );
        int count = 0;

        Combinatorics com = new Combinatorics();

        System.out.println( "nCk Combinations of " + set.length() + "P" + k + ": " + in + " are:" );
        com.Init( set );
        count = com.StringCombinations( 0, k );
        System.out.println( "Count:" + count + " combinations when choosing "
                            + k + " items " + " from a set of " + set.length() + "." );

        /*System.out.println( "nPk Permutations of " + set.length() + "P" + k + ": " + set + " are:" );
        com.Init( set );
        count = com.StringPermutations( k );
        System.out.println( "Count:" + count + " permutations when choosing "
                            + k + " items from a set of " + set.length() + "." );*/
    }

    static class Combinatorics
    {
        private StringBuilder out = new StringBuilder();
        boolean found[];
        private String in;

        void Init( String str ) {
            in = str;
            found = new boolean[ str.length() ];
        }

        /*public void PrintPermutations() {

            if ( out.length() == in.length() ) {
                System.out.println( out );
                return;
            }

            for ( int i = 0; i < in.length(); i++ ) {
                if ( found[i] ) {
                    continue;
                }
                out.append( in.charAt( i ) );
                found[i] = true;
                PrintPermutations();
                found[i] = false;
                out.setLength( out.length() - 1 );
            }
        }*/

        public int StringPermutations( int k ) {

            int count = 0;

            if ( out.length() == k ) {
                System.out.println( out );
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
                System.out.println( out );
                return 1;
            }

            for ( int i = start; i < in.length(); i++ ) {
                out.append( in.charAt( i ) );
                count += StringCombinations( i + 1, k );
                out.setLength( out.length() - 1 );
            }

            return count;
        }

        public int SetPermutations( int set[], int k ) {
            return -1;
        }

        public int SetCombinations( int set[], int k ) {
            return -1;
        }
    }

}
