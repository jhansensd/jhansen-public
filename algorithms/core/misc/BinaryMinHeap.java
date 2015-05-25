package core.complex.misc;

import java.util.Comparator;

/**
 * Created by jhansen on 5/23/2015.
 */
public class BinaryMinHeap<Key>
{
    private Key             elements[];
    private int             size;
    private Comparator<Key> comparator;

    public BinaryMinHeap( int max ) {
        elements = ( Key[] ) new Object[ max + 1 ];
        size = 0;
    }

    public BinaryMinHeap( Key keys[] ) {
        size = keys.length;
        elements = (Key[])new Object[keys.length + 1];
        for ( int i = 0; i < size; i++ )
            elements[ i + 1 ] = keys[ i ];

        for ( int k = size/2; k >= 1; k-- )
            sink( k );

        assert isMinHeap(1);
    }

    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == elements.length; }
    public int size() { return size; }

    public Key min() {
        if ( !isEmpty() )
            return elements[1];

        return null;
    }

    public void insert( Key x ) {
        if ( isFull() )
            return;

        elements[++size] = x;
        swim(size);
        assert isMinHeap(1);
    }

    public Key delMin() {
        if ( isEmpty() )
            return null;

        swap( 1, size );
        Key min = elements[ size-- ];
        sink( 1 );
        elements[size + 1] = null;

        assert isMinHeap(1);
        return min;
    }

    private void swim( int k ) {
        while ( k > 1 && greater( k/2, k ) ) {
            swap( k, k/2 );
            k = k/2;
        }
    }

    private void sink( int k ) {
        while ( 2*k <= size ) {
            int j = 2*k;
            if ( j < size && greater(j, j+1) ) j++;
            if ( !greater(k, j)) break;
            swap(k, j);
            k = j;
        }
    }

    public void swap( int i, int j ) {
        Key temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    private boolean greater( int i, int j ) {
        if (comparator == null) {
            return ( ( Comparable<Key>) elements[i] ).compareTo( elements[j] ) > 0;
        }
        else {
            return comparator.compare( elements[i], elements[j] ) > 0;
        }
    }

    private boolean isMinHeap(int k) {
        if (k > size) return true;
        int left = 2*k, right = 2*k + 1;
        if (left <= size && greater(k, left))  return false;
        if (right <= size && greater(k, right)) return false;
        return isMinHeap(left) && isMinHeap(right);
    }

    public void print() {
        if ( isEmpty() )
            return;

        System.out.println( "Printing Min Binary Heap." );

        for ( int i = 1; i <= size; i++ ) {
            System.out.print( elements[ i ] + " " );
        }
        System.out.println();
    }
}
