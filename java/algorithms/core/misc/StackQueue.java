package testdstructures;

/**
 * Created by jhansen on 5/10/2015.
 */
public class StackQueue  {
    private ArrayStack s1;
    private ArrayStack s2;

    public static void Run() {
        StackQueue queue = new StackQueue();
        queue.Enqueue( 1 );
        queue.Enqueue( 2 );
        queue.Enqueue( 3 );
        queue.Enqueue( 4 );
        queue.Enqueue( 5 );
        queue.Print();
        System.out.println( "Queuesize:" + queue.Size() );
        System.out.println( "StackQueue dequeue dequeue:" + queue.Dequeue() + " " + queue.Dequeue() );
        queue.Print();
        System.out.println( "Queuesize:" + queue.Size() );
    }

    public StackQueue() {
        s1 = new ArrayStack( 10, false );
        s2 = new ArrayStack( 10, false );
    }

    public int Size() {
        return s1.Size() + s2.Size();
    }

    public void Enqueue(int value) {
        s1.Push( value );
    }

    public int Peek() {
        while ( !s1.IsEmpty() ) {
            s2.Push( s1.Pop() );
        }

        return s2.Peek();
    }

    public int Dequeue() {
        while ( !s1.IsEmpty() ) {
            s2.Push( s1.Pop() );
        }

        return s2.Pop();
    }

    public void Print() {
        String printStr = "";
        System.out.println( "Printing StackQueue queue." );

        if ( !s1.IsEmpty() ) {
            while ( !s1.IsEmpty() ) {
                int val = s1.Pop();
                printStr += val + " ";
                s2.Push( val );
            }
        } else {
            while ( !s2.IsEmpty() ) {
                int val = s2.Pop();
                printStr = ( val + " " ) + printStr;
                s1.Push( val );
            }
        }

        System.out.println( printStr );
    }
}
