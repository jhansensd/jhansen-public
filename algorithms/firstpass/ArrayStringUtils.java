package testdstructures;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by jhansen on 5/1/2015.
 */
public class ArrayStringUtils
{
    public static boolean marked[];

    public static void Run( String in )
    {
        marked = new boolean[ in.length() ];
        Combinations( in, new StringBuilder(), 0 );

        /*char str[] = new char[ 100 ];

        for ( int i = 0; i < in.length(); i++ ) {
            str[i] = in.charAt(i);
        }*/

        //ReplaceSpaces( str, in.length() );
        //System.out.println( "Removed spaces str is:" + new String( str ) );
        //char str[] = in.toCharArray();
        //RemoveDuplicates2( str );
        //System.out.println( "Remove dups is:" + new String( str ) );
        //int n = StringToInt( in );
        //System.out.println( "StringToInt is:" + n );
        //int n = Integer.parseInt( in );
        //System.out.println( "IntToString is:" + IntToString( n ) );
        /*CheckAnagram( "thisisatest", "shitisatest" );
        CheckAnagram( "thisisatest", "sshitiatest" );
        CheckAnagram( "thisisatest", "shitistesat" );
        CheckAnagram( "thisisatest", "shitsiatest" );
        CheckAnagram( "thisisdtest", "shitsiatest" );
        CheckAnagram( "thisisatest", "shitssatest" );
        CheckAnagram( "thiseretest", "shitsiatest" );*/
        //System.out.println( "Reverse iterative of " + in + " is " + ReverseIterative( in ) );
        //System.out.println( "Reverse iterative of " + in + " is " + ReverseRecursive( in ) );
        //String args[] = in.split( " " );
        //System.out.println( "Remove chars of (in:" + args[0] + " remove:" + args[1] + ") is:" + RemoveChars( args[0], args[1] ) );
        //System.out.println( "args[1]:" + args[0] + " isRotation of args[1]:" + args[1] + " is: " +
        //                   IsRotation( args[0], args[1] ) );
    }

    public static String IntToString( int val )
    {
        boolean isNegative = false;
        char intStr[] = new char[ 24 ];
        int size = 0;

        if ( val < 0 ) {
            isNegative = true;
            val = -val;
        }

        do {
            intStr[ size++ ] = ( char ) ( ( val % 10 ) + '0' );
            val /= 10;
        } while ( val > 0 );

        if ( isNegative ) {
            intStr[ size++ ] = '-';
        }

        for ( int i = 0; i < ( size / 2 ); i++ ) {
            char temp = intStr[ i ];
            intStr[ i ] = intStr[ size - 1 - i ];
            intStr[ size - 1 - i ] = temp;
        }

        return new String( intStr );
    }

    public static int StringToInt( String input )
    {
        if ( input == null ) {
            return 0;
        }

        boolean isNegative = false;
        int index = 0;

        if ( input.charAt( 0 ) == '-' ) {
            isNegative = true;
            index++;
        }

        int count = 0;
        while ( index < input.length() ) {
            count = ( count * 10 ) + input.charAt( index++ ) - '0';
        }

        if ( isNegative ) {
            count = -count;
        }

        return count;
    }

    public static String RemoveChars( String input, String remove )
    {
        if ( input == null || remove == null ) {
            return input;
        }

        StringBuilder inputBuilder = new StringBuilder( input );

        Boolean charBuff[] = new Boolean[ 128 ];
        for ( int i = 0; i < charBuff.length; i++ ) {
            charBuff[ i ] = new Boolean( false );
        }

        for ( char c : remove.toCharArray() ) {
            charBuff[ c ] = true;
        }

        int dstCount = 0;
        for ( int i = 0; i < inputBuilder.length(); i++ ) {
            if ( !charBuff[ inputBuilder.charAt( i ) ] ) { // Only copy unskipped chars.
                inputBuilder.setCharAt( dstCount++, inputBuilder.charAt( i ) );
            }
        }

        return new String( inputBuilder.substring( 0, dstCount ) );
    }

    public static Character FindFirstNonrepeatChar( String in )
    {
        Object seenOnce = null;
        Object seenTwice = null;

        HashMap repeats = new HashMap<Character, Object>();

        for ( Character c : in.toCharArray() ) {
            if ( repeats.containsKey( c ) ) {
                repeats.put( c, seenTwice );
            }
            else {
                repeats.put( c, seenOnce );
            }
        }

        for ( Character c : in.toCharArray() ) {
            if ( repeats.get( c ) == seenOnce ) {
                return c;
            }
        }

        return null;
    }


    public static boolean IsUniqueChars( String in )
    {
        boolean chars[] = new boolean[ 256 ];

        for ( int i = 0; i < in.length(); i++ ) {

            if ( chars[ in.charAt( i ) ] ) {
                return false;
            }

            chars[ in.charAt( i ) ] = true;
        }

        return true;
    }

    public static boolean IsUniqueChars2( String in )
    {
        int chars = 0;

        for ( int i = 0; i < in.length(); i++ ) {
            int val = in.charAt( i ) - 'a';
            if ( ( chars & ( 1 << val ) ) > 1 ) {
                return false;
            }

            chars |= ( 1 << val );
        }

        return true;
    }

    public static String ReverseIterative( String str )
    {
        char input[] = str.toCharArray();
        char rev[] = new char[ input.length ];
        int start = 0;
        int end = input.length - 1;

        while ( end >= 0 ) {
            rev[ start++ ] = input[ end-- ];
        }

        return new String( rev );
    }

