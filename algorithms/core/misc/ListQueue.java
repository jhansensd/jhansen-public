package core.complex.misc;

/**
 * Created by jhansen on 5/23/2015.
 */
public class ListQueue<Item>
{
    ListNode<Item> front;
    ListNode<Item> back;
    int size;

    public ListQueue() {
        front = back = null;
        size = 0;
    }

    public boolean isEmpty() { return front == null; }
    public int size() { return size; }

    public Item peek() {
        if ( !isEmpty() )
            return null;

        return front.data();
    }

    public void enqueue( Item item ) {
        ListNode<Item> node = new ListNode<>( item, null );

        if ( isEmpty() )
            front = node;
        else
            back.next( node );

        back = node;
        ++size;
    }

    public Item dequeue() {
        if ( isEmpty() )
            return null;

        Item item = front.data();
        front = front.next();
        --size;

        if ( isEmpty() )
            back = null;


        return item;
    }

    public void print() {
        if ( isEmpty() )
            return;

        String str = "";

        for ( Item data : front ) {
            str += data + " ";
        }
        System.out.println( "Printing ListQueue:" + str );
    }
}
