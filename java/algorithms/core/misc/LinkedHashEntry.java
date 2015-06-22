package core.complex.misc;

import java.util.Iterator;

public class LinkedHashEntry<Key, Value> implements Iterable<LinkedHashEntry>
{
    private Key key;
    private Value value;
    LinkedHashEntry<Key, Value> next;

    LinkedHashEntry( Key key, Value value ) {
        this.key = key;
        this.value = value;
    }

    public Key Key() { return key; }
    public Value Value() { return value; }

    public void Key( Key key ) { this.key = key; }
    public void Value( Value value ) { this.value = value; }

    public void append( Key key, Value value ) {
        LinkedHashEntry<Key, Value> entry = this;
        while ( entry.next != null ) {
            entry = entry.next;
        }
        entry.next = new LinkedHashEntry<>(key, value);
    }

    public LinkedHashEntry remove( Key key ) {
        LinkedHashEntry<Key, Value> entry = this;

        if ( entry.key.equals( key ) ) {
            return entry;
        }

        while ( entry.next != null ) {
            if ( entry.next.Key() == key ) {
                LinkedHashEntry next = entry.next;
                entry.next = entry.next.next;
                return next;
            }
            entry = entry.next;
        }

        return null;
    }

    public Iterator<LinkedHashEntry> iterator() { return new HashEntryIterator( this ); }

    public class HashEntryIterator implements Iterator<LinkedHashEntry> {
        private LinkedHashEntry<Key, Value> current;

        public HashEntryIterator( LinkedHashEntry<Key, Value> current ) { this.current = current; }

        public boolean hasNext()  { return current != null; }
        public LinkedHashEntry next() {
            if ( !hasNext() )
                return null;

            LinkedHashEntry entry = current;
            current = current.next;
            return entry;
        }
    }
}