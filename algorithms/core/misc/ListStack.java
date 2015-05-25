package core.complex.misc;

/**
 * Created by jhansen on 5/23/2015.
 */
public class ListStack<Item>
{
    private int size;
    private ListNode<Item> head;

    public ListStack() {
        size = 0;
        head = null;
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public void push( Item item ) {
        ListNode<Item> node = new ListNode( item, head );
        head = node;
        ++size;
    }

    public Item pop() {
        if ( isEmpty() )
            return null;

        Item item = head.data();
        head = head.next();
        --size;
        return item;
    }

    public Item peek() {
        if ( isEmpty() )
            return null;

        return head.data();
    }

    public void print() {
        if ( isEmpty() )
            return;

        String str = "";

        for ( Item data : head ) {
            str += data + " ";
        }
        System.out.println( "Printing ListStack:" + str );
    }
}
