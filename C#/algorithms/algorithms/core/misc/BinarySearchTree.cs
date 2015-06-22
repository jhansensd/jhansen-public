using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms.core.misc {
    class BinarySearchTree<Data> {
        private Node<Data> root;

        internal class Node<Data> {
            internal Data data;
            internal Node<Data> left, right;

            public Node(Data data) {
                this.data = data;
                left = right = null;
            }
        }

        public Node<Data> Root { get; set; }
        public bool isEmpty() { return root == null; }

        public int size(Node<Data> node) {
            if (node != null)
                return size(node.left) + size(node.right) + 1;

            return 0;
        }

        // Maximum height from this node.
        public int height(Node<Data> node) {
            if (node == null) return -1;
            return Math.Max(height(node.left), height(node.right)) + 1;
        }

        // How far from root to this node.
        public int level(Node<Data> node) {
            return getLevelDepth(node, node.data, 1);
        }

        private int getLevelDepth(Node<Data> node, Data data, int level) {
            if (node == null)
                return 0;

            if (node.data.Equals(data)) {
                return level;
            }

            int downLevel = getLevelDepth(node.left, data, level + 1);
            if (downLevel != 0)
                return downLevel;

            downLevel = getLevelDepth(node.right, data, level + 1);
            return downLevel;
        }

        public Node<Data> searchIter(Data data) {
            Node<Data> tmp = root;

            while (tmp != null) {
                if (data.Equals(tmp.data))
                    return tmp;
                else if (data.CompareTo(tmp.data) < 0)
                    tmp = tmp.left;
                else if (data.compareTo(tmp.data) > 0)
                    tmp = tmp.right;
            }

            return null;
        }

        public Node<Data> searchRecur(Node<Data> node, Data data) {
            if (node == null)
                return null;

            if (data.Equals(node.data))
                return node;
            else if (data.compareTo(node.data) < 0)
                return searchRecur(node.left, data);
            else if (data.compareTo(node.data) > 0)
                return searchRecur(node.right, data);

            return null;
        }

        public void insertRecur(Node<Data> node, Data data) {

            if (data.compareTo(node.data) <= 0) {
                if (node.left == null)
                    node.left = new Node<Data>(data);
                else
                    insertRecur(node.left, data);
            } else if (data.compareTo(node.data) > 0) {
                if (node.right == null)
                    node.right = new Node<Data>(data);
                else
                    insertRecur(node.right, data);
            }
        }

        public void insertIter(Data data) {
            Node<Data> node = root;

            while (node != null) {
                if (data.compareTo(node.data) <= 0) {
                    if (node.left == null) {
                        node.left = new Node<Data>(data);
                        return;
                    } else
                        node = node.left;
                } else if (data.compareTo(node.data) > 0) {
                    if (node.right == null) {
                        node.right = new Node<Data>(data);
                        return;
                    } else
                        node = node.right;
                }
            }
        }

        public Node<Data> delete(Node<Data> node, Data data) {
            if (node == null) return null;
            if (data.compareTo(node.data) < 0)
                node.left = delete(node.left, data);
            else if (data.compareTo(node.data) > 0)
                node.right = delete(node.right, data);
            else {
                if (node.right == null) return node.left;
                if (node.left == null) return node.right;
                Node<Data> tmp = node;
                node = leftMostChild(node.right);
                node.right = deleteMin(tmp.right);
                node.left = tmp.left;
            }
            return node;
        }

        private Node<Data> deleteMin(Node<Data> node) {
            if (node.left == null) return node.right;
            node.left = deleteMin(node.left);
            return node;
        }

        public bool isBST(Node<Data> node, int min, int max) {
            if (node == null)
                return true;

            if ((min != null && ((Integer)(node.data)).compareTo(min) < 0) ||
                 (max != null && ((Integer)(node.data)).compareTo(max) > 0))
                return false;

            return isBST(node.left, min, (Integer)node.data) &&
                   isBST(node.right, (Integer)node.data, max);
        }

        public bool isBalanced(Node<Data> node) {
            if (node == null) return true;
            return (Math.Abs(height(node.left) - height(node.right)) <= 1) &&
                     isBalanced(node.left) && isBalanced(node.right);
        }

        public Node<Data> createMinimalBST(int arr[], int start, int end) {
            if (start > end)
                return null;

            int mid = (start + end) / 2;
            Node<Data> node = new Node<Data>(mid);
            node.left = createMinimalBST(arr, start, mid - 1);
            node.right = createMinimalBST(arr, mid + 1, end);
            return node;
        }

        public int max(Node<Data> node) {
            if (node.right == null)
                return (int)node.data;

            return max(node.right);
        }

        public int min(Node<Data> node) {
            if (node.left == null)
                return (int)node.data;

            return max(node.left);
        }

        public int nthMax(Node<Data> node, int n) {
            ListStack<Node<Data>> stack = new ListStack<Node<Data>>();

            while (node != null) {
                stack.push(node);
                node = node.right;
            }

            while ((node = stack.pop()) != null) {
                if (--n == 0) {
                    break;
                }

                if (node.left != null) {
                    node = node.left;
                    while (node != null) {
                        stack.push(node);
                        node = node.right;
                    }
                }
            }

            return (Integer)node.data;
        }

        public Node<Data> successor(Node<Data> node) {
            ListStack<Node<Data>> stack = new ListStack<Node<Data>>();
            Node<Data> tmp = node;
            stack.push(tmp);

            // Do a binary search through the tree to put parents
            // on the stack to the current node.
            tmp = pushSearchStack(stack, tmp);
            if (tmp == null)
                return null; // Not found.

            if (tmp.right != null)
                return leftMostChild(tmp.right);

            // Now find in order successor from here which is either:
            // A) Left most child of the right child if there is one.
            // B) Go up until we are a left child of the parent, then return parent.
            // C) Went to the top of the tree, return null.
            Node<Data> child = tmp;
            Node<Data> parent = stack.pop();
            while (parent != null && parent.left != child) {
                child = parent;
                parent = stack.pop();
            }

            return parent;
        }

        public Node<Data> predecessor(Node<Data> node) {
            ListStack<Node<Data>> stack = new ListStack<Node<Data>>();
            Node<Data> tmp = node;
            stack.push(tmp);

            // Do a binary search through the tree to put parents
            // on the stack to the current node.
            tmp = pushSearchStack(stack, tmp);
            if (tmp == null)
                return null; // Not found.

            if (tmp.left != null)
                return rightMostChild(tmp.left);

            // Now find in order successor from here which is either:
            // A) Left most child of the right child if there is one.
            // B) Go up until we are a left child of the parent, then return parent.
            // C) Went to the top of the tree, return null.
            Node<Data> child = tmp;
            Node<Data> parent = stack.pop();
            while (parent != null && parent.left == child) {
                child = parent;
                parent = stack.pop();
            }

            return parent;
        }

        public Node<Data> pushSearchStack(ListStack<Node<Data>> stack, Node<Data> node) {
            while (node != null) {
                node = stack.pop();
                if (node.data.Equals(node.data))
                    return node;
                else if (node.data.compareTo(node.data) < 0)
                    stack.push(node.left);
                else if (node.data.compareTo(node.data) > 0)
                    stack.push(node.right);
            }

            return null;
        }

        public Node<Data> leftMostChild(Node<Data> node) {
            if (node == null)
                return null;

            if (node.left != null) {
                return leftMostChild(node.left);
            }

            return node;
        }

        public Node<Data> rightMostChild(Node<Data> node) {
            if (node == null)
                return null;

            if (node.right != null) {
                return rightMostChild(node.right);
            }

            return node;
        }

        public void dfsPreorderIter(Node<Data> node) {
            ListStack<Node<Data>> stack = new ListStack<Node<Data>>(); ;

            while (!stack.isEmpty() || node != null) {
                if (node != null) {
                    visit(node);
                    if (node.right != null)
                        stack.push(node.right);
                    node = node.left;
                } else {
                    node = stack.pop();
                }
            }
        }

        public void dfsInorderIter(Node<Data> node) {
            ListStack<Node<Data>> stack = new ListStack<Node<Data>>();

            while (!stack.isEmpty() || node != null) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    node = stack.pop();
                    visit(node);
                    node = node.right;
                }
            }
        }

        public void dfsPostorderIter(Node<Data> node) {
            ListStack<Node<Data>> stack = new ListStack<Node<Data>>();
            Node<Data> lastVisited = null;

            while (!stack.isEmpty() || node != null) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    Node<Data> peekNode = stack.peek();
                    if (peekNode.right != null && lastVisited != peekNode.right) {
                        node = peekNode.right;
                    } else {
                        visit(peekNode);
                        lastVisited = stack.pop();
                    }
                }
            }
        }

        public void dfsPreorderRecur(Node<Data> node) {
            if (node == null)
                return;

            visit(node);
            dfsInorderRecur(node.left);
            dfsPreorderRecur(node.right);
        }

        public void dfsInorderRecur(Node<Data> node) {
            if (node == null)
                return;

            dfsInorderRecur(node.left);
            visit(node);
            dfsInorderRecur(node.right);
        }

        public void dfsPostorderRecur(Node<Data> node) {
            if (node == null)
                return;

            dfsPostorderRecur(node.left);
            dfsPostorderRecur(node.right);
            visit(node);
        }

        public void breadthFirstSearch(Node<Data> node) {
            ListQueue<Node<Data>> queue = new ListQueue<Node<Data>>();
            queue.enqueue(node);

            while (!queue.isEmpty()) {
                node = queue.dequeue();
                visit(node);

                if (node.right != null)
                    queue.enqueue(node.right);

                if (node.left != null)
                    queue.enqueue(node.left);
            }
        }

        private void visit(Node<Data> node) {
            if (node != null)
                Console.WriteLine(node.data + " ");
        }

        private static Random randNum = new Random(10000);
        public Node<Data> createRandomTree(int depth) {
            int numNodes = (int)Math.Pow(2, depth) - 1;
            int[] numArray = new int[numNodes];

            for (int i = 0; i < numNodes; i++) {
                Integer num = randNum.nextInt(1000);

                while (searchRecur(root, (Data)num) != null) {
                    num = randNum.nextInt(1000);
                }

                numArray[i] = num;
            }

            // Sort the array.
            java.util.Arrays.sort(numArray);

            root = makeTree(0, numNodes - 1, numArray);
            return root;
        }

        private Node<int> makeTree(int low, int high, int[] array) {
            if (low > high) {
                return null;
            } else {
                int mid = (low + high) >> 1;
                Node<int> node = new Node<int>(new int(array[mid]));
                node.left = makeTree(low, (mid - 1), array);
                node.right = makeTree((mid + 1), high, array);
                return node;
            }
        }

        public void print() {
            Console.WriteLine("digraph BinarySearchTree {");
            printNode(root, null);
            Console.WriteLine("}");
        }

        private void printNode(Node<Data> root, Node<Data> parent) {
            if (root == null) {
                return;
            }

            if (parent != null) {
                Console.WriteLine("\t" + parent.data + " -> " + root.data + ";");
            }

            printNode(root.left, root);
            printNode(root.right, root);
        }
    }
}
