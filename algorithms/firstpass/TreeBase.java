package testdstructures;

import java.util.ArrayList;

abstract public class TreeBase<DataType>
{
    enum TreeType
    {
        eGenericTree,
        eBinaryTree,
        eBinarySearchTree,
        eTreeTypeNone
    }

    enum DFSType
    {
        ePreOrder,
        eInOrder,
        ePostOrder,
        eDFSTypeNone
    }

    abstract public class BaseNode
    {
        public DataType Data() { return null; }
        public BaseNode Parent() { return null; }
        BaseNode Child( int index ) { return null; }
        ArrayList<BaseNode> Children() { return null;}
        BaseNode Right() { return null; }
        BaseNode Left() { return null; }
        void SetParent( BaseNode parent ) {}
        void SetLeft( BaseNode left ) {}
        void SetRight( BaseNode right ) {}
        void SetData( DataType data ) {}
    }

    public BaseNode Search( DataType data )
    {
        return FindNode( false, DFSType.eDFSTypeNone, true, m_root, data, false );
    }

    public BaseNode FindNode(boolean depthFirstSearch, DFSType dfsType, boolean iterative,
                             BaseNode node, DataType data, boolean printPath)
    {
        if ( depthFirstSearch ) {
            if ( iterative ) {
                return DepthFirstSearchIterative( node, dfsType, data, printPath);
            } else {
                return DepthFirstSearchRecursive( node, dfsType, data, printPath);
            }
        }

        if ( iterative ) {
            return BreadthFirstSearchIterative( node, data, printPath);
        } else {
            return BreadthFirstSearchRecursive( node, data, printPath );
        }
    }

    protected BaseNode m_root;

    public BaseNode Root()
    {
        return m_root;
    }

    void InsertNodeIterative( DataType data, BaseNode parent, int distance )
    {}

    BaseNode InsertNodeRecursive( DataType data, BaseNode parent, int distance )
    { return null; }

    void InsertNodeIterative( DataType data, BaseNode parent )
    { return; }

    BaseNode InsertNodeRecursive( DataType data, BaseNode parent )
    { return null; }

    abstract boolean RemoveNode( DataType data );
    abstract BaseNode DepthFirstSearchIterative( BaseNode node, DFSType dfsType, DataType data, boolean printPath );
    abstract BaseNode BreadthFirstSearchIterative( BaseNode node, DataType data, boolean printPath);
    abstract BaseNode DepthFirstSearchRecursive( BaseNode node, DFSType dfsType, DataType data, boolean printPath );
    abstract BaseNode BreadthFirstSearchRecursive( BaseNode node, DataType data, boolean printPath);
    abstract boolean IsBalanced( BaseNode node );
    abstract  void Balance();
    abstract int GetDepthIterative( BaseNode node );
    abstract int GetDepthRecursive( BaseNode node );
    abstract void Rotate( boolean right, int amount );
    abstract BaseNode FindMinimum( BaseNode node );
    abstract BaseNode FindMaximum( BaseNode node );
    abstract BaseNode Successor( BaseNode node );
    abstract BaseNode Predecessor( BaseNode node );
    abstract BaseNode CreateRandomTree( int depth, int childSize, BaseNode parent );
    abstract void Print( BaseNode root );
}