    public static String ReverseRecursive( String str )
    {
        if ( str == null || str.length() == 1 ) {
            return str;
        }

        char first = str.charAt( 0 );
        String reverse = ReverseRecursive( str.substring( 1 ) );
        reverse += first;

        return reverse;
    }

    public static void CheckAnagram( String str1, String str2 )
    {
        boolean isAnagram = IsAnagram3( str1, str2 );

        if ( isAnagram ) {
            System.out.println( "Str1:" + str1 + " Str2:" + str2 + " are anagrams." );
        }
        else {
            System.out.println( "Str1:" + str1 + " Str2:" + str2 + " are not anagrams." );
        }
    }

    public static boolean IsAnagram1( String str1, String str2 )
    {
        if ( str1.length() != str2.length() ) {
            return false;
        }

        int foundChars[] = new int[ 256 ];
        int num_completed = 0;
        int unique_chars = 0;

        for ( char c : str1.toCharArray() ) {
            if ( foundChars[ c ] == 0 ) {
                unique_chars++;
            }

            foundChars[ c ]++;
        }

        for ( char c : str2.toCharArray() ) {
            if ( foundChars[ c ] == 0 ) {
                return false;
            }
            --foundChars[ c ];

            if ( foundChars[ c ] == 0 ) {
                num_completed++;
            }

            if ( num_completed == unique_chars ) {
                return true;
            }
        }

        return false;
    }

    public static boolean IsAnagram2( String str1, String str2 )
    {
        if ( str1.length() != str2.length() ) {
            return false;
        }

        int foundChars[] = new int[ 256 ];

        for ( int i = 0; i < str1.length(); i++ ) {

            foundChars[ str1.charAt( i ) ]++;
            foundChars[ str2.charAt( i ) ]--;
        }

        // If all values are zero then we have an anagram.
        for ( int count : foundChars ) {
            if ( count != 0 )
                return false;
        }

        return true;
    }

    public static boolean IsAnagram3( String str1, String str2 )
    {
        if ( str1.length() != str2.length() ) {
            return false;
        }

        int foundChars1[] = new int[ 256 ];
        int foundChars2[] = new int[ 256 ];

        for ( int i = 0; i < str1.length(); i++ ) {

            foundChars1[ str1.charAt( i ) ]++;
            foundChars2[ str2.charAt( i ) ]++;
        }

        for ( int i = 0; i < foundChars1.length; i++ ) {
            if ( foundChars1[ i ] != foundChars2[ i ] ) {
                return false;
            }
        }

        return true;
    }

    public static void RemoveDuplicates( char[] str )
    {
        if ( str == null ) {
            return;
        }

        int len = str.length;
        if ( len < 2 ) {
            return;
        }

        boolean isDuplicate = false;
        int count = 0;

        for ( int i = 0; i < len; i++ ) {
            for ( int j = 0; j < i; j++ ) {
                if ( str[ i ] == str[ j ] ) {
                    isDuplicate = true;
                    break;
                }
            }

            if ( !isDuplicate ) {
                str[ count++ ] = str[ i ];
            }

            isDuplicate = false;
        }

        while ( count < str.length ) {
            str[ count++ ] = 0;
        }
    }

    public static void RemoveDuplicates2( char[] str )
    {
        if ( str == null ) {
            return;
        }

        int len = str.length;
        if ( len < 2 ) {
            return;
        }

        boolean buff[] = new boolean[ 128 ];

        int count = 0;
        for ( int i = 0; i < str.length; i++ ) {
            if ( !buff[ str[ i ] ] ) {
                str[ count++ ] = str[ i ];
                buff[ str[ i ] ] = true;
            }
        }

        while ( count < str.length ) {
            str[ count++ ] = 0;
        }
    }

    // Replace spaces with %20.
    public static void ReplaceSpaces( char[] str, int len )
    {
        if ( str == null ) {
            return;
        }

        int spaces = 0;

        for ( int i = 0; i < len; i++ ) {
            if ( str[ i ] == ' ' ) {
                spaces++;
            }
        }

        int newIndex = len + ( spaces * 2 ) - 1;
        str[ newIndex ] = '\0';

        for ( int i = len - 1; i >= 0; i-- ) {
            if ( str[ i ] == ' ' ) {
                str[ newIndex ] = '0';
                str[ newIndex - 1 ] = '2';
                str[ newIndex - 2 ] = '%';
                newIndex -= 3;
            }
            else {
                str[ newIndex-- ] = str[ i ];
            }
        }
    }

    public static boolean IsRotation( String s1, String s2 )
    {
        if ( s1.length() != s2.length() ) {
            return false;
        }

        String newStr = s1 + s1;

        if ( newStr.contains( s2 ) ) {
            return true;
        }

        return false;
    }

    public static void Permute( String in, StringBuilder perm )
    {
        if ( in == null ) {
            return;
        }

        if ( perm.length() == in.length() ) {
            System.out.println( perm + " " );
            return;
        }

        for ( int i = 0; i < in.length(); i++ ) {

            if ( marked[ i ] ) {
                continue;
            }

            perm.append( in.charAt( i ) );
            marked[ i ] = true;
            Permute( in, perm );
            marked[ i ] = false;
            perm.setLength( perm.length() - 1 );
        }
    }

    public static void Combinations( String in, StringBuilder out, int start )
    {
        for( int i = start; i < in.length() - 1; ++i ){
            out.append( in.charAt(i) );
            System.out.println( out );
            Combinations( in, out, i + 1);
            out.setLength( out.length() - 1 );
        }

        out.append( in.charAt( in.length() - 1 ) );
        System.out.println( out );
        out.setLength( out.length() - 1 );
    }
}