package core.complex;

import core.complex.arraystrings.ArrayStrings;
import core.complex.misc.*;
import core.complex.arraystrings.Combinatorics;
import core.complex.arraystrings.Sorting;
import core.complex.lists.DoublyLinkedList;
import core.complex.lists.SinglyLinkedList;

/**
 * Created by jhansen on 5/22/2015.
 */
public class Testing
{
    public static void Run( String in ) {
        //ListFunctions();
        //ArrayStringFunctions();
        //CombinatoricFunctions( in );
        //BitFunctions( in );
        //LinkedHashFunctions();
        //StackFunctions();
        //QueueFunctions();
        //HeapFunctions();
        //SortingFunctions();
        //BinarySearchTreeFunctions();
        GraphFunctions();
    }

    public static void ListFunctions() {
        // Single Linked List, head only.
        System.out.println( "Single linked list, head only." );
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.insertFirst( 25 );
        list1.insertFirst( 45 );
        list1.insertFirst( 29 );
        list1.insertFirst( 135 );
        list1.insertLast( 99 );
        list1.print();
        list1.reverseIter();
        list1.print();
        list1.reverseRecur( list1.head() );
        list1.print();
        /*list1.print();
        list1.deleteNode( list1.search( 25 ) );
        list1.print();
        list1.insertAfter( list1.search( 29 ), 66 );
        list1.print();
        list1.deleteFirst();
        list1.print();
        list1.deleteLast();
        list1.print();*/

        System.out.println();


        // Singly Linked List, head and tail.
        /*System.out.println( "Singly linked list, head and tail." );
        SinglyTailLinkedList<Integer> list2 = new SinglyTailLinkedList<>();
        list2.insertFirst( 25 );
        list2.insertFirst( 45 );
        list2.insertFirst( 29 );
        list2.insertFirst( 135 );
        list2.insertLast( 99 );
        list2.print();
        list2.deleteNode( list2.search( 25 ) );
        list2.print();
        list2.insertAfter( list2.search( 29 ), 66 );
        list2.print();
        list2.deleteFirst();
        list2.print();
        list2.deleteLast();
        list2.print();

        System.out.println();*/

        // Doubly Linked List, head only.
        System.out.println( "Doubly linked list, head only." );
        DoublyLinkedList<Integer> list3 = new DoublyLinkedList<>();
        list3.insertFirst( 25 );
        list3.insertFirst( 45 );
        list3.insertFirst( 29 );
        list3.insertFirst( 135 );
        list3.insertLast( 99 );
        /*list3.print();
        list3.deleteNode( list3.search( 25 ) );
        list3.print();
        list3.insertAfter( list3.search( 29 ), 66 );
        list3.print();
        list3.deleteFirst();
        list3.print();
        list3.deleteLast();*/
        list3.print();
        list3.reverseIter();
        list3.print();
        list3.reverseRecur( list3.head() );
        list3.print();

        System.out.println();

        // Doubly Linked List, head and tail.
        /*System.out.println( "Doubly linked list, head and tail." );
        DoublyTailLinkedList<Integer> list4 = new DoublyTailLinkedList<>();
        list4.insertFirst( 25 );
        list4.insertFirst( 45 );
        list4.insertFirst( 29 );
        list4.insertFirst( 135 );
        list4.insertLast( 99 );
        list4.print();
        list4.deleteNode( list4.search( 25 ) );
        list4.print();
        list4.insertAfter( list4.search( 29 ), 66 );
        list4.print();
        list4.deleteFirst();
        list4.print();
        list4.deleteLast();
        list4.print();

        System.out.println();

        // Circular Singly Linked List, head and tail.
        System.out.println( "Circular Singly linked list, head and tail." );
        CircularSLinkedList<Integer> list5 = new CircularSLinkedList<>();
        list5.insertFirst( 25 );
        list5.insertFirst( 45 );
        list5.insertFirst( 29 );
        list5.insertFirst( 135 );
        list5.insertLast( 99 );
        list5.print();
        list5.deleteNode( list5.search( 25 ) );
        list5.print();
        list5.insertAfter( list5.search( 29 ), 66 );
        list5.print();
        list5.deleteFirst();
        list5.print();
        list5.deleteLast();
        list5.print();

        System.out.println();

        // Circular Singly Linked List, head and tail.
        System.out.println( "Circular Singly linked list, head and tail." );
        CircularDLinkedList<Integer> list6 = new CircularDLinkedList<>();
        list6.insertFirst( 25 );
        list6.insertFirst( 45 );
        list6.insertFirst( 29 );
        list6.insertFirst( 135 );
        list6.insertLast( 99 );
        list6.print();
        list6.deleteNode( list6.search( 25 ) );
        list6.print();
        list6.insertAfter( list6.search( 29 ), 66 );
        list6.print();
        list6.deleteFirst();
        list6.print();
        list6.deleteLast();
        list6.deleteLast();
        list6.print();*/
    }

