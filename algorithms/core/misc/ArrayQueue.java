package core.complex.misc;

/**
 * Created by jhansen on 5/23/2015.
 */
public class ArrayQueue<Item>
{
    int front;
    int back;
    int size;
    Item elements[];

    public ArrayQueue( int max ) {
        size = 0;
        front = back = -1;
        elements = (Item[])new Object[ max ];
    }

    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == elements.length; }
    public int size() { return size; }

    public void enqueue( Item data ) {
        if ( isFull() )
            return;

        if ( isEmpty() ) {
            ++front;
            ++back;
        }
        else {
            back = (back + 1) % elements.length;
        }

        ++size;
        elements[ back ] = data;
    }

    public Item dequeue()  {
        if ( isEmpty() )
            return null;

        Item item = elements[front];
        if ( front == back )
            front = back = -1;
        else
            front = (front + 1) % elements.length;

        --size;
        return item;
    }

    public Item peek()  {
        if ( isEmpty() )
            return null;

        return elements[front];
    }

    public void print() {
        if ( isEmpty() )
            return;

        String str = "";

        int i = front;
        while( front != back ) {
            str += elements[ i ] + " ";
            front = ( front + 1 ) % elements.length;
        }

        str += elements[ back ] + " ";
        System.out.println( "Printing ArrayQueue:" + str );
    }
}
