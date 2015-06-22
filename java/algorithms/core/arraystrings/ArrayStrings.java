package core.complex.arraystrings;

/**
 * Created by jhansen on 5/23/2015.
 */
public class ArrayStrings
{
    static final int ASCII_COUNT = 256;

    public static int BinarySearchIter( int arr[], int val, int low, int high ) {
        int mid;
        while ( low <= high ) {
            mid = ( low + high ) / 2;
            if ( val == arr[mid] )
                return mid;
            else if ( val < arr[mid] )
                high = mid - 1;
            else if ( val > arr[mid] )
                low = mid + 1;
        }

        return -1;
    }

    public static int BinarySearchRecur( int arr[], int val, int low, int high )
    {
        if ( low > high )
            return -1;

        int mid = ( low + high ) / 2;

        if ( val == arr[mid] )
            return mid;
        else if ( val < arr[mid] )
            return BinarySearchRecur( arr, val, low, mid - 1 );
        else if ( val > arr[mid] )
            return BinarySearchRecur( arr, val, mid + 1, high );

        return -1;
    }

    public static boolean isAnagram1( String s1, String s2 ) {
        if ( s1.length() != s2.length() )
            return false;

        int count[] = new int[ ASCII_COUNT ];

        for ( char c : s1.toCharArray() ) {
            ++count[c];
        }

        for ( char c : s2.toCharArray() ) {
            if ( --count[c] < 0 )
                return false;
        }

        return true;
    }

    public static boolean isAnagram2( String s1, String s2 ) {
        if ( s1.length() != s2.length() )
            return false;

        int count[] = new int[ ASCII_COUNT ];

        for ( int i = 0; i < s1.length(); i++ ) {
            ++count[ s1.charAt( i ) ];
            --count[ s2.charAt( i ) ];
        }

        for ( int i = 0; i < s1.length(); i++ ) {
            if ( count[ s1.charAt( i ) ] != 0 )
                return false;
        }

        return true;
    }
}