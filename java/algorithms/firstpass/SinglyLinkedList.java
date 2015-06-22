package testdstructures;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Created by hansj058 on 5/1/2015.
 */
public class SinglyLinkedList<DataType>
{
    static int count = -1;
    private ListNode m_head;

    static public void Run()
    {
        SinglyLinkedList random = SinglyLinkedList.CreateRandomList( 10 );
        random.Print();

        /*SinglyLinkedList list = new SinglyLinkedList();
        list.InsertLast( 1 );
        list.InsertLast( 2 );
        list.InsertLast( 3 );
        list.InsertLast( 4 );*/
        /*list.InsertLast( 5 );
        list.InsertLast( 6 );
        list.InsertLast( 7 );
        list.InsertLast( 8 );
        list.InsertLast( 9 );
        list.InsertLast( 10 );*/

        //System.out.println( "Initial list acyclic:" + list.Acyclic() );
        //SinglyLinkedList.ListNode node10 = list.Find( 10 );
        //SinglyLinkedList.ListNode node6 = list.Find( 6 );
        //node10.SetNext( node6 );
        //System.out.println( "After making cycle, acyclic is:" + list.Acyclic() );

        //System.out.println( "The beginning value is:" + list.FindBeginning().Data() );
        /*SinglyLinkedList list2 = new SinglyLinkedList();
        list2.InsertLast( 6 );
        list2.InsertLast( 9 );
        list2.InsertLast( 2 );

        System.out.println( "Printing list1." );
        list1.Print();
        System.out.println( "Printing list2." );
        list2.Print();

        SinglyLinkedList sumList = new SinglyLinkedList();
        sumList.SetHead( sumList.AddNumericListsRecursive( list1.Head(), list2.Head(), 0 ) );
        System.out.println( "Printing sum list." );
        sumList.Print();*/
        //list.Print();
        //SinglyLinkedList list = new SinglyLinkedList();
        //list.RemoveDuplicates2();

        /*System.out.println( "Count is " + list.Count() );
        System.out.println( "Middle1 is " + list.FindMiddle1().Data() );
        System.out.println( "Middle2 is " + list.FindMiddle1().Data() );*/

        /*for ( int i = 0; i < list.Count(); i++ ) {
           // System.out.println( "NthToLastIterative of " + i + " is " + list.NthToLastIterative( i ).Data() );
            count = -1;
            System.out.println( "NthToLastRecursive of " + i + " is " + list.NthToLastRecursive( list.Head(), i ).Data() );
        }*/

        //SinglyLinkedList.ListNode mid = list.FindMiddle1();

        /*list.Print();
        System.out.println( "Deleting middle." );
        list.RemoveMid( mid );
        list.Print();
        System.out.println( "Deleting first." );
        list.DeleteFirst();
        list.Print();
        System.out.println( "Deleting first." );
        list.DeleteFirst();
        list.Print();*/

        //list.Print();

        /*System.out.println( "Reversing list recursively1." );
        list.SetHead( list.ReverseRecursive1( list.Head() ) );
        list.Print();

        System.out.println( "Reversing list recursively2." );
        list.SetHead( list.ReverseRecursive2( list.Head() ) );
        list.Print();

        System.out.println( "Reversing list iteratively." );
        list.ReverseIterative();
        list.Print();*/
    }

    private static Random randNum = new Random( 10000 );
    static public SinglyLinkedList CreateRandomList( int count )
    {
        SinglyLinkedList list = new SinglyLinkedList();
        for ( int i = 0; i < count; i++ ) {
            list.InsertFirst( randNum.nextInt( 1000 ) );
        }

        return list;
    }

    static public SinglyLinkedList MergeLists( SinglyLinkedList list1, SinglyLinkedList list2 )
    {
        return null;
    }

