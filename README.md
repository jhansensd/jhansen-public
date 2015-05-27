# Algorithms and Data Structures Repository
This repository contains basic data structures and algorithms implementations as prepared for a Google Interview. This information should help anyone out that is interviewing at Google or would like to look at some basic algorithm implementations. Amongst some of the most fun in this process was creating the adjacency list graph. In it I was able to apply some cool algorithms such as topological sort, Dijikstra's shortest path, and Primm's minimum spanning tree. Enjoy the use of this code. It was created in the IntelliJ ide but I have not moved those files over yet because this is just the code in a publically viewable format only.

The following describes the project main directories and files:
* [Core Algorithms](https://github.com/jhansensd/jhansen-public/tree/master/algorithms/core) - This is the core algorithms and data structures code directory. This contains the most up to date and complete view of the core code.
* [Firstpass Algorithms](https://github.com/jhansensd/jhansen-public/tree/master/algorithms/firstpass) - This contains the original source code that was created.
* [Secondpass Algorithms](https://github.com/jhansensd/jhansen-public/tree/master/algorithms/secondpass) - This contains a second pass at some of the algorithms and data structures.

## Core Algorithms File Descriptions
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
  * [AdjacencyGraph](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/AdjacencyGraph.java) - This contains an adjacency graph implementation. This contains Dijkstra's shortest path algorithm, Primm's algorithm for minimum spanning trees, cycle detection, preorder, postorder, and reverse postorder depth first search, and topological sort amongst other functionality.
  [AdjacencyMatrixGraph](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/AdjacencyMatrixGraph.java) - This contains an adjacency matrix graph implementation. In this file, breadth first search, Bellman ford, and Floyd Warshall (for negative weight paths) shortest path algorithms.
* [ArrayQueue](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/ArrayQueue.java) - This contains a queue implemented as an array containing basic enqueue, dequeue, and peek operations.
* [ArrayStack](https://github.com/jhansensd/jhansen-public/blob/master/algorithms/core/misc/ArrayStack.java) - This contains an queue implemented as an array supporting basic push, pop, and peek operations.
* [BinaryMinHeap]
* [BinarySearchTree]
* [Bits]
* [LinkedHash]
* [LinkedHashEntry]
* [ListNode]
* [ListQueue]
* [ListStack]
* [QueueStack]
* [StackQueue]

