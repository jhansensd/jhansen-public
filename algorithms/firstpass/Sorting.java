package testdstructures;

/**
 * Created by jhansen on 4/27/2015.
 */
public class Sorting
{
    public void Swap( int arr[], int index1, int index2 )
    {
        if ( index1 != index2 ) {
            int temp = arr[ index1 ];
            arr[ index2 ] = arr[ index1 ];
            arr[ index1 ] = temp;
        }
    }

    public void InsertionSort( int arr[] )
    {
        int i, j;
        System.out.println( "Insertion Sort:" );
        PrintArray( arr );

        for ( i = 1; i < arr.length; i++ ) {
            j = i;
            while ( j > 0 && ( arr[ j ] < arr[ j - 1 ] ) ) {
                Swap( arr, j, j - 1 );
                --j;
            }
        }

        PrintArray( arr );
    }

    public void SelectionSort( int arr[] )
    {
        int min = 0;
        System.out.println( "Selection Sort:" );
        PrintArray( arr );

        for ( int i = 0; i < arr.length; i++ )
        {
            min = i;
            for ( int j = i + 1; j < arr.length; j++ ) {
                if ( arr[ j ] < arr[min] ) {
                    min = j;
                }
            }

            Swap( arr, i, min );
        }

        PrintArray( arr );
    }

    public void QuickSort( int arr[], int low, int high )
    {
        System.out.println( "QuickSort:" );
        PrintArray( arr );

        int p;

        if ( low < high )
        {
            p = Partition( arr, low, high );
            QuickSort( arr, low, p - 1 );
            QuickSort( arr, p + 1, high);
        }

        PrintArray( arr );
    }

    public int Partition( int arr[], int low, int high )
    {
        int pivot = arr[(low + high)/2];

        while ( low <= high ) {
            while ( arr[ low ] < pivot ) low++;
            while ( arr[ high ] > pivot ) high--;

            if ( low < high ) {
                Swap( arr, low, high );
                low++;
                high++;
            }
        }

        return low;
    }

    public void MergeSort( int arr[] )
    {}

    public void HeapSort( int arr[] )
    {}

    public void RadixSort( int arr[] )
    {}

    public void BucketSort( int arr[] )
    {}

    public void BubbleSort( int arr[] )
    {
        boolean swapped = true;
        System.out.println( "Bubble Sort:" );
        PrintArray( arr );

        while ( swapped ) {
            swapped = false;
            for ( int i = 0; i < arr.length - 1; i++ ) {
                if ( arr[ i ] > arr[ i + 1 ] ) {
                    int temp = arr[ i ];
                    arr[ i ] = arr[ i + 1 ];
                    arr[ i + 1 ] = temp;
                    swapped = true;
                }
            }
        }

        PrintArray( arr );
    }

    public void PrintArray( int arr[] )
    {
        System.out.print( "Array is:" );

        for ( int i : arr )
        {
            System.out.print( i + " " );
        }

        System.out.println();
    }

}
