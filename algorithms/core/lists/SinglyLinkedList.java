package core.complex.lists;


import java.util.Iterator;

/**
 * Created by jhansen on 5/22/2015.
 */
public class SinglyLinkedList<Item> implements Iterable<Item>
{
    Node head;

    private class Node<Item> {

        Node next;
        Item data;

        public Node( Item _data ) {
            data = _data;
            next = null;
        }
    }

    public SinglyLinkedList() {
        head = null;
    }

    public Node head() { return head; }

    public Node search( Item data ) {
        Node tmp = head;
        while ( tmp != null ) {
            if ( tmp.data == data )
                return tmp;

            tmp = tmp.next;
        }
        return null;
    }

    public void insertFirst( Item data ) {
        Node tmp = new Node( data );
        tmp.next = head;
        head = tmp;
    }

    public void insertAfter( Node node, Item data ) {
        Node tmp = new Node( data );

        if ( node == null ) {
            tmp.next = head;
            head = tmp;
            return;
        }

        Node next = node.next;
        node.next = tmp;
        tmp.next = next;
    }

    public void insertLast( Item data ) {
        Node node = new Node( data );

        if ( head == null ) {
            head = node;
            return;
        }

        Node tmp = head;
        while ( tmp.next != null )
            tmp = tmp.next;

        tmp.next = node;
    }

    public void deleteFirst() {
        if ( head != null ) {
            head = head.next;
        }
    }

    public void deleteLast() {
        if ( head != null ) {
            Node tmp = head;
            while ( tmp.next != null ) {
                if ( tmp.next.next == null ) {
                    tmp.next = null;
                    return;
                }
                tmp = tmp.next;
            }
        }
    }

    public void deleteNode( Node node ) {
        if ( head != null ) {
            Node tmp = head;
            if ( tmp == node ) {
                head = tmp.next;
                return;
            }

            while ( tmp.next != null ) {
                if ( tmp.next == node )
                    tmp.next = node.next;

                tmp = tmp.next;
            }
        }
    }

    public void reverseIter() {
        if ( head == null )
            return;

        Node next = null;
        Node prev = null;
        Node curr = head;

        while ( curr != null ) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head = prev;
    }

    public Node<Item> reverseRecur( Node node ) {
        if ( node == null || node.next == null )
            return node;

        Node second = node.next;
        node.next = null;
        Node newNode = reverseRecur( second );
        second.next = node;
        head = newNode;
        return newNode;
    }

    public Iterator<Item> iterator() { return new ListIterator<Item>( head ); }

    private class ListIterator<Item> implements Iterator<Item> {

        private Node<Item> current;
        public ListIterator( Node first ) { current = first; }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if ( hasNext() ) {
                Item data = current.data;
                current = current.next;
                return data;
            }
            return null;
        }
    }

    public void print() {
        System.out.println( "SinglyLinkedList: " );
        for ( Item data : this )
            System.out.print( data + " " );

        System.out.println();
    }
}
