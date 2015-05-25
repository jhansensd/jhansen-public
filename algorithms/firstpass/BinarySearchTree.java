package testdstructures;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;
import java.util.*;

@SuppressWarnings("unchecked")

/**
 * Created by jhansen on 5/5/2015.
 */
public class BinarySearchTree<DataType> extends TreeBase<DataType>
{
    public class TreeNode extends BaseNode
    {
        TreeNode( DataType data, BaseNode parent )
        {
            m_data = data;
            m_parent = parent;
        }

        public DataType Data()
        {
            return m_data;
        }

        public BaseNode Parent()
        {
            return m_parent;
        }

        public void SetParent( BaseNode parent ) { m_parent = parent; }

        public BaseNode Left()
        {
            return m_left;
        }

        public BaseNode Right()
        {
            return m_right;
        }

        public void SetLeft( BaseNode left )
        {
            m_left = left;
        }
        public void SetRight( BaseNode right )
        {
            m_right = right;
        }

        public void SetData( DataType data )
        {
            m_data = data;
        }

        DataType m_data;
        BaseNode m_parent;
        BaseNode m_left;
        BaseNode m_right;
    }

    public void InsertNodeIterative( DataType data, BaseNode parent )
    {
        if ( parent == null ) {
            BaseNode node = new TreeNode( data, parent );
            if ( m_root == null ) {
                m_root = node;
            }

            return;
        }

        while ( parent != null ) {

            if ( (Integer)data < (Integer)parent.Data() ) {

                if ( parent.Left() == null ) {
                    parent.SetLeft( new TreeNode( data, parent ) );
                    return;
                } else {
                    parent = parent.Left();
                }

            } else {

                if ( parent.Right() == null ) {
                    parent.SetRight( new TreeNode( data, parent ) );
                    return;
                } else {
                    parent = parent.Right();
                }
            }
        }
    }

    public BaseNode InsertNodeRecursive( DataType data, BaseNode parent )
    {
        if ( parent == null ) {
            TreeNode node = new TreeNode( data, parent );
            if ( m_root == null ) {
                m_root = node;
            }

            return node;
        }

        if ( (Integer)data < (Integer)parent.Data() ) {
            parent.SetLeft( InsertNodeRecursive( data, parent.Left() ) );
        } else {
            parent.SetRight( InsertNodeRecursive( data, parent.Right() ) );
        }

        return parent;
    }

    public boolean RemoveNode( DataType data )
    {
        return false;
    }

    public BaseNode BinarySearchIterative( BaseNode node, DataType data, boolean printPath )
    {
        if ( node == null ) {
            return null;
        }

        if ( printPath ) {
            System.out.println( "Traversing" );
        }

        while ( node != null ) {
            if ( node.Data() == data ) {
                return node;
            } else if ( (Integer)node.Data() < (Integer)data ) {
                node = node.Left();
            } else {
                node = node.Right();
            }
        }

        return null;
    }

    public BaseNode BinarySearchRecursive( BaseNode node, DataType data, boolean printPath )
    {
        if ( node == null ) {
            return null;
        }

        if ( node.Data().equals( data ) ) {
            return node;
        }
        if ( (Integer)data < (Integer)node.Data() ) {
            return BinarySearchRecursive( node.Left(), data, printPath );
        }

        return BinarySearchRecursive( node.Right(), data, printPath );
    }

    public BaseNode DepthFirstSearchRecursive( BaseNode node, DFSType dfsType, DataType data, boolean printPath )
    {
        if ( node == null ) {
            return null;
        }

        if ( node.Data() == data ) {
            if ( printPath == true ) {
                System.out.println( "Found our data of:" + data + " here!" );
            }

            return node;
        }

        if ( dfsType == DFSType.ePreOrder && printPath == true ) {
            System.out.println( "Pre Order Visit Node:" + node.Data() );
        }

        BaseNode foundNode = DepthFirstSearchRecursive( node.Left(), dfsType, data, printPath );

        if ( foundNode != null ) {
            return foundNode;
        }

        if ( dfsType == DFSType.eInOrder && printPath == true ) {
            System.out.println( "In Order Visiting Node:" + node.Data() );
        }

        foundNode = DepthFirstSearchRecursive( node.Right(), dfsType, data, printPath );

        if ( foundNode != null ) {
            return foundNode;
        }

        if ( dfsType == DFSType.ePostOrder && printPath == true ) {
            System.out.println( "Post Order Visiting Node:" + node.Data() );
        }

        return null;
    }

    public BaseNode BreadthFirstSearchIterative( BaseNode node, DataType data, boolean printPath )
    {
        if ( node == null ) {
            return null;
        }

        if ( printPath ) {
            System.out.println( "Traversing" );
        }

        // Add the top item to a queue.
        java.util.LinkedList<BaseNode> queue = new LinkedList();
        queue.add( node );

        while ( !queue.isEmpty() ) {

            // Pop first element from the front and check for a match.
            BaseNode tempNode = queue.remove();

            if ( printPath ) {
                System.out.println( "Depth:" + GetDepthRecursive( tempNode ) + " Val:" + tempNode.Data() );
            }

            if ( tempNode.Data() == data ) {
                return tempNode;
            }

            if ( tempNode.Left() != null ) {
                queue.add( tempNode.Left() );
            }

            if ( tempNode.Right() != null ) {
                queue.add( tempNode.Right() );
            }
        }

        return null;
    }

