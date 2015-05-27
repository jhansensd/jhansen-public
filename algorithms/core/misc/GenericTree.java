package testdstructures;

import java.util.*;

public class GenericTree<DataType> extends TreeBase<DataType>
{
    public class TreeNode extends BaseNode
    {
        TreeNode(DataType data, BaseNode parent)
        {
            m_data = data;
            m_parent = parent;
            m_children = new ArrayList();
        }

        public DataType Data()
        {
            return m_data;
        }

        public BaseNode Parent()
        {
            return m_parent;
        }

        public BaseNode Child( int index )
        {
            return m_children.get( index );
        }

        public ArrayList<BaseNode> Children()
        {
            return m_children;
        }

        DataType m_data;
        BaseNode m_parent;
        ArrayList<BaseNode> m_children;
    }

    // Attempt to insert the node distance away from the parent.
    public void InsertNodeIterative(DataType data, BaseNode parent, int distance )
    {
        Random randNum = new Random();

        if ( parent != null ) {
            // Insert this node distance from the parent in a random child.
            for (int i = 1; i < distance; i++) {
                if ( parent.Children().isEmpty()) {
                    System.out.println("Couldn't go the distance:" + distance); // Couldn't go the distance!
                    return; // Don't do anything because we couldn't go the distance we needed.
                }

                Random num = new Random();
                parent = parent.Child( num.nextInt( parent.Children().size()) );
            }
        }

        // Insert under the found parent in a random location.
        TreeNode newNode = new TreeNode( data, parent );

        if ( parent != null ) {
            int randInsertionIndex = randNum.nextInt( parent.Children().size() + 1 );
            parent.Children().add( randInsertionIndex, newNode );
        }

        if ( m_root == null ) {
            m_root = newNode;
        }
    }

    public BaseNode InsertNodeRecursive(DataType data, BaseNode parent, int distance )
    {
        return null;
    }

    public boolean RemoveNode( DataType data )
    {
        BaseNode node = FindNode( false, TreeBase.DFSType.ePreOrder, true, m_root, data, false );

        if ( node != null ) {
            node.Parent().Children().remove( node );
            return true;
        }

        return false;
    }

    public BaseNode DepthFirstSearchIterative( BaseNode node, DFSType dfsType, DataType data, boolean printPath )
    {
        return null;
    }

    public BaseNode DepthFirstSearchRecursive( BaseNode node, DFSType dfsType, DataType data, boolean printPath )
    {
        return null;
    }

    public BaseNode BreadthFirstSearchIterative( BaseNode node, DataType data, boolean printPath )
    {
        if ( node == null )
        {
            return null;
        }

        if ( printPath ) {
            System.out.println( "Traversing" );
        }

        // Add the top item to a new queue.
        LinkedList<BaseNode> nodeQueue = new LinkedList();
        nodeQueue.addLast(node);

        // While the queue is not empty.
        while( !nodeQueue.isEmpty() ) {

            // Dequeue the first item from the front and check for a found match.
            BaseNode tempNode = nodeQueue.pollFirst();

            if ( printPath ) {
                System.out.println( "Depth:" + GetDepthRecursive( tempNode ) + " Val:" + tempNode.Data() );
                //System.out.println( "\t" + oldNode.Data() + " -> " + tempNode.Data() + "[color=red]" );
            }

            if ( tempNode.Data() == data ) {
                return tempNode;
            }

            // Enqueue remaining children to end of our queue.
            for ( BaseNode treeNode : tempNode.Children() ) {
                nodeQueue.addLast( treeNode );
            }
        }

        return null;
    }

    public BaseNode BreadthFirstSearchRecursive( BaseNode node, DataType data, boolean printPath )
    {
        return null;
    }

    public boolean IsBalanced( BaseNode node )
    {
        int maxDepth = Integer.MIN_VALUE;
        int minDepth = Integer.MAX_VALUE;

        if ( node == null || node.Children().size() == 0 )
        {
            return true;
        }

        // If the depth between any leaves is greater than 1, this tree is not balanced.
        for ( BaseNode child : node.Children() ) {
            int depth = GetDepthRecursive( child );

            if ( depth > maxDepth ) {
                maxDepth = depth;
            }

            if ( depth < minDepth ) {
                minDepth = depth;
            }
        }

        if ( ( Math.abs( maxDepth - minDepth ) <= 1 ) )
        {
            return false;
        }

        boolean balanced = true;

        // Make sure all the children our also balanced.
        for ( BaseNode child : node.Children() ) {
            balanced = ( balanced && IsBalanced( (BaseNode)child ) );
        }

        return balanced;
    }

    public void Balance()
    {
    }

    // Get the depth of a specific node.
    public int GetDepthIterative( BaseNode node )
    {
        if ( node == null )
        {
            return 0;
        }

        int maxDepth = 0;

        if ( node.Children() != null ) {
            for ( BaseNode tempNode : node.Children() ) {
                int depth = GetDepthIterative( tempNode );
                if ( depth > maxDepth ) {
                    maxDepth = depth;
                }
            }
        }

        return maxDepth + 1;
    }

    public int GetDepthRecursive( BaseNode node )
    {
        if ( node == null )
        {
            return 0;
        }

        int maxDepth = 0;

        if ( node.Children() != null ) {
            for ( BaseNode tempNode : node.Children() ) {
                int depth = GetDepthRecursive( tempNode );
                if ( depth > maxDepth ) {
                    maxDepth = depth;
                }
            }
        }

        return maxDepth + 1;
    }

    public void Rotate(boolean right, int amount)
    {
    }

    public BaseNode FindMinimum( BaseNode node )
    {
        if ( node == null )
        {
            return null;
        }

        BaseNode minNode = node;

        for ( BaseNode tempNode : node.Children() ) {
            BaseNode minChild = FindMinimum( tempNode );
            Integer min = (Integer)minChild.Data();
            if ( min.intValue() < ((Integer)(node.Data())).intValue() )
            {
                minNode = minChild;
            }
        }

        return minNode;
    }

    public BaseNode FindMaximum( BaseNode node )
    {
        if ( node == null )
        {
            return null;
        }

        BaseNode maxNode = node;

        for ( BaseNode tempNode : node.Children() ) {
            BaseNode maxChild = FindMaximum( tempNode );
            Integer max = (Integer)maxChild.Data();
            if ( max.intValue() > ((Integer)(node.Data())).intValue() )
            {
                maxNode = maxChild;
            }
        }

        return maxNode;
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
        if ( depth == 0 ) {
            return null;
        }

        Integer num = new Integer( randNum.nextInt( 1000 ) );

        while ( Search( ( DataType ) num ) != null ) {
            num = new Integer( randNum.nextInt( 1000 ) );
        }

        TreeNode root = new TreeNode( (DataType)num, parent );

        if ( parent == null ) {
            m_root = root;
        }
        else
        {
            parent.Children().add( root );
        }

        // Loop through all the children and add random nodes.
        for ( int i = 0; i < childSize; i++ ) {
            CreateRandomTree( depth - 1, childSize, root );
        }

        return null;
    }

    public void Print( BaseNode root )
    {
        if ( root == null )
        {
            root = m_root;
        }

        System.out.println( "digraph GenericTree {" );
        PrintNode(root);
        System.out.println("}");
    }

    private void PrintNode( BaseNode root )
    {
        if ( root == null )
        {
            return;
        }

        if ( root.Parent() != null )
        {
            System.out.println( "\t" + root.Parent().Data() + " -> " + root.Data() + ";" );
        }

        if ( root.Children() != null ) {
            for ( BaseNode tempNode : root.Children() ) {
                PrintNode( tempNode );
            }
        }
    }

}