    public static void SortingFunctions() {
        int arr1[] = { 43, 23, 99, 64, 12, 16, 66, 23, 121, 32 };
        System.out.println( "Bubble Sorting" );
        Sorting.bubbleSort( arr1 );
        for ( int i : arr1 )
            System.out.print( i + " " );
        System.out.println();

        int arr2[] = { 43, 23, 99, 64, 12, 16, 66, 23, 121, 32 };
        System.out.println( "Insertion Sorting" );
        Sorting.insertionSort( arr2 );
        for ( int i : arr2 )
            System.out.print( i + " " );
        System.out.println();

        int arr3[] = { 43, 23, 99, 64, 12, 16, 66, 23, 121, 32 };
        System.out.println( "Selection Sorting" );
        Sorting.insertionSort( arr3 );
        for ( int i : arr3 )
            System.out.print( i + " " );
        System.out.println();

        int arr4[] = { 43, 23, 99, 64, 12, 16, 66, 23, 121, 32 };
        System.out.println( "Radix Sorting" );
        Sorting.insertionSort( arr4 );
        for ( int i : arr4 )
            System.out.print( i + " " );
        System.out.println();

        Integer arr5[] = { 43, 23, 99, 64, 12, 16, 66, 23, 121, 32 };
        System.out.println( "Heap Sorting" );
        Sorting.heapSort( arr5 );
        for ( int i : arr5 )
            System.out.print( i + " " );
        System.out.println();

        int arr6[] = { 43, 23, 99, 64, 12, 425, 16, 66, 88, 23, 121, 32 };
        System.out.println( "Quick Sorting" );
        Sorting.quickSort( arr6, 0, arr6.length - 1 );
        for ( int i : arr6 )
            System.out.print( i + " " );
        System.out.println();

        int arr7[] = { 43, 23, 99, 64, 12, 425, 16, 66, 88, 23, 121, 32 };
        System.out.println( "Merge Sorting" );
        Sorting.mergeSort( arr7, 0, arr7.length - 1 );
        for ( int i : arr7 )
            System.out.print( i + " " );
        System.out.println();
    }

