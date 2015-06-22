package testdstructures;

/**
 * Created by jhansen on 5/10/2015.
 */
public class QueueStack  {
    private ArrayQueue q1;
    private ArrayQueue q2;

       public static void Run() {
        QueueStack stack = new QueueStack();
        stack.Push( 1 );
        stack.Push( 2 );
        stack.Push( 3 );
        stack.Push( 4 );
        stack.Push( 5 );
        stack.Print();
        System.out.println( "stacksize:" + stack.Size() );
        System.out.println( "stackStack pop pop:" + stack.Pop() + " " + stack.Pop() );
        stack.Print();
        System.out.println( "stacksize:" + stack.Size() );
    }

    public QueueStack() {
        q1 = new ArrayQueue( 10 );
        q2 = new ArrayQueue( 10 );
    }

    public int Size() {
        return q1.Size() + q2.Size();
    }

    public void Push(int value) {
        q1.Enqueue( value );
    }

    // Keep dequeing until only one element left, and then
    // return that item which is the top item.
    public int Pop() {
        int item = -1;
        while( !q1.IsEmpty() ) {
            item = q1.Dequeue();

            if ( !q1.IsEmpty() ) {
                q2.Enqueue( item );
            }
        }

        MoveOver();

        return item;
    }

    public int Peek()  {
        int item = -1;
        while ( !q1.IsEmpty() ) {
            item = q1.Dequeue();
            q2.Enqueue( item );
        }

        MoveOver();

        return item;
    }

    public void Print() {
        String printStr = "";
        System.out.println( "Printing QueueStack stack." );

        while ( !q1.IsEmpty() ) {
            int val = q1.Dequeue();
            printStr = val + " " + printStr;
            q2.Enqueue( val );
        }

        MoveOver();

        System.out.println( printStr );
    }

    private void MoveOver() {
        // Move everything back to q1 now.
        while( !q2.IsEmpty() ) {
            q1.Enqueue( q2.Dequeue() );
        }
    }
}
