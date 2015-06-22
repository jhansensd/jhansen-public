package core.complex.lists;


/**
 * Created by jhansen on 5/22/2015.
 */
public class SinglyTailLinkedList<Item>
{
    Node head;
    Node tail;

    class Node {

        Node next;
        Item data;

        public Node( Item _data ) {
            data = _data;
            next = null;
        }
    }

    public SinglyTailLinkedList() {
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

        if ( tail == null )
            tail = tmp;

        head = tmp;
    }

    public void insertAfter( Node node, Item data ) {
        Node tmp = new Node( data );

        if ( node == tail ) {
            tail = tmp;
        }

        if ( node == null ) {
            tmp.next = head;
            head = tmp;

            if ( tail == null )
                tail = head;

            return;
        }

        Node next = node.next;
        node.next = tmp;
        tmp.next = next;
    }

    public void insertLast( Item data ) {
        Node node = new Node( data );

        if ( head == null ) {
            head = tail = node;
            return;
        }

        tail.next = node;
        tail = node;
    }

    public void deleteFirst() {
        if ( head != null ) {
            head = head.next;

            if ( head == null )
                tail = null;
        }
    }

    public void deleteLast() {
        if ( head != null ) {
            Node tmp = head;

            if ( tmp.next == null ) {
                head = tail = null;
            }

            while ( tmp.next != tail ) {
                tmp = tmp.next;
            }

            tmp.next = null;
            tail = tmp;
        }
    }

    public void deleteNode( Node node ) {
        if ( head != null ) {
            Node tmp = head;
            if ( tmp == node ) {
                head = tmp.next;

                if ( head == null )
                    tail = null;

                return;
            }

            while ( tmp != null ) {
                if ( tmp.next == node ) {
                    tmp.next = node.next;

                    if ( tmp.next == null ) {
                        tail = tmp;
                    }

                    return;
                }
                tmp = tmp.next;
            }
        }
    }

    public void reverseIter() {

    }

    public void reverseRecur() {

    }

    public void print() {
        System.out.println( "SinglyTailLinkedList: " );
        Node tmp = head;
        while ( tmp != null ) {
            System.out.print( tmp.data + " " );
            tmp = tmp.next;
        }
        System.out.println();
    }
}

