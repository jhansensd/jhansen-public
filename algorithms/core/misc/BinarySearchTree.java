package core.complex.misc;


import java.util.Random;
import java.util.Stack;

/**
 * Created by jhansen on 5/24/2015.
 */
public class BinarySearchTree<Data extends Comparable<Data>> {

    private Node<Data> root;

    private class Node<Data> {
        private Data data;
        private Node<Data> left, right;

        public Node( Data data ) {
            this.data = data;
            left = right = null;
        }
    }

    public Node root() { return root; }
    public boolean isEmpty() { return root == null; }

    public int size( Node node ) {
        if ( node != null )
            return size( node.left ) + size( node.right ) + 1;

        return 0;
    }

    // Maximum height from this node.
    public int height( Node node ) {
        if ( node == null ) return -1;
        return Math.max( height( node.left), height( node.right ) ) + 1;
    }

    // How far from root to this node.
    public int level( Node<Data> node ) {
        return getLevelDepth( node, node.data, 1 );
    }

    private int getLevelDepth( Node node, Data data, int level ) {
        if ( node == null )
            return 0;

        if ( node.data.equals( data ) ) {
            return level;
        }

        int downLevel = getLevelDepth( node.left, data, level + 1 );
        if ( downLevel != 0 )
            return downLevel;

        downLevel = getLevelDepth( node.right, data, level + 1 );
        return downLevel;
    }

    public Node searchIter( Data data ) {
        Node<Data> tmp = root;

        while ( tmp != null ) {
            if ( data.equals( tmp.data ) )
                return tmp;
            else if ( data.compareTo( tmp.data ) < 0 )
                tmp = tmp.left;
            else if ( data.compareTo( tmp.data ) > 0 )
                tmp = tmp.right;
        }

        return null;
    }

    public Node searchRecur( Node<Data> node, Data data ) {
        if ( node == null )
            return null;

        if ( data.equals( node.data ) )
            return node;
        else if ( data.compareTo( node.data ) < 0 )
            return searchRecur( node.left, data );
        else if ( data.compareTo( node.data ) > 0 )
            return searchRecur( node.right, data );

        return null;
    }

    public void insertRecur( Node<Data> node, Data data ) {

        if ( data.compareTo( node.data ) <= 0 ) {
            if ( node.left == null )
                node.left = new Node<>( data );
            else
                insertRecur( node.left, data );
        }
        else if ( data.compareTo( node.data ) > 0 ) {
            if ( node.right == null )
                node.right = new Node<>( data );
            else
                insertRecur( node.right, data );
        }
    }

    public void insertIter( Data data ) {
        Node<Data> node = root;

        while ( node != null ) {
            if ( data.compareTo( node.data ) <= 0 ) {
                if ( node.left == null ) {
                    node.left = new Node<>( data );
                    return;
                }
                else
                    node = node.left;
            }
            else if ( data.compareTo( node.data ) > 0 ) {
                if ( node.right == null ) {
                    node.right = new Node<>( data );
                    return;
                }
                else
                    node = node.right;
            }
        }
    }

    public Node delete( Node<Data> node, Data data ) {
        if ( node == null ) return null;
        if ( data.compareTo( node.data ) < 0 )
            node.left = delete( node.left, data );
        else if ( data.compareTo( node.data ) > 0 )
            node.right = delete( node.right, data );
        else {
            if ( node.right == null ) return node.left;
            if ( node.left == null ) return node.right;
            Node tmp = node;
            node = leftMostChild( node.right );
            node.right = deleteMin( tmp.right );
            node.left = tmp.left;
        }
        return node;
    }

    private Node deleteMin( Node node ) {
        if (node.left == null) return node.right;
        node.left = deleteMin( node.left );
        return node;
    }

    public boolean isBST( Node<Data> node, Integer min, Integer max ) {
        if ( node == null )
            return true;

        if ( ( min != null && ((Integer)(node.data)).compareTo( min ) < 0 ) ||
             ( max != null && ((Integer)(node.data)).compareTo( max ) > 0 ) )
                return false;

        return isBST( node.left, min, (Integer)node.data ) &&
               isBST( node.right, (Integer)node.data, max );
    }

    public boolean isBalanced( Node node ) {
        if ( node == null ) return true;
        return ( Math.abs( height( node.left ) - height( node.right ) ) <= 1 ) &&
                 isBalanced( node.left ) && isBalanced( node.right );
    }

    public Node createMinimalBST( int arr[], int start, int end ) {
        if ( start > end )
            return null;

        int mid = (start + end) / 2;
        Node node = new Node( mid );
        node.left = createMinimalBST( arr, start, mid - 1 );
        node.right = createMinimalBST( arr, mid + 1, end );
        return node;
    }

    public int max( Node node ) {
        if ( node.right == null )
            return (int)node.data;

        return max( node.right );
    }

    public int min( Node node ) {
        if ( node.left == null )
            return (int)node.data;

        return max( node.left );
    }

    public int nthMax( Node<Data> node, int n ) {
        ListStack<Node> stack = new ListStack<>();

        while ( node != null ) {
            stack.push( node );
            node = node.right;
        }

        while ( (node = stack.pop()) != null ) {
            if ( --n == 0 ) {
                break;
            }

            if ( node.left != null ) {
                node = node.left;
                while ( node != null ) {
                    stack.push( node );
                    node = node.right;
                }
            }
        }

        return (Integer)node.data;
    }

