package testdstructures;

/**
 * Created by jhansen on 5/11/2015.
 */
public class ListStack<Item>
{
    private int size;
    private Node<Item> head;

    public static void Run()
    {
        ListStack stack = new ListStack();
        stack.Push( 1 );
        stack.Push( 2 );
        stack.Push( 3 );
        stack.Push( 4 );
        stack.Push( 5 );
        stack.Print();
        /*System.out.println( "stacksize:" + stack.Size() );
        System.out.println( "stackStack pop pop:" + stack.Pop() + " " + stack.Pop() );
        stack.Print();
        System.out.println( "stacksize:" + stack.Size() );*/
    }

    public ListStack()
    {
        size = 0;
        head = null;
    }

    public boolean IsEmpty()
    {
        return (size == 0);
    }

    public int Size()
    {
        return size;
    }

    public void Push( Item item )
    {
        Node node = new Node( item, head );
        head = node;
        ++size;
    }

    public Item Pop()
    {
        if ( IsEmpty() ) {
            return null;
        }

        Node<Item> temp = head;
        head = head.Next();
        --size;
        return temp.Data();
    }

    public Item Peek()
    {return head.Data();}

    public void Clear() {
        head = null;
    }

    public void Print()
    {
        String str = "";
        Node temp = head;
        while ( temp != null ) {
            str += temp.Data() + " ";
            temp = temp.Next();
        }
        System.out.println( "Printing ListStack:" + str );
    }
}
