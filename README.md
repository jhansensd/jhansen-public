# Algorithms and Data Structures Repository
This repository contains basic data structures and algorithms implementations as prepared for a Google Interview. This repo should help anyone interviewing at Google or who would like to look at basic algorithm implementations. Amongst some of the most fun in this process was creating the adjacency list graph. In it I was able to apply algorithms such as topological sort, Dijikstra's and Bellman Ford single source shortest path, Floyd Warshall all-pairs shortest path, and Primm's minimum spanning tree. Enjoy the use of this code. The below graph implementations support output in GraphViz dot format so that you can view the graphs in a visual tool. This works with both directed and undirected graphs.

The following describes the project main directories and files:
* [Core Algorithms](https://github.com/jhansensd/jhansen-public/tree/master/algorithms/core) - This is the core algorithms and data structures code directory. This contains the most up to date and complete view of the core code.
* [Firstpass Algorithms](https://github.com/jhansensd/jhansen-public/tree/master/algorithms/firstpass) - This contains the original source code that was created.
* [Secondpass Algorithms](https://github.com/jhansensd/jhansen-public/tree/master/algorithms/secondpass) - This contains a second pass at some of the algorithms and data structures.

## Core Algorithm Descriptions
* Array and String Implementations (core/arraystrings)
  * [ArrayStrings](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/arraystrings/ArrayStrings.java) - This object contains methods to perform binary search and to search for weather or not a string is an anagram or a permutation of another string.
  * [Combinatorics](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/arraystrings/Combinatorics.java) - This object contains the core code to handle calculating combinations and permutations of strings.
  * [Sorting](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/arraystrings/Sorting.java) - The sorting file contains all of the standard 0(nlogn) and 0(n^2) sorting algorithms (insertion, selection, buttble, radix, heapsort, quicksort, as well as merging and partitioning functions.

* Linked List Implementations (core/lists)
  * [CircularDLinkedList](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/lists/CircularDLinkedList.java) - This is a circular doubly linked list that contains insertion/deletion 0(1) and 0(n) operations for insertions and deletion.
  * [CircularSLinkedList](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/lists/CircularSLinkedList.java) - This is a circular sinly linked list that contains insertion/deletion 0(1) and 0(n) operations for insertions and deletion.
  * [DoublyLinkedList](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/lists/DoublyLinkedList.java) - - This is a doubly linked list that contains insertion/deletion 0(1) and 0(n) operations for insertions and deletion.
  * [DoublyTailLinkedList](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/lists/DoublyTailLinkedList.java) - - This is a doubly linked list which tracks a tail pointer and contains insertion/deletion 0(1) and 0(n) operations for insertions and deletion.
  * [SinglyLinkedList](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/lists/SinglyLinkedList.java) - - This is a singly linked list that contains insertion/deletion 0(1) and 0(n) operations for insertions and deletion.
  * [SinglyTailLinkedList](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/lists/SinglyTailLinkedList.java) - This is a singly linked list which tracks a tail pointer and contains insertion/deletion 0(1) and 0(n) operations for insertions and deletion.

* Miscelaneous Impementations (core/misc)
  * [AdjacencyGraph](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/AdjacencyGraph.java) - This contains an adjacency graph implementation that supports directed, undirected, and weighted graphs that can store arbitrary data at each node. Contains Dijkstra's shortest path algorithm, Primm's algorithm for minimum spanning trees, cycle detection, preorder, postorder, and reverse postorder depth first search, and topological sort amongst other functionality.
  [AdjacencyMatrixGraph](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/AdjacencyMatrixGraph.java) - This contains an adjacency matrix graph implementation that supports directed, undirected, and weighted graphs that can store arbitrary data at each node. In this file, breadth first search, Bellman ford, and Floyd Warshall (for negative weight paths) shortest path algorithms ,Primm's minimum spanning tree algorithms.
* [ArrayQueue](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/ArrayQueue.java) - Contains a queue implemented as an array containing basic enqueue, dequeue, and peek operations.
* [ArrayStack](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/ArrayStack.java) - Contains an queue implemented as an array supporting basic push, pop, and peek operations.
* [BinaryMinHeap](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/BinaryMinHeap.java) - Contains a binary min heap priority queue implementation built on top of an array with 1 based indexing (for ease of swim and sink of 2*n and n/2) that supports basics insertion/removal operations.
* [BinarySearchTree](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/BinarySearchTree.java) - Contains a binary search tree implementation which includes methods for creating a minimal Binary Search Tree, checking levels, and most notably iterative and breadth first search and iterative and recursive depth first search functionality.
* [Bits](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/Bits.java) - Contains core bit manipulation operations such as clearing, setting, flipping, retrieving, and clearing bit ranges.
* [LinkedHash](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/LinkedHash.java) - Contains a linked hash table that implements basic getting and setting functionality in average case 0(1) when there are no collisions.
* [LinkedHashEntry](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/LinkedHashEntry.java) - Contains an entry in the above LinkedHash table which contains a genric key and value using.
* [ListNode](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/ListNode.java) - Contains a list node that implements an Iterator.
* [ListQueue](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/ListQueue.java) - Contains a queue implemented via a list node.
* [ListStack](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/ListStack.java) - Contains a stack implemented via a list node.
* [QueueStack](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/QueueStack.java) - Contains an interesting implementation of a stack implemented with two queues supporting enqueue/dequeue functionality.
* [StackQueue](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/StackQueue.java) - Contains an interesting implementation of a queue implemented with two stacks containing push/pop functionality.

