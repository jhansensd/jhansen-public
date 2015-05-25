package core.complex.misc;

import practiceruns.Utility;

import java.util.*;

/**
 * Created by jhansen on 5/24/2015.
 */
public class AdjacencyGraph<Data extends Comparable<Data>> {

    private boolean          directed;
    private final int        V; // This won't change.
    private int              E;
    private Node             nodes[];
    private LinkedList<Edge> edges[];
    private LinkedList<Integer> cycle;
    private LinkedList<Integer>   preorder;   // Vertices in preorder.
    private LinkedList<Integer>   postorder;  // Vertices in postorder.

    public class Node {
        Data    data;
        boolean visited; // Visited status.
        int     path; // Stores parent-child paths.
        int     dist; // Stores distances between a source and dest nodes.
    }

    public class Edge {
        int u; // Source vert.
        int v; // Destination vert.
        int weight;
    }

    private static int MAX_WEIGHT = 50;
    private static Random generator = new Random( 1000 );

    private AdjacencyGraph( boolean directed, int V ) {
        this.directed = directed;
        this.V = V;
        this.E = 0;
        edges = new LinkedList[ V ];
        nodes = (Node[])new Object[ V ];
        cycle = new LinkedList<>();
        preorder = new LinkedList<>();
        postorder = new LinkedList<>();
        clearPathData();
        clearOrder();
    }

    public AdjacencyGraph( boolean directed, int V, int E ) {
        this( directed, V );

        while ( this.E < E ) {
            int x = generator.nextInt( V );
            int y = generator.nextInt( V );
            int weight = generator.nextInt( MAX_WEIGHT );
            /*if ( x == y || edges( x, y ) != Integer.MAX_VALUE || edges( y, x ) != Integer.MAX_VALUE ) {
                continue;
            }*/
            //InsertEdge( x, y, weight );
        }

        // Store strings in all the nodes.
        for ( int i = 0; i < nodes.length; i++ ) {
            nodes[ i ].data = (Data)Utility.RandWord( generator.nextFloat() );
        }
    }

    void clearPathData( boolean topsort ) {
        for ( int i = 0; i < V; i++ ) {
            nodes[i].data = null;
            if ( topsort == false )
                nodes[i].visited = false;
            nodes[i].path = Integer.MAX_VALUE;
            nodes[i].dist = Integer.MAX_VALUE;
        }
    }

    // Graph properties.
    public boolean directed() { return directed; }
    public int V() { return V; }
    public int E() { return E; }

    // Edge properties.
    public Edge edge( int x, int y ) { return edges[x].get( y ); }
    public int src( int x, int y ) { return edges[x].get( y ).u; }
    public int dst( int x, int y ) { return edges[x].get( y ).v; }
    public int weight( int x, int y ) { return edges[x].get( y ).weight; }

    public void edge( int x, int y, Edge edge ) { edges[x].set( y, edge ); }
    public void src( int x, int y, int src ) { edges[x].get( y ).u = src; }
    public void dst( int x, int y, int dst ) { edges[x].get( y ).v = dst; }
    public void weight( int x, int y, int weight ) { edges[x].get( y ).weight = weight; }

    public int link( int u, int v ) {

        for ( int i = 0; i < edges[ u ].size(); i++ ) {
            if ( dst( u, i ) == v )
                return i;
        }

        return -1;
    }

    public void insertEdge( int x, int y, int weight, boolean directed ) {
        Edge edge = new Edge();
        if ( edges[x] == null ) {
            edges[x] = new LinkedList();
        }
        edge.weight = weight;
        edges[x].push( edge );

        if ( !directed )
            insertEdge( x, y, weight, true );
        else
            ++E;
    }

    // Not removing any nodes.
    public void removeEdge( int x, int y ) {
        edges[x].remove( link( x, y ) );
        --E;

        if ( !directed )
            edges[y].remove( link( y, x ) );
    }

