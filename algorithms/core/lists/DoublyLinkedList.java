package core.complex.lists;


/**
 * Created by jhansen on 5/22/2015.
 */
public class DoublyLinkedList<Item>
{
    Node head;

    private class Node<Item> {

        Node next;
        Node prev;
        Item data;

        public Node( Item _data ) {
            data = _data;
            next = null;
            prev = null;
        }
    }

    public DoublyLinkedList() {
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

        if ( head != null ) {
            head.prev = tmp;
        }

        head = tmp;
    }

    public void insertAfter( Node node, Item data ) {
        Node tmp = new Node( data );

        if ( node == null ) {
            tmp.next = head;
            tmp.next.prev = tmp;
            head = tmp;
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
            head = node;
            return;
        }

        Node tmp = head;
        while ( tmp.next != null )
            tmp = tmp.next;

        tmp.next = node;
        node.prev = tmp;
    }

    public void deleteFirst() {
        if ( head != null ) {
            head = head.next;
            head.prev = null;
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
        if ( head == null )
            return;

        Node next = null;
        Node curr = head;

        while ( curr != null ) {

            next = curr.next;
            curr.next = curr.prev;
            curr.prev = curr;
            head = curr.prev;
            curr = next;
        }
    }

    public Node<Item> reverseRecur( Node node ) {
        if ( node == null || node.next == null )
            return node;

        Node second = node.next;
        if ( second.next != null )
            second.next.prev = null;
        node.next = null;
        Node newNode = reverseRecur( second );
        second.next = node;
        node.prev = second;
        head = newNode;
        return newNode;
    }

    public void print() {
        System.out.println( "DoublyLinkedList: " );
        Node tmp = head;
        while ( tmp != null ) {
            System.out.print( tmp.data + " " );
            tmp = tmp.next;
        }
        System.out.println();
    }
}