    static public boolean Compare( SinglyLinkedList list1, SinglyLinkedList list2 )
    {
        SinglyLinkedList.ListNode node1 = list1.Head();
        SinglyLinkedList.ListNode node2 = list2.Head();

        while ( node1 != null && node2 != null ) {

            if ( node1.Data() != node2.Data() ) {
                return false;
            }

            node1 = node1.Next();
            node2 = node2.Next();
        }

        return true;
    }

    public ListNode AddNumericListsIterative( ListNode node1, ListNode node2 )
    {
        if ( node1 == null && node2 == null ) {
            return null;
        }

        int val1, val2;
        Integer result;
        int carry = 0;
        ListNode sumNode = null;

        while ( true ) {

            val1 = 0;
            val2 = 0;

            if ( node1 != null ) {
                val1 = ( Integer ) node1.Data();
                node1 = node1.Next();
            }

            if ( node2 != null ) {
                val2 = ( Integer ) node2.Data();
                node2 = node2.Next();
            }

            result = val1 + val2 + carry;

            if ( result >= 10 ) {
                result -= 10;
                carry = 1;
            }
            else {
                carry = 0;
            }

            ListNode resultNode = new ListNode( ( DataType ) result );
            resultNode.SetNext( sumNode );
            sumNode = resultNode;

            if ( node1 == null && node2 == null ) {
                return resultNode;
            }
        }
    }

    public ListNode AddNumericListsRecursive( ListNode list1, ListNode list2, int carry )
    {
        if ( list1 == null && list2 == null ) {
            return null;
        }

        Integer total = carry;

        if ( list1 != null ) {
            total += ( Integer ) list1.Data();
        }

        if ( list2 != null ) {
            total += ( Integer ) list2.Data();
        }

        Integer result = ( total % 10 );
        ListNode node = new ListNode( ( DataType ) result );

        node.SetNext( AddNumericListsRecursive( list1 == null ? null : list1.Next(),
                                                list2 == null ? null : list2.Next(),
                                                total > 10 ? 1 : 0 ) );

        return node;
    }

    public class ListNode implements Iterable<ListNode>
    {
        public ListIterator iterator()
        {
            return new ListIterator();
        }

        public class ListIterator implements Iterator<ListNode>
        {
            private ListNode current;

            public ListIterator()
            {
                current = m_head;
            }

            @Override
            public boolean hasNext()
            {
                return current != null;
            }

            @Override
            public ListNode next()
            {
                if ( current != null ) {
                    ListNode node = current;
                    current = current.Next();
                    return node;
                }

                return null;
            }

            @Override
            public void remove()
            {
                ListNode prev = m_head;

                while (prev != null && current != null)
                {
                    if (prev.Next() == current)
                    {
                        prev.SetNext(current.Next());
                        current = prev;
                        return;
                    }
                }
            }
        }

        public ListNode( DataType data )
        {
            m_data = data;
            m_next = null;
        }

        public DataType Data() { return m_data; }
        public void SetData( DataType data ) { m_data = data; }

        public ListNode Next() { return m_next; }
        public void SetNext( ListNode node ) { m_next = node; }

        private DataType m_data;
        private ListNode m_next;
    }

    public SinglyLinkedList()  { m_head = null; }
    public ListNode Head() { return m_head; }
    public void SetHead( ListNode head ) { m_head = head; }
    public ListNode GetFirst() { return m_head; }

    public ListNode GetLast()
    {
        if ( m_head == null ) {
            return null;
        }

        for ( ListNode node : m_head ) {
            if ( node.Next() == null ) {
                return node;
            }
        }

        return null;
    }

    public ListNode GetNext( ListNode node )
    {
        if ( node != null ) {
            return node.Next();
        }

        return null;
    }

    public ListNode GetPrev( ListNode node )
    {
        if ( m_head == null || node == null ) {
            return null;
        }

        for ( ListNode curr : m_head ) {
            if ( curr.Next() == node ) {
                return curr;
            }
        }

        return null;
    }

