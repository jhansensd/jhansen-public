package core.complex.lists;


/**
 * Created by jhansen on 5/22/2015.
 */
public class DoublyTailLinkedList<Item>
{
    Node head;
    Node tail;

    private class Node {

        Node next;
        Node prev;
        Item data;

        public Node( Item _data ) {
            data = _data;
            next = null;
            prev = null;
        }
    }

    public DoublyTailLinkedList() {
        head = null;
        tail = null;
    }

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

        if ( head != null )
            head.prev = tmp;

        if ( tail == null ) {
            tail = tmp;
        } else if ( tail.prev == null ) {
            tail.prev = tmp;
        }

        head = tmp;
    }

    public void insertAfter( Node node, Item data ) {
        Node tmp = new Node( data );

        if ( node == tail ) {
            tail = tmp;
        }

        if ( node == null ) {
            tmp.next = head;
            tmp.next.prev = tmp;
            head = tmp;

            if ( tail == null )
                tail = head;
            return;
        }

        Node next = node.next;
        node.next = tmp;
        tmp.next = next;
        next.prev = tmp;
        tmp.prev = node;
    }

    public void insertLast( Item data ) {
        Node node = new Node( data );

        if ( head == null ) {
            head = tail = node;
            return;
        }

        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    public void deleteFirst() {
        if ( head != null ) {
            head = head.next;

            if ( head == null )
                tail = null;
            else
                head.prev = null;
        }
    }

    public void deleteLast() {
        if ( tail != null ) {
            if ( tail.prev == null )
                tail = head = null;
            else {
                tail.prev.next = null;
                tail = tail.prev;
            }
        }
    }

    public void deleteNode( Node node ) {
        if ( node == tail ) {
            tail = tail.prev;
            tail.next = null;
        }

        if ( node.prev == null ) {
            head = head.next;
            head.prev = null;
            return;
        }

        node.prev.next = node.next;

        if ( node.next != null )
            node.next.prev = node.prev;
    }

    public void reverseIter() {

    }

    public void reverseRecur() {

    }

    public void print() {
        System.out.println( "DoublyTailLinkedList: " );
        Node tmp = tail;
        String str = "";
        while ( tmp != null ) {
            str = tmp.data + " " + str;
            tmp = tmp.prev;
        }
        System.out.println( str );
    }
}