    public static void ArrayStringFunctions() {
        // Binary Search
        int arr1[] = { 43, 23, 25, 64, 12, 16, 66, 23, 121, 32 };
        System.out.println( "Binary Search Iter for 54 is:" + ArrayStrings.BinarySearchIter( arr1, 54, 0, arr1.length ) );
        System.out.println( "Binary Search Iter for 66 is:" + ArrayStrings.BinarySearchIter( arr1, 66, 0, arr1.length ) );
        System.out.println( "Binary Search Recur for 54 is:" + ArrayStrings.BinarySearchRecur( arr1, 54, 0, arr1.length ) );
        System.out.println( "Binary Search Recur for 66 is:" + ArrayStrings.BinarySearchRecur( arr1, 66, 0, arr1.length ) );

        /*
        // Anagrams.
        String s1 = "abcdefg";
        String s2 = "bacdefg";
        String s3 = "gaedcfb";
        String s4 = "bacdwfg";
        System.out.println( s1 + " and " + s2 + " IsAnagram1():" + ArrayStrings.isAnagram1( s1, s2 ) );
        System.out.println( s2 + " and " + s3 + " IsAnagram1():" + ArrayStrings.isAnagram1( s2, s3 ) );
        System.out.println( s3 + " and " + s4 + " IsAnagram1():" + ArrayStrings.isAnagram1( s3, s4 ) );
        System.out.println( s1 + " and " + s2 + " IsAnagram2():" + ArrayStrings.isAnagram2( s1, s2 ) );
        System.out.println( s2 + " and " + s3 + " IsAnagram2():" + ArrayStrings.isAnagram2( s2, s3 ) );
        System.out.println( s3 + " and " + s4 + " IsAnagram2():" + ArrayStrings.isAnagram2( s3, s4 ) );
        */
    }

    public static void CombinatoricFunctions( String in ) {
        String strs[] = in.split( " " );
        String set = strs[0];
        int k = Integer.parseInt( strs[1] );
        int count = 0;

        // Combinatorics.
        Combinatorics com = new Combinatorics();

        System.out.println( "nCk Combinations of " + set.length() + "P" + k + ": " + in + " are:" );
        com.Init( set );
        count = com.StringCombinations( 0, k );
        com.PrintSet();
        System.out.println( "Count:" + count + " combinations when choosing "
                            + k + " items from a set of " + set.length() + "." );

        System.out.println( "nPk Permutations of " + set.length() + "P" + k + ": " + set + " are:" );
        com.Init( set );
        count = com.StringPermutations( k );
        com.PrintSet();
        System.out.println( "Count:" + count + " permutations when choosing "
                            + k + " items from a set of " + set.length() + "." );
    }

    public static void BitFunctions( String in ) {
        Bits bit = new Bits( Integer.parseInt( in ) );

        bit.Print();
        System.out.println( "Number of on bits is: " + bit.CountOnBits() );
        System.out.println( "GetBit 3: " + bit.GetBit( 3 ) );
        bit.FlipBit( 3 );
        bit.Print();
        bit.SetBit( 5 );
        bit.Print();
        bit.ClearBit( 5 );
        bit.Print();
        bit.UpdateBit( 5, 0 );
        bit.Print();
        bit.UpdateBit( 5, 1 );
        bit.Print();
        System.out.println();

        bit.SetVal( -1 );
        bit.Print();
        bit.ClearBitRange( 4, 8 );
        bit.Print();
        System.out.println();

        bit.SetVal( -1 );
        bit.Print();
        bit.ClearHighBits( 27 );
        bit.Print();
        System.out.println();

        bit.SetVal( -1 );
        bit.ClearLowBits( 3 );
        bit.Print();
    }

    public static void LinkedHashFunctions() {
        String key1 = "key1";
        String key2 = "key2";
        String key3 = "key3";

        System.out.println( "Creating linked list hash." );
        LinkedHash<String, Integer> hash = new LinkedHash<>( 10 );
        hash.put( key1, new Integer( 244 ) );
        hash.put( key2, new Integer( 13 ) );
        hash.put( key3, new Integer( 94 ) );

        System.out.println( "Hash retrieval " + key1 + ": " + hash.get( key1 ) );
        System.out.println( "Hash retrieval " + key2 + ": " + hash.get( key2 ) );
        System.out.println( "Hash retrieval " + key3 + ": " + hash.get( key3 ) );
        hash.print();
        hash.remove( key2 );
        hash.print();


        /*for ( LinkedHashEntry<String, Integer> entry : hash ) {
            System.out.println( "Hash (Key:" + entry.Key() + " Value:" + entry.Value() + ")" );
        }*/
    }