    public ListNode GetByIndex( int index )
    {
        if ( m_head == null ) {
            return null;
        }

        int count = 0;
        for ( ListNode node : m_head ) {
            if ( count++ == index ) {
                return node;
            }
        }

        return null;
    }

    public void InsertFirst( DataType data )
    {
        ListNode node = new ListNode( data );

        if ( m_head == null ) {
            m_head = node;
        } else {
            node.SetNext( m_head );
            m_head = node;
        }
    }

    public void InsertLast( DataType data )
    {
        if ( m_head == null ) {
            m_head = new ListNode( data );
            return;
        }

        for ( ListNode node : m_head ) {
            if ( node.Next() == null ) {
                ListNode newNode = new ListNode( data );
                node.SetNext( newNode );
            }
        }
    }

    public void InsertAfter( ListNode node, DataType data )
    {
        if ( m_head == null || node == null ) {
            return;
        }

        for ( ListNode curr : m_head ) {
            if ( curr == node ) {
                ListNode newNode = new ListNode( data );
                ListNode next = curr.Next();
                curr.SetNext( newNode );
                newNode.SetNext( next );
                return;
            }
        }
    }

    public void InsertBefore( ListNode node, DataType data )
    {
        if ( m_head == null || node == null ) {
            return;
        }

        for ( ListNode curr : m_head ) {
            if ( curr.Next() == node ) {
                ListNode newNode = new ListNode( data );
                curr.SetNext( newNode );
                newNode.SetNext( node );
                return;
            }
        }
    }

    public void DeleteFirst()
    {
        if ( m_head != null ) {
            m_head = m_head.Next();
        } else {
            m_head = null;
        }
    }

    public void Delete( ListNode node )
    {
        if ( m_head != null ) {
            return;
        }

        ListNode prev = null;

        for ( ListNode checkNode : m_head ) {

            if ( checkNode.Data() == node.Data() ) {
                prev.SetNext( checkNode.Next() );
                return;
            }

            prev = checkNode;
        }
    }

    public void DeleteLast()
    {
        if ( m_head == null ) {
            return;
        }

        ListNode prev = null;
        for ( ListNode node : m_head ) {
            if ( node.Next() == null ) {
                prev.SetNext( null );
            }
            prev = node;
        }
    }

    public void DeleteAll() { m_head = null; }

    public void ReverseIterative()
    {
        if ( m_head == null || m_head.Next() == null ) {
            return;
        }

        ListNode nextNode = null;
        ListNode prevNode = null;
        ListNode currNode = m_head;

        while ( currNode != null ) {
            nextNode = currNode.Next();
            currNode.SetNext( prevNode );
            prevNode = currNode;
            currNode = nextNode;
        }

        m_head = prevNode;
    }

    public ListNode ReverseRecursive1( ListNode node )
    {
        if ( node == null || node.Next() == null ) {
            return node;
        }

        ListNode secondNode = node.Next();
        ListNode reversedList = ReverseRecursive1( secondNode );
        secondNode.SetNext( node );
        node.SetNext( null );

        return reversedList;
    }

    public ListNode ReverseRecursive2( ListNode node )
    {
        if ( node == null || node.Next() == null ) {
            return node;
        }

        ListNode reversedList = ReverseRecursive2( node.Next() );

        node.Next().SetNext( node );
        node.SetNext( null );

        return reversedList;
    }

    public ListNode Find( DataType data )
    {
        if ( m_head == null ) {
            return null;
        }

        for ( ListNode node : m_head ) {
            if ( node.Data() == data ) {
                return node;
            }
        }

        return null;
    }

    public ListNode FindMiddle1()
    {
        ListNode slow = m_head;
        ListNode fast = m_head;

        while ( fast != null && fast.Next() != null ) {

            slow = slow.Next();
            fast = fast.Next().Next();
        }

        return slow;
    }

