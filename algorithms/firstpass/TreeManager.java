package testdstructures;

import java.util.ArrayList;

/**
 * Created by hansj058 on 5/1/2015.
 */
public class TreeManager
{
    public TreeManager( ArrayList<String> input )
    {
        Run(input);
    }

    public TreeBase CreateTree( TreeBase.TreeType eTreeType )
    {
        switch ( eTreeType )
        {
            case eGenericTree:
                return new GenericTree();

            case eBinarySearchTree:
                return new BinarySearchTree();

            /*
            case eBinaryTree:
                return new BinaryTree();

            case eBTree:
                return new AdjacencyListGraph( directed );

            case eRedBlackTree:
                return new RedBlackTree();

            case eAVLTree:
                return new AVLTree();*/
        }

        return null;
    }

    public static void Run( ArrayList<String> input )
    {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        tree.CreateRandomTree( 5, 2, tree.Root() );
        tree.Print( null );
        tree.DepthFirstSearchIterative( tree.Root(), TreeBase.DFSType.eInOrder, null, true );

        //System.out.println( "Height of tree recursive is:" + tree.GetDepthRecursive( tree.Root() ) );
        //System.out.println( "Height of tree iterative is:" + tree.GetDepthIterative( tree.Root() ) );
        //BinarySearchTree.BaseNode node = tree.BinarySearchRecursive( tree.Root(), 363, false );
        //tree.Print( node );

        /*tree.InsertNodeIterative( 1, tree.Root() );
        tree.InsertNodeIterative( 2, tree.Root() );
        tree.InsertNodeIterative( 3, tree.Root() );
        tree.InsertNodeIterative( 4, tree.Root() );
        tree.InsertNodeIterative( 5, tree.Root() );
        tree.InsertNodeIterative( 6, tree.Root() );
        tree.InsertNodeIterative( 7, tree.Root() );
        tree.Print( null );

        tree.Rotate( false, 1 );
        tree.Print( null );*/

        //tree.CreateRandomTree( 4, 2, tree.Root() );
        //tree.Print( null );

        /*System.out.println( "Min is:" + tree.FindMinimum( tree.Root() ).Data() );
        System.out.println( "Max is:" + tree.FindMaximum( tree.Root() ).Data() );

        System.out.println( "Preorder" );
        tree.DepthFirstSearchIterative( tree.Root(), TreeBase.DFSType.ePreOrder, -1, true );

        System.out.println( "Inorder" );
        tree.DepthFirstSearchIterative( tree.Root(), TreeBase.DFSType.eInOrder, -1, true );

        System.out.println( "Postorder" );
        tree.DepthFirstSearchIterative( tree.Root(), TreeBase.DFSType.ePostOrder, -1, true );*/

        //TreeBase tree = CreateTree( TreeBase.TreeType.eBinaryTree );
        //TreeBase tree = new GenericTree<Integer>();
        //TreeBase tree = new BinarySearchTree<Integer>();
        //tree.CreateRandomTree( 3, 3, null );
       /* tree.InsertNodeIterative( 3, tree.Root() );
        tree.InsertNodeIterative( 2, tree.Root() );
        tree.InsertNodeIterative( 5, tree.Root() );
        tree.InsertNodeIterative( 4, tree.Root() );
        tree.InsertNodeIterative( 7, tree.Root() );
        tree.InsertNodeIterative( 6, tree.Root() );
        tree.InsertNodeIterative( 8, tree.Root() );
        tree.Print( null );*/
        /*System.out.println( "Minimum is:" + tree.FindMinimum( tree.Root() ).Data() );
        System.out.println( "Maximum is:" + tree.FindMaximum( tree.Root() ).Data() );
        System.out.println( "GetDepth root is:" + tree.GetDepth( tree.Root() ) );
        System.out.println( "GetDepth root first child is:" + tree.GetDepth( tree.Root().Child(1) ) );*/

        //tree.BreadthFirstSearchIterative( tree.Root(), 10, true );
    }
}
