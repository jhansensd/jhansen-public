package core.complex.misc;

import java.util.Iterator;


/**
 * Created by jhansen on 5/23/2015.
 */
public class LinkedHash<Key, Value> implements Iterable<LinkedHashEntry>
{
    private int MAX_TABLE_SIZE;
    private LinkedHashEntry<Key, Value> table[];

    public Iterator<LinkedHashEntry> iterator() { return new LinkedHashIterator(); }

    private class LinkedHashIterator implements Iterator<LinkedHashEntry> {

        private int index;
        private LinkedHashEntry<Key, Value> current;

        public LinkedHashIterator() {
            current = table[0];
            index = 0;
        }

        public boolean hasNext() { return ( index != MAX_TABLE_SIZE && table[index] != null ); }

        public LinkedHashEntry next() {
            if ( !hasNext() )
                return null;

            if ( current == null )
                current = table[index];

            Iterator<LinkedHashEntry> iter = current.iterator();

            if ( iter.hasNext() ) {
                LinkedHashEntry entry = iter.next();
                current = entry;
                return entry;
            }
            else {
                LinkedHashEntry entry = current;
                ++index;

                if ( index == MAX_TABLE_SIZE )
                    current = null;
                else
                    current = table[index];

                return entry;
            }
        }
    }

    public LinkedHash( int max ) {
        MAX_TABLE_SIZE = max;
        table = new LinkedHashEntry[ MAX_TABLE_SIZE ];
    }

    public void put( Key key, Value value ) {

        int hash = key.hashCode() % MAX_TABLE_SIZE;

        if ( table[hash] == null ) {
            table[ hash ] = new LinkedHashEntry<>( key, value );
            return;
        }

        for ( LinkedHashEntry<Key, Value> entry : table[hash] ) {
            if ( entry.Key().equals( key ) ) {
                entry.Value( value );
                return;
            }
        }

        table[hash].append( key, value );
    }

    public Value get( Key key ) {
        int hash = key.hashCode() % MAX_TABLE_SIZE;

        if ( table[hash] == null )
            return null;

        LinkedHashEntry<Key, Value> collided = table[hash];

        for ( LinkedHashEntry<Key, Value> entry : table[hash] ) {
            if ( entry.Key().equals( key ) ) {
                return collided.Value();
            }
        }

        return null;
    }

    public void remove( Key key ) {
        int hash = key.hashCode() % MAX_TABLE_SIZE;

        if ( table[hash] == null )
            return;

        LinkedHashEntry<Key, Value> collided = table[hash];

        for ( LinkedHashEntry<Key, Value> entry : table[hash] ) {
            if ( entry.Key().equals( key ) ) {
                if ( collided.remove( key ) == table[hash] )
                    table[hash] = collided.next;
            }
        }
    }

    public void print() {

        System.out.println( "Outputting hash table values." );
        LinkedHashEntry<Key, Value> entry = null;
        for ( int i = 0; i < table.length; i++ ) {
            if ( table[i] == null )
                continue;

            Iterator<LinkedHashEntry> iter = table[i].iterator();
            while ( iter.hasNext() ) {
                entry = iter.next();
                System.out.println( "Hash (Key:" + entry.Key() + " Value:" + entry.Value() + ")" );
            }
        }
    }
}
