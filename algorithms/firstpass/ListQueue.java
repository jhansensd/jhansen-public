package testdstructures;

import java.util.Iterator;

/**
 * Created by jhansen on 5/13/2015.
 */
public class ListQueue<Item> implements Iterable<Item>
{
    Node<Item> m_front;
    Node<Item> m_back;
    int m_size;
    int m_max;

    public static void Run()
    {
        ListQueue<Integer> myQueue = new ListQueue<Integer>( 100 );
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

    public ListQueue( int max )
    {
        m_size = 0;
        m_max = max;
    }

    public boolean IsEmpty() { return m_size == 0; }
    public boolean IsFull() { return m_size == m_max; }
    public int Size() { return m_size; }
    public Item Front()
    {
        if ( IsEmpty() ) {
            return null;
        }

        return m_front.Data();
    }

    public void Enqueue( int val )
    {
        if ( IsFull() ) {
            System.out.println( "Can not enqueue a full stack!" );
        }

        Node oldBack = m_back;
        m_back = new Node( val, null );

        if ( !IsEmpty() ) {
            oldBack.SetNext( m_back );
        } else {
            m_front = m_back;
        }

        m_size++;
    }

    public Item Dequeue()
    {
        if ( IsEmpty() ) {
            return null;
        }

        Item val = m_front.Data();
        m_front = m_front.Next();
        m_size--;

        if ( IsEmpty() ) {
            m_back = null;
        }

        return val;
    }

    public void Clear()
    {
        m_size = 0;
        m_front = m_back = null;
    }

    public void Print()
    {
        System.out.println( "\nPrinting array queue data:" );

        if ( m_size == 0 ) {
            return;
        }

        Node node = m_front;
        while ( node != m_back ) {
            System.out.print( node.IntData() + " " );
            node = node.Next();
        }

        System.out.print( node.IntData() + " " ); // Last element.
        System.out.println( "\nDone printing array queue data." );
    }

    public Iterator<Item> iterator() { return new ListQueueIterator<Item>(m_front); }

    private class ListQueueIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListQueueIterator( Node<Item> front ) { current = front; }
        public boolean hasNext() { return current != null; }
        public void remove()
        {
            Node prev = m_front;
            while ( prev.Next() != current ) {
                prev = prev.Next();
            }

            prev.SetNext( current.Next() );
            current = current.Next();
        }

        public Item next() {
            if ( !hasNext() ) {
                return null;
            }
            Item item = current.Data();
            current = current.Next();
            return item;
        }

    }
}
