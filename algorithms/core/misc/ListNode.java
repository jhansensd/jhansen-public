package core.complex.misc;

import java.util.Iterator;


/**
 * Created by jhansen on 5/23/2015.
 */
public class ListNode<Item> implements Iterable<Item> {

    private ListNode<Item> next;
    private Item data;

    public ListNode( Item data, ListNode<Item> next ) {
        this.data = data;
        this.next = next;
    }

    public ListNode<Item> next() { return next; }
    public Item data() { return data; }

    public void next( ListNode<Item> next ) { this.next = next; }
    public void data( Item data ) { this.data = data; }

    public Iterator<Item> iterator() {
        return new NodeIterator<Item>(this);
    }

    private class NodeIterator<Item> implements Iterator<Item>
    {
        private ListNode<Item> current;

        public NodeIterator( ListNode<Item> first ) { current = first; }

        public boolean hasNext() { return current != null; }
        public Item next() {
            Item item = current.data;
            current = current.next;
            return item;
        }
    }
}