    // Only check for directed graphs.
    public boolean acyclic() {
        if ( !directed )
            return false;

        cycle.clear();
        for ( int i = 0; i < V; i++ )
            dfsIterative( i, null, true );

        if ( cycle.isEmpty() )
            return true;

        return false;
    }

    public int[] neighbors( int u ) {
        int neighbors[] = new int[edges[u].size()];
        for ( int i = 0; i < neighbors.length; i++ )
            neighbors[i] = dst( u, i );

        return neighbors;
    }

    void dfsRecursive( int start, Data val, boolean topsort ) {
        clearPathData( topsort );
        nodes[start].visited = true;

        preorder.add( start );
        for ( int v : neighbors( start ) ) {
            if ( !nodes[v].visited )
                nodes[v].path = start;
                nodes[v].dist = nodes[start].dist + 1;
                dfsRecursive( v, val );
        }
        postorder.add( start );
    }

    void dfsIterative( int start, Data val, boolean cycle ) {
        LinkedList<Integer> stack = new LinkedList<>();
        clearPathData( false );
        nodes[start].visited = true;
        stack.push( start );

        while ( !stack.isEmpty() ) {
            Integer u = stack.pop();
            for ( int v : neighbors(u) ) {
                if ( !nodes[v].visited ) {
                    if ( nodes[v].data.equals( val ) ) {
                        System.out.println( "Found target node with val:" + val + "!" );
                        return;
                    }
                    nodes[v].path = u;
                    nodes[v].dist = nodes[u].dist + 1;
                    nodes[v].visited = true;
                    stack.add( v );
                }
                else if ( cycle && stack.contains( v ) ) {
                    processCycle( u, v );
                    return;
                }
            }
        }
    }

    private void processCycle( int u, int v ) {
        for ( int x = u; x != v; x = nodes[x].path )
            cycle.push( x );

        cycle.push( v ); // Last node in cycle.
        cycle.push( u ); // First node in cycle (again).
    }

    void breadthFirstSearch( int start, Data val ) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        clearPathData( false );
        nodes[start].visited = true;
        queue.add( start );