    private static void StackFunctions() {
        // List Stack.
        /*ListStack stack = new ListStack();
        stack.push( 1 );
        stack.push( 2 );
        stack.push( 3 );
        stack.push( 4 );
        stack.push( 5 );
        stack.print();
        System.out.println( "stacksize:" + stack.Size() );
        System.out.println( "stackStack pop pop:" + stack.pop() + " " + stack.pop() );
        stack.print();
        System.out.println( "stacksize:" + stack.Size() );*/

        // Array Stack.
        ArrayStack stack = new ArrayStack( 10 );
        stack.push( 1 );
        stack.push( 2 );
        stack.push( 3 );
        stack.push( 4 );
        stack.push( 5 );
        stack.print();
        System.out.println( "stacksize:" + stack.Size() );
        System.out.println( "stackStack pop pop:" + stack.pop() + " " + stack.pop() );
        stack.print();
        System.out.println( "stacksize:" + stack.Size() );
    }

    private static void QueueFunctions() {
        // ListQueue
        ListQueue<Integer> listQueue = new ListQueue<>();
        listQueue.enqueue( 1 );
        listQueue.enqueue( 2 );
        listQueue.enqueue( 3 );
        listQueue.enqueue( 4 );
        listQueue.enqueue( 5 );
        listQueue.print();
        listQueue.dequeue();
        int val = listQueue.dequeue();
        listQueue.print();
        System.out.println( "Dequeued val is:" + val );
        System.out.println();

        // ArrayQueue
        ArrayQueue<Integer> myQueue = new ArrayQueue<>( 100 );
        myQueue.enqueue( 1 );
        myQueue.enqueue( 2 );
        myQueue.enqueue( 3 );
        myQueue.enqueue( 4 );
        myQueue.enqueue( 5 );
        myQueue.print();
        myQueue.dequeue();
        val = myQueue.dequeue();
        myQueue.print();
        System.out.println( "Dequeued val is:" + val );
    }

    private static void HeapFunctions() {
        Integer arr[] = { 32, 54, 642, 23, 654, 123, 12, 54 };
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<Integer>( arr );
        heap.print();
        heap.delMin();
        heap.delMin();
        heap.print();
        heap.insert( 44 );
        heap.print();
        heap.delMin();
        heap.delMin();
        heap.delMin();
    }

    private static void BinarySearchTreeFunctions() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.createRandomTree( 4 );
        tree.print();
        /*System.out.println( "Tree is balanced:" + tree.isBalanced( tree.root() ) );
        System.out.println( "Tree height is:" + tree.height( tree.root() ) );
        System.out.println( "Is BST:" + tree.isBST( tree.root(), null, null ) );
        System.out.println( "tree max:" + tree.max( tree.root() ) );
        System.out.println( "tree min:" + tree.min( tree.root() ) );
        System.out.println( "nth:2 max:" + tree.nthMax( tree.root(), 2 ) );
        System.out.println( "inserting node iter with 223:" );
        tree.insertIter( 223 );
        System.out.println( "inserting node recur with 432:" );
        tree.insertRecur( tree.root(), 431 );

        System.out.println( "deleting node with 727:" );
        tree.delete( tree.root(), 727 );
        tree.print();*/
        System.out.println( "dfsPreorderIter():" );
        tree.dfsPreorderIter( tree.root() );
        System.out.println();
        System.out.println( "dfsInorderIter():" );
        tree.dfsInorderIter( tree.root() );
        System.out.println();
        System.out.println( "dfsPostorderIter():" );
        tree.dfsPostorderIter( tree.root() );
        System.out.println();

        System.out.println( "dfsPreorderRecur():" );
        tree.dfsPreorderRecur( tree.root() );
        System.out.println();
        System.out.println( "dfsInorderRecur():" );
        tree.dfsInorderRecur( tree.root() );
        System.out.println();
        System.out.println( "dfsPostorderRecur():" );
        tree.dfsPostorderRecur( tree.root() );
        System.out.println();
    }

    public static void GraphFunctions()
    {}
}