    public ListNode FindMiddle2()
    {
        if ( m_head == null ) {
            return null;
        }

        ListNode middle = m_head;
        int count = 0;

        for ( ListNode node : m_head ) {
            if ( ( count++ % 2 ) == 0 ) {
                middle = middle.Next();
            }
        }

        return null;
    }

    // Don't use head.
    public void RemoveMid( ListNode node ) {

        if ( m_head == null || node == null || node.Next() == null ) {
            return;
        }

        node.SetData( node.Next().Data() );
        node.SetNext( node.Next().Next() );
    }

    // N = 0 is the last element.
    public ListNode NthToLastIterative( int n )
    {
        if ( m_head == null || n < 0 ) {
            return null;
        }

        ListNode current = m_head;
        ListNode behind = m_head;

        for ( int i = 0; i < n; i++ ) {

            if ( current.Next() == null ) {
                return null; // Not found.
            }

            current = current.Next();
        }

        while ( current.Next() != null ) {
            current = current.Next();
            behind = behind.Next();
        }

        return behind;
    }

    // N = 0 is the last element.
    public ListNode NthToLastRecursive( ListNode node, int n )
    {
        if ( m_head == null || node == null ) {
            return null;
        }

        ListNode found = NthToLastRecursive( node.Next(), n );

        if ( ++count == n ) {
            return node;
        }

        return found;
    }


    public boolean Acyclic()
    {
        if ( m_head == null ) {
            return false;
        }

        ListNode slow = m_head;
        ListNode fast = m_head.Next(); // Moved ahead so we don't start at the same spot.

        while ( fast != null && fast.Next() != null ) {

            // Check if fast has caught up with slow.
            if ( slow == fast || fast.Next() == slow ) {
                return true;
            }

            slow = slow.Next();
            fast = fast.Next().Next();
        }

        return false;
    }


    public ListNode FindBeginning()
    {
        if ( Acyclic() == false || m_head == null ) {
            return null;
        }

        ListNode slow = m_head;
        ListNode fast = m_head;

        // Find meeting place.
        while ( true ) {

            if ( fast == null || fast.Next() == null ) {
                return null;
            }

            slow = slow.Next();
            fast = fast.Next().Next();

            if ( slow == fast ) {
                break;
            }
        }

        slow = m_head;

        while ( slow != fast ) {
            slow = slow.Next();
            fast = fast.Next();
        }

        return slow;
    }

    // With extra buffer.
    public void RemoveDuplicates()
    {
        if ( m_head == null ) {
             return;
        }

        HashMap<Integer, Boolean> map = new HashMap();
        ListNode prev = null;
        for ( ListNode node : m_head ) {
            if ( map.containsKey( node.Data() ) ) {
                prev.SetNext( node.Next() );
            } else {
                map.put( (Integer)node.Data(), true );
                prev = node;
            }
        }
    }

    // Without extra buffer.
    public void RemoveDuplicates2()
    {
        if ( m_head == null ) {
            return;
        }

        ListNode current = m_head.Next();
        ListNode previous = m_head;

        while ( current != null ) {
            ListNode runner = m_head;

            while ( runner != current ) {
                if ( runner.Data() == current.Data() ) { // Remove it.
                    ListNode tmp = current.Next();
                    previous.SetNext( current.Next() );
                    current = tmp;
                    break;
                }

                runner = runner.Next();
            }

            if ( current == runner ) {
                previous = current;
                current = current.Next();
            }
        }
    }

    public int Count()
    {
        if ( m_head == null ) {
            return 0;
        }

        int count = 0;
        for ( ListNode node : m_head ) {
            count++;
        }

        return count;
    }

    public void Sort() {}
    public void Shuffle() {}

    public void Print()
    {
        System.out.println( "\nPrinting list data:" );

        if ( m_head == null ) {
            return;
        }

        for ( ListNode node : m_head ) {
            System.out.print( node.Data() + " " );
        }

        System.out.println( "\nDone printing list data.\n" );
    }
}
//