        while ( !queue.isEmpty() ) {
            Integer u = queue.remove();
            for ( int v : neighbors(u) ) {
                if ( !nodes[v].visited ) {
                    if ( nodes[v].data.equals( val ) ) {
                        System.out.println( "Found target node with val:" + val + "!" );
                        return;
                    }
                    nodes[v].path = u;
                    nodes[v].dist = nodes[u].dist + 1;
                    nodes[v].visited = true;
                    queue.add( v );
                }
            }
        }
    }

    // Directed graphs (DAGs) only!
    void topologicalSort( boolean print ) {
        // Simply do a depth first search to generate post order paths.
        // Then print reverse postorder.
        if ( acyclic() ) {
            clearPathData( false );
            clearOrder();

            for ( int v = 0; v < V; ++v ) {
                if ( !nodes[v].visited )
                    dfsRecursive( v, null, true );
            }

            if ( print ) {
                System.out.println( "Printing Topological Sort:" );
                for ( int i = postorder.size() - 1; i >= 0; --i )
                    System.out.println( nodes[ i ].data + " " );
                System.out.println();
            }
        }
    }

    void topologicalShortestPath( int start ) {
        topologicalSort( false ); // First we top sort.
        clearPathData( false );
        nodes[start].dist = 0;

        for ( int v = postorder.size() - 1; v >= 0; --v ) { // Loop through top sort.
            for ( int w : neighbors( postorder.get( v ) ) ) {
                if ( nodes[w].dist > ( nodes[v].dist + edges[v].get( link( v, w ) ).weight ) ) {
                    nodes[w].dist = nodes[w].dist + edges[v].get( link( v, w ) ).weight;
                    nodes[w].path = v;
                }
            }
        }
    }

    void dijikstraShortestPath( int start ) {
        clearPathData( false );

        nodes[start].dist = 0;
        int distance;
        int v = start;

        while ( nodes[v].visited == false ) {
            nodes[v].visited = true;
            for ( int w : neighbors(v) ) {
                if ( nodes[w].dist > ( nodes[v].dist + edges[v].get( link( v, w ) ).weight ) ) {
                    nodes[w].dist = nodes[w].dist + edges[v].get( link( v, w ) ).weight;
                    nodes[w].path = v;
                }
            }

            v = 0;
            distance = Integer.MAX_VALUE;
            for ( int i = 0; i < V; i++ ) {
                if ( (nodes[i].visited == false) && (distance > nodes[i].dist) ) {
                    distance = nodes[i].dist;
                    v = i;
                }
            }
        }
    }

    void primmMinSpanningTree( int start ) {
        clearPathData( false );

        nodes[ start ].dist = 0;
        int distance;
        int v = start;

        while ( nodes[v].visited == false ) {
            nodes[v].visited = true;
            for ( int w : neighbors(v) ) {
                if ( ( nodes[w].dist > edges[v].get( link( v, w ) ).weight ) &&
                     ( nodes[v].visited == false ) ) {
                    nodes[w].dist = nodes[v].dist + edges[v].get( link( v, w ) ).weight;
                    nodes[w].path = v;
                }
            }

            v = 0;
            distance = Integer.MAX_VALUE;
            for ( int i = 0; i < V; i++ ) {
                if ( (nodes[i].visited == false) && (distance > nodes[i].dist) ) {
                    distance = nodes[i].dist;
                    v = i;
                }
            }
        }
    }

    void aStarShortestPath() {}

    void printPreorder( int start ) {
        clearOrder();

        dfsRecursive( start, null, false ); // Generate our preorder list.

        System.out.println( "Printing Preorder:" );
        for ( Integer i : preorder ) {
            System.out.println( nodes[i].data + " " );
        }
        System.out.println();
    }

    void printPostOrder( int start ) {
        clearOrder();

        dfsRecursive( start, null, false ); // Generate our postorder list.

        System.out.println( "Printing Postorder:" );
        for ( Integer i : postorder ) {
            System.out.println( nodes[i].data + " " );
        }
        System.out.println();
    }

    void printReverseOrder( int start ) {
        clearOrder();

        dfsRecursive( start, null, false ); // Generate our postorder list.

        System.out.println( "Printing Reverse Postorder:" );
        for ( int i = postorder.size() - 1; i >= 0; --i )
            System.out.println( nodes[ postorder.get( i ) ].data + " " );
        System.out.println();
    }

    void clearOrder() {
        preorder.clear();
        postorder.clear();
    }

    void printPath( int start, int end ) {
        System.out.println( "Printing Path:" );
        for ( int x = start; x != end; x = nodes[x].path )
            System.out.println( nodes[ x ].data + "" );
        System.out.println();
    }

    void printCycle() {
        if ( cycle == null )
            return;

        System.out.println( "Printing Cycle:" );
        for ( Integer i : cycle )
            System.out.println( nodes[ i ].data + "" );
        System.out.println();
    }

    void printGraph() {
        // Create a start dictionary, and every time we draw, add to start list. If we are
        // drawing an undirected graph, and we have already drawn the edge, don't draw it again.
        HashMap<Integer, Boolean> found = new HashMap();

        if ( directed ) {
            System.out.println( "digraph AdjListGraph {" );
        }
        else {
            System.out.println( "graph AdjListGraph {" );
        }

        for ( int v = 0; v < V; ++v ) {
            for ( int e = 0; e < edges[v].size(); ++e ) {
                if ( directed ) {
                    System.out.println( "\t" + nodes[ v ].data + " -> " + dst( v, e )  +
                                        " [label=" + weight( v, e ) + "];" );
                }
                else {
                    if ( !found.containsKey( dst( v, e ) ) ) {
                        System.out.println( "\t" + nodes [ v ].data + " -- " + dst( v, e ) +
                                            " [label=" + weight( v, e ) + "];" );
                        found.put( v, true );
                    }
                }
            }
        }
    }
}
