package testdstructures;

/**
 * Created by jhansen on 5/13/2015.
 */
public class ArrayQueue
{
    int m_front;
    int m_back;
    int m_size;
    int m_elements[];

    public static void Run()
    {
        ArrayQueue myQueue = new ArrayQueue( 100 );
        myQueue.Enqueue( 1 );
        myQueue.Enqueue( 2 );
        myQueue.Enqueue( 3 );
        myQueue.Enqueue( 4 );
        myQueue.Enqueue( 5 );
        myQueue.Print();
        myQueue.Dequeue();
        int val = myQueue.Dequeue();
        myQueue.Print();
        System.out.println( "Dequeued val is:" + val );
    }

    public ArrayQueue( int max )
    {
        m_elements = new int[ max ];
        m_size = 0;
        m_front = m_back = -1;
    }

    public boolean IsEmpty() { return m_size == 0; }
    public boolean IsFull() { return m_size == m_elements.length; }
    public int Size() { return m_size; }
    public int Front()
    {
        if ( IsEmpty() ) {
            return -1;
        }

        return m_elements[ m_front ];
    }

    public void Enqueue( int val )
    {
        if ( IsFull() ) {
            System.out.println( "Can not enqueue a full stack!" );
        }

        if ( IsEmpty() ) {
            m_front++;
            m_back++;
        } else {
            m_back = (m_back+1)% m_elements.length;
        }

        m_elements[ m_back ] = val;
        m_size++;
    }

    public int Dequeue()
    {
        if ( IsEmpty() ) {
            return -1;
        }

        int val = m_elements[ m_front ];
        if ( m_front == m_back ) {
            m_front = m_back = -1;
        } else {
            m_front = (m_front + 1) % m_elements.length;
        }

        m_size--;

        return val;
    }

    public void Clear()
    {
        for ( int i = 0; i < m_elements.length; i++ ) {
            m_elements[ i ] = 0;
        }

        m_size = 0;
        m_front = m_back = -1;
    }

    public void Print()
    {
        System.out.println( "\nPrinting array queue data:" );

        if ( m_size == 0 ) {
            return;
        }

        int i = m_front;
        while ( i != m_back ) {
            System.out.print( m_elements[ i ] + " " );
            i = (i + 1) % m_elements.length;
        }

        System.out.print( m_elements[ m_back ] + " " ); // Last element.
        System.out.println( "\nDone printing array queue data." );
    }
}
