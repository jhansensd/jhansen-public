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
public class BinaryTree<DataType> extends TreeBase<DataType>
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
    }

    public BaseNode InsertNodeRecursive( DataType data, BaseNode parent )
    {
        return null;
    }

    public boolean RemoveNode( DataType data )
    {
        return false;
    }

    public BaseNode DepthFirstSearchIterative( BaseNode node, DFSType dfsType, DataType data, boolean printPath )
    {return null;
    }

    public BaseNode BreadthFirstSearchIterative( BaseNode node, DataType data, boolean printPath )
    { return null;
    }

    public BaseNode DepthFirstSearchRecursive( BaseNode node, DFSType dfsType, DataType data, boolean printPath )
    {return null;}

    public BaseNode BreadthFirstSearchRecursive( BaseNode node, DataType data, boolean printPath)
    {
        return null;
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

        return Math.max( GetDepthIterative( node.Left() ), GetDepthIterative( node.Right() ) ) + 1;
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
        return;
    }

    public BaseNode FindMinimum( BaseNode node )
    {
        return null;
    }

    public BaseNode FindMaximum( BaseNode node )
    {
        return null;
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
        System.out.println( "Depth is " + depth );

        if ( depth == 0 ) {
            return null;
        }

        Integer num = randNum.nextInt( 1000 );

        while ( Search( ( DataType ) num ) != null ) {
            num = randNum.nextInt( 1000 );
        }

        TreeNode root = new TreeNode( ( DataType )num, parent );

        if ( m_root == null ) {
            m_root = parent = root;
        }

        System.out.println( "Parent data: " + parent.Data() + " Node data: " + root.Data() );

        System.out.println( "Setting Left:" );
        root.SetLeft( CreateRandomTree( depth - 1, 2, root ) );

        System.out.println( "Setting Right:" );
        root.SetRight( CreateRandomTree( depth - 1, 2, root ) );

        return root;
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