    public Node successor( Node<Data> node ) {
        ListStack<Node> stack = new ListStack<>();
        Node<Data> tmp = node;
        stack.push( tmp );

        // Do a binary search through the tree to put parents
        // on the stack to the current node.
        tmp = pushSearchStack( stack, tmp );
        if ( tmp == null )
            return null; // Not found.

        if ( tmp.right != null )
            return leftMostChild( tmp.right );

        // Now find in order successor from here which is either:
        // A) Left most child of the right child if there is one.
        // B) Go up until we are a left child of the parent, then return parent.
        // C) Went to the top of the tree, return null.
        Node child = tmp;
        Node parent = stack.pop();
        while ( parent != null && parent.left != child ) {
            child = parent;
            parent = stack.pop();
        }

        return parent;
    }

    public Node predecessor( Node<Data> node ) {
        ListStack<Node> stack = new ListStack<>();
        Node<Data> tmp = node;
        stack.push( tmp );

        // Do a binary search through the tree to put parents
        // on the stack to the current node.
        tmp = pushSearchStack( stack, tmp );
        if ( tmp == null )
            return null; // Not found.

        if ( tmp.left != null )
            return rightMostChild( tmp.left );

        // Now find in order successor from here which is either:
        // A) Left most child of the right child if there is one.
        // B) Go up until we are a left child of the parent, then return parent.
        // C) Went to the top of the tree, return null.
        Node child = tmp;
        Node parent = stack.pop();
        while ( parent != null && parent.left == child ) {
            child = parent;
            parent = stack.pop();
        }

        return parent;
    }

    public Node pushSearchStack( ListStack<Node> stack, Node<Data> node ) {
        while ( node != null ) {
            node = stack.pop();
            if ( node.data.equals( node.data ) )
                return node;
            else if ( node.data.compareTo( node.data ) < 0 )
                stack.push( node.left );
            else if ( node.data.compareTo( node.data ) > 0 )
                stack.push( node.right );
        }

        return null;
    }

    public Node leftMostChild( Node node ) {
        if ( node == null )
            return null;

        if ( node.left != null ) {
            return leftMostChild( node.left );
        }

        return node;
    }

    public Node rightMostChild( Node node ) {
        if ( node == null )
            return null;

        if ( node.right != null ) {
            return rightMostChild( node.right );
        }

        return node;
    }

    public void dfsPreorderIter( Node<Data> node ) {
        ListStack<Node> stack = new ListStack<>();;

        while ( !stack.isEmpty() || node != null ) {
            if ( node != null ) {
                visit( node );
                if ( node.right != null )
                    stack.push( node.right );
                node = node.left;
            }
            else {
                node = stack.pop();
            }
        }
    }

    public void dfsInorderIter( Node<Data> node ) {
        ListStack<Node> stack = new ListStack<>();

        while ( !stack.isEmpty() || node != null ) {
            if ( node != null ) {
                stack.push( node );
                node = node.left;
            } else {
                node = stack.pop();
                visit( node );
                node = node.right;
            }
        }
    }

    public void dfsPostorderIter(  Node<Data> node ) {
        ListStack<Node> stack = new ListStack<>();
        Node lastVisited = null;

        while ( !stack.isEmpty() || node != null ) {
            if ( node != null ) {
                stack.push( node );
                node = node.left;
            } else {
                Node peekNode = stack.peek();
                if ( peekNode.right != null && lastVisited != peekNode.right ) {
                    node = peekNode.right;
                }
                else {
                    visit( peekNode );
                    lastVisited = stack.pop();
                }
            }
        }
    }

    public void dfsPreorderRecur( Node<Data> node ) {
        if ( node == null )
            return;

        visit( node );
        dfsInorderRecur( node.left );
        dfsPreorderRecur( node.right );
    }

    public void dfsInorderRecur( Node<Data> node ) {
        if ( node == null )
            return;

        dfsInorderRecur( node.left );
        visit( node );
        dfsInorderRecur( node.right );
    }

    public void dfsPostorderRecur( Node node ) {
        if ( node == null )
            return;

        dfsPostorderRecur( node.left );
        dfsPostorderRecur( node.right );
        visit( node );
    }

    public void breadthFirstSearch( Node node ) {
        ListQueue<Node> queue = new ListQueue<>();
        queue.enqueue( node );

        while ( !queue.isEmpty() ) {
            node = queue.dequeue();
            visit( node );

            if ( node.right != null )
                queue.enqueue( node.right );

            if ( node.left != null )
                queue.enqueue( node.left );
        }
    }

    private void visit( Node node ) {
        if ( node != null )
            System.out.print( node.data + " " );
    }

    private static Random randNum = new Random( 10000 );
    public Node createRandomTree( int depth ) {
        int numNodes = ( int ) Math.pow( 2, depth ) - 1;
        int numArray[] = new int[ numNodes ];

        for ( int i = 0; i < numNodes; i++ ) {
            Integer num = randNum.nextInt( 1000 );

            while ( searchRecur( root, (Data)num ) != null ) {
                num = randNum.nextInt( 1000 );
            }

            numArray[ i ] = num;
        }

        // Sort the array.
        java.util.Arrays.sort( numArray );

        root = makeTree( 0, numNodes - 1, numArray );
        return root;
    }

    private Node makeTree( int low, int high, int[] array )
    {
        if ( low > high ) {
            return null;
        } else {
            int mid = (low + high) >> 1;
            Node<Integer> node = new Node( new Integer( array[ mid ] ) );
            node.left = makeTree( low, ( mid - 1 ), array );
            node.right = makeTree( ( mid + 1 ), high, array );
            return node;
        }
    }

    public void print()
    {
        System.out.println( "digraph BinarySearchTree {" );
        printNode( root, null );
        System.out.println( "}" );
    }

    private void printNode( Node root, Node parent )
    {
        if ( root == null ) {
            return;
        }

        if ( parent != null ) {
            System.out.println( "\t" + parent.data + " -> " + root.data + ";" );
        }

        printNode( root.left, root );
        printNode( root.right, root );
    }

}
