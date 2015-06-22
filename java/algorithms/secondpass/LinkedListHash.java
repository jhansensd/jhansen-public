package practiceruns;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedHashEntry<Data>
{
    String key;
    Data value;
    LinkedHashEntry<Data> next;

    LinkedHashEntry( String key, Data value ) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

/**
 * Created by jhansen on 5/16/2015.
 */
public class LinkedListHash<Data> implements Iterable
{
    private int MAX_TABLE_SIZE;
    private int size;
    private LinkedHashEntry<Data> table[];

    public Iterator<LinkedHashEntry> iterator()
    { return new ListHashIterator(); }

    private class ListHashIterator implements Iterator<LinkedHashEntry> {

        private int index;
        private LinkedHashEntry<Data> current;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public ListHashIterator() {
            current = table[0];
            index = 0;
        }

        public boolean hasNext()  {
            if ( index == ( MAX_TABLE_SIZE - 1 ) && current.next == null ) {
                return false;
            }

            return true;
        }

        public void remove() {

            LinkedHashEntry iter = table[index];

            while ( iter.next != current ) {
                iter = iter.next;
            }

            size--;
            current = iter;
            iter.next = current.next;
        }

        public LinkedHashEntry next() {
            if ( !hasNext() ) throw new NoSuchElementException();

            LinkedHashEntry entry;

            if ( current.next == null ) {
                entry = table[index];
                current = table[++index];
                return entry;
            }

            entry = current;
            current = current.next;
            return entry;
        }
    }



    public static void Run() {

        String key1 = "key1";
        String key2 = "key2";
        String key3 = "key3";

        System.out.println( "Creating linked list hash." );
        LinkedListHash<Integer> hash = new LinkedListHash<>( 10 );
        hash.Put( key1, new Integer( 244 ) );
        hash.Put( key2, new Integer( 13 ) );
        hash.Put( key3, new Integer( 94 ) );

        System.out.println( "Hash retrieval " + key1 + ": " + hash.Get( key1 ) );
        System.out.println( "Hash retrieval " + key2 + ": " + hash.Get( key2 ) );
        System.out.println( "Hash retrieval " + key3 + ": " + hash.Get( key3 ) );
    }


    public LinkedListHash( int max ) {
        size = 0;
        MAX_TABLE_SIZE = max;
        table = new LinkedHashEntry[ MAX_TABLE_SIZE ];
    }

    public boolean IsFull() {
        return size == MAX_TABLE_SIZE;
    }

    public int Size() { return size; }

    public void Clear() {
        for ( int i = 0; i < MAX_TABLE_SIZE; i++ ) {
            table[i] = null;
        }
    }

    public Data Get( String key ) {
        int hash = hashFunc( key ) % MAX_TABLE_SIZE;
        if ( table[hash] == null ) {
            return null;
        }

        LinkedHashEntry<Data> entry = table[hash];
        while( entry != null && !entry.key.equals( key ) ) {
            entry = entry.next;
        }

        if ( entry == null ) {
            return null;
        }

        return entry.value;
    }

    public void Put( String key, Data data ) {

        if ( IsFull() ) {
            System.out.println( "Can't put key:" + key +
                                " because hash is full: maxsize:" + MAX_TABLE_SIZE + "!" );
            return;
        }

        int hash = hashFunc( key ) % MAX_TABLE_SIZE;
        LinkedHashEntry<Data> entry = new LinkedHashEntry<>( key, data );

        if ( table[hash] == null ) {
            table[hash] = entry;
        } else {
            LinkedHashEntry iter = table[hash];
            while ( iter.next != null ) {
               iter = iter.next;
            }

            iter.next = entry;
        }
    }

    private int hashFunc( String key ) {
        int hashVal = key.hashCode();
        hashVal %= MAX_TABLE_SIZE;
        if ( hashVal < 0 ) {
            hashVal += MAX_TABLE_SIZE;
        }

        return hashVal;
    }

}
