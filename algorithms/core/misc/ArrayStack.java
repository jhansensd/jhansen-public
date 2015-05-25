package core.complex.misc;

/**
 * Created by jhansen on 5/23/2015.
 */
public class ArrayStack<Item>
{
    private int top;
    private Item elements[];

    public ArrayStack( int max ) {
        top = -1;
        elements = (Item[])new Object[ max ];
    }

    public boolean isEmpty() { return top == -1; }
    public boolean isFull() { return (top + 1) == elements.length; }
    public int Size() { return top + 1; }

    public void push( Item item ) {
        if ( isFull() )
            return;

        elements[++top] = item;
    }

    public Item pop() {
        if ( isEmpty() )
            return null;

        return elements[top--];
    }

    public Item peek() {
        if ( isEmpty() )
            return null;

        return elements[ top ];
    }

    public void print() {
        if ( isEmpty() )
            return;

        String str = "";

        for ( int i = 0; i < top + 1; i++ ) {
            str += elements[i] + " ";
        }
        System.out.println( "Printing ArrayStack:" + str );
    }
}