    public BaseNode DepthFirstSearchIterative( BaseNode node, DFSType dfsType, DataType data, boolean printPath )
    {
        if ( node == null ) {
            return null;
        }

        switch ( dfsType ) {
            case ePreOrder:
                return PreOrderDFSIterative( node, data, printPath );

            case eInOrder:
                return InOrderDFSIterative( node, data, printPath );

            case ePostOrder:
                return PostOrderDFSIterative( node, data, printPath );
        }

        return null;
    }

    public BaseNode PreOrderDFSIterative( BaseNode node, DataType data, boolean printPath)
    {
        BaseNode checkNode = node;
        LinkedList<BaseNode> stack = new java.util.LinkedList();
        stack.push( checkNode );

        while ( !stack.isEmpty() ) {

            checkNode = stack.pop();

            if ( printPath ) {
                System.out.println( "Pre Order Visit:" + checkNode.Data() );
            }

            if ( checkNode.Data() == data ) {
                System.out.println( "Found our data of:" + data + " here!" );
                return checkNode;
            }

            if ( checkNode.Right() != null ) {
                stack.push( checkNode.Right() );
            }

            if ( checkNode.Right() != null ) {
                stack.push( checkNode.Left() );
            }
        }

        return null;
    }

    public BaseNode InOrderDFSIterative( BaseNode node, DataType data, boolean printPath)
    {
        LinkedList<BaseNode> stack = new java.util.LinkedList();

        while ( !stack.isEmpty() || node != null ) {

            if ( node != null ) {
                stack.push( node );
                node = node.Left();
            }
            else {

                BaseNode newNode = stack.pop();

                if ( printPath ) {
                    System.out.println( "In Order Visit:" + newNode.Data() );
                }

                if ( newNode.Data() == data ) {
                    System.out.println( "Found our data of:" + data + " here!" );
                    return node;
                }

                node = newNode.Right();
            }
        }

        return null;
    }

    public BaseNode PostOrderDFSIterative( BaseNode node, DataType data, boolean printPath)
    {
        LinkedList<BaseNode> stack = new java.util.LinkedList();
        BaseNode lastVisited = null;
        while ( !stack.isEmpty() || node != null ) {

            if ( node != null ) {
                stack.push( node );
                node = node.Left();
            }
            else {

                BaseNode newNode = stack.peek();

                if ( newNode.Right() != null && lastVisited != newNode.Right() ) {
                    node = newNode.Right();
                }
                else {
                    if ( printPath ) {
                        System.out.println( "In Order Visit:" + newNode.Data() );
                    }

                    if ( newNode.Data() == data ) {
                        System.out.println( "Found our data of:" + data + " here!" );
                        return node;
                    }

                    lastVisited = stack.pop();
                }
            }
        }

        return null;
    }

    public BaseNode BreadthFirstSearchRecursive( BaseNode node, DataType data, boolean printPath)
    {
        if ( node == null ) {
            return null;
        }

        if ( printPath ) {
            System.out.println( "Traversing" );
        }

        if ( printPath ) {
            System.out.println( "Depth:" + GetDepthRecursive( node ) + " Val:" + node.Data() );
        }

        if ( node.Data() == data ) {
            return node;
        }

        BaseNode left = BreadthFirstSearchRecursive( node.Left(), data, printPath );

        if ( left != null )
        {
            return left;
        }

        return BreadthFirstSearchRecursive( node.Right(), data, printPath );
    }

    public boolean IsBalanced( BaseNode node )
    {
        if ( node == null ) {
            return true;
        }

        return ( Math.abs( GetDepthRecursive( node.Left() ) -
                           GetDepthRecursive( node.Right() ) ) <= 1 ) &&
                IsBalanced( node.Left() ) &&
                IsBalanced( node.Right() );
    }

    public void Balance()
    {}

    public int GetDepthIterative( BaseNode node )
    {
        if ( node == null ) {
            return 0;
        }

        int depth = 0;
        Deque<BaseNode> queue = new ArrayDeque();
        queue.add( node );

        while ( !queue.isEmpty() ) {
            int nodeCount = queue.size();
            depth++;
            while ( nodeCount > 0 ) {
                node = queue.remove();

                if ( node.Left() != null ) {
                    queue.add( node.Left() );
                }
                if ( node.Right() != null ) {
                    queue.add( node.Right() );
                }

                --nodeCount;
            }
        }

        return depth;
    }

