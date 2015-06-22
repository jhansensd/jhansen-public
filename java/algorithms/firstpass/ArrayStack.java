package testdstructures;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Created by hansj058 on 5/1/2015.
 */
public class ArrayStack
{
    boolean m_resizeable;
    int m_capacity;
    int m_top;
    int m_elements[];

    static public void Run()
    {
        ArrayStack myStack = CreateRandomStack( 20, 12 , false );
        myStack.Print();
        System.out.println( "Stack Capacity:" + myStack.Capacity() );

        for( int i = 0; i < 15; i++ ) {
        //    myStack.Pop();
        }

        System.out.println( "Stack Capacity:" + myStack.Capacity() );


        System.out.println( "Size is " + myStack.Size() );
        System.out.println( "Top top is:" + myStack.Pop() + " " + myStack.Pop() );
        myStack.Print();
        System.out.println( "Size is " + myStack.Size() );
        myStack.Push( 10 );
        myStack.Print();
        myStack.Push( 12 );
        myStack.Print();
        System.out.println( "Size is " + myStack.Size() );
    }

    public ArrayStack( int capacity, boolean resizeable )
    {
        m_resizeable = resizeable;
        m_capacity = capacity;
        m_elements = new int[ capacity ];
        m_top = -1;
    }

    private static Random randNum = new Random( 10000 );
    public static ArrayStack CreateRandomStack( int count, int capacity, boolean resizeable )
    {
        ArrayStack stack = new ArrayStack( capacity, resizeable );
        for ( int i = 0; i < count; i++ ) {
            stack.Push( randNum.nextInt( 1000 ) );
        }

        return stack;
    }

    public int Capacity()
    {
        return m_capacity;
    }
    public int Size()
    {
        return m_top + 1;
    }
    public int Peek()
    {
        if ( IsEmpty() ) {
            return -1;
        }

        return m_elements[ m_top ];
    }

    public int Pop()
    {
        if ( IsEmpty() ) {
            return -1;
        }

        if ( m_top <= ( m_capacity >> 2 ) ) {
            Compact();
        }

        return m_elements[ m_top-- ];
    }

    public void Push( int data )
    {
        if ( (m_top + 1) < m_capacity ) {
            m_elements[ ++m_top ] = data;
        } else {
            Expand();
        }
    }

    public void Compact()
    {
        if ( !m_resizeable ) {
            return;
        }

        System.out.println( "Compacting stack capacity to " + ( m_capacity >> 1 ) );
        int newElements[] = new int[ m_capacity >> 1 ];
        for ( int i = 0; i <= m_top; i++ ) {
            newElements[ i ] = m_elements[ i ];
        }
        m_elements = newElements;
        m_capacity >>= 1;
    }

    public void Expand()
    {
        if ( !m_resizeable ) {
            return;
        }

        System.out.println( "Expanding stack capacity to " + ( m_capacity << 1 ) );
        int newElements[] = new int[ m_capacity << 1 ];
        for ( int i = 0; i <= m_top; i++ ) {
            newElements[ i ] = m_elements[ i ];
        }
        m_elements = newElements;
        m_capacity <<= 1;
    }

    public boolean IsEmpty() { return ( m_top == -1 ); }

    public void Print()
    {
        if ( IsEmpty() ) {
            return;
        }

        System.out.println( "\nPrinting array stack data:" );
        for ( int i = m_top; i >= 0; --i ) {
            System.out.print( m_elements[ i ] + " " );
        }
        System.out.println( "\nDone printing array stack data." );
    }
}