    public int GetDepthRecursive( BaseNode node )
    {
        if ( node == null ) {
            return 0;
        }

        return Math.max( GetDepthRecursive( node.Left() ), GetDepthRecursive( node.Right() ) ) + 1;
    }

    public void Rotate( boolean right, int amount )
    {
        if ( m_root == null || amount <= 0 ) {
            return;
        }

        if( right ) {

            if ( m_root.Left() == null ) {
                return;
            }

            BaseNode newRoot = m_root.Left();
            m_root.SetLeft( newRoot.Right() );
            if ( newRoot.Right() != null ) {
                m_root.Left().SetParent( m_root );
            }

            newRoot.SetRight( m_root );
            m_root.SetParent( newRoot );

            m_root = newRoot;
            m_root.SetParent( null );
            Rotate( right, amount - 1 );
        } else {
            if ( m_root.Right() == null ) {
                return;
            }

            BaseNode newRoot = m_root.Right();
            m_root.SetRight( newRoot.Left() );
            if ( newRoot.Left() != null ) {
                m_root.Right().SetParent( m_root );
            }

            newRoot.SetLeft( m_root );
            m_root.SetParent( newRoot );

            m_root = newRoot;
            m_root.SetParent( null );
            Rotate( right, amount - 1 );
        }
    }

    public BaseNode FindMinimum( BaseNode node )
    {
        if ( node == null ) {
            return null;
        }

        BaseNode min = FindMinimum( node.Left() );
        if ( min == null ) {
            return node;
        }

        return min;
    }

    public BaseNode FindMaximum( BaseNode node )
    {
        if ( node == null ) {
            return null;
        }

        BaseNode max = FindMaximum( node.Right() );
        if ( max == null ) {
            return node;
        }

        return max;
    }

    public void HeapifyBinaryTree( BaseNode node )
    {
        int size = Traverse( node, 0, null );
       ArrayList list = new ArrayList<BaseNode>();

        // Sort array of nodes based on their values, using Comparator object
        list.sort( new Comparator<BaseNode>(){
            @Override public int compare(BaseNode m, BaseNode n){
                int mv = (Integer)m.Data(), nv = (Integer)n.Data();
                return ( mv < nv ? -1 : ( mv == nv ? 0 : 1));
            }
        });

        for ( int i = 0; i < size; i++ ) {
            int left = 2*i + 1;
            int right = 2*i + 2;
            BaseNode baseNode = (BaseNode)list.get( i );
            BaseNode leftNode = (BaseNode)list.get( left );
            BaseNode rightNode = (BaseNode)list.get( right );

            baseNode.SetLeft( left >= size ? null : leftNode );
            baseNode.SetRight( right >= size ? null : rightNode );
        }
    }

    public int Traverse( BaseNode node, int count, BaseNode arr[] )
    {
        if ( node == null ) {
            return count;
        }

        if ( arr != null ) {
            arr[ count ] = node;
        }

        count++;

        count = Traverse( node.Left(), count, arr );
        count = Traverse( node.Right(), count, arr );
        return count;
    }

    public BaseNode Successor( BaseNode node )
    {
        return null;
    }

    public BaseNode Predecessor( BaseNode node )
    {
        return null;
    }

    private static Random randNum = new Random( 10000 );
    public BaseNode CreateRandomTree( int depth, int childSize, BaseNode parent )
    {
        int numNodes = (int)Math.pow( 2, depth) - 1;
        int numArray[] = new int[ numNodes ];

        for ( int i = 0; i < numNodes; i++ ) {
            Integer num = randNum.nextInt( 1000 );

            while ( Search( ( DataType ) num ) != null ) {
                num = randNum.nextInt( 1000 );
            }

            numArray[ i ] = num;
        }

        // Sort the array.
        java.util.Arrays.sort( numArray );

        m_root = MakeTree( 0, numNodes - 1, numArray );
        return m_root;
    }

    private TreeNode MakeTree( int low, int high, int[] array )
    {
        if ( low > high ) {
            return null;
        } else {
            int mid = (low + high) >> 1;
            Integer num = new Integer( array[ mid ] );
            TreeNode node = new TreeNode( (DataType)num, null );
            node.SetLeft( MakeTree( low, ( mid - 1 ), array ) );
            node.SetRight( MakeTree( ( mid + 1 ), high, array ) );

            if ( node.Left() != null ) {
                node.Left().SetParent( node );
            }

            if ( node.Right() != null ) {
                node.Right().SetParent( node );
            }

            return node;
        }
    }

    public void Print( BaseNode root )
    {
        if ( root == null ) {
            root = m_root;
        }

        System.out.println( "digraph BinarySearchTree {" );
        PrintNode( root );
        System.out.println( "}" );
    }

    private void PrintNode( BaseNode root )
    {
        if ( root == null ) {
            return;
        }

        if ( root.Parent() != null ) {
            System.out.println( "\t" + root.Parent().Data() + " -> " + root.Data() + ";" );
        }

        PrintNode( root.Left() );
        PrintNode( root.Right() );
    }
}
