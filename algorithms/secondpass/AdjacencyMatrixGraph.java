package practiceruns;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;


/**
 * Created by hansj058 on 5/1/2015.
 */
public class AdjacencyMatrixGraph
{
    public static void Run()
    {
        Utility.ReadWordList( "wordlist.txt" );
        AdjacencyMatrixGraph1<String> graph = new AdjacencyMatrixGraph1<>( true, 12, 15 );

        String str1 = "faucet";
        String str2 = "fullcolored";

        graph.PrintGraph();

        System.out.println( "BFS Shortest path from (" + str1 + " -> " + str2 + ")!" );
        graph.PrintPath( graph.IndexByData( str1 ), graph.IndexByData( str2 ), AdjacencyMatrixGraph1.PathType.eBFSShortestPath );

        System.out.println( "Dijikstra Shortest path from (" + str1 + " -> " + str2 + ")!" );
        graph.PrintPath( graph.IndexByData( str1 ), graph.IndexByData( str2 ), AdjacencyMatrixGraph1.PathType.eDijikstraShortestPath );

        System.out.println( "BellmanFord Shortest path from (" + str1 + " -> " + str2 + ")!" );
        graph.PrintPath( graph.IndexByData( str1 ), graph.IndexByData( str2 ), AdjacencyMatrixGraph1.PathType.eBellmanFordShortestPath );

        //System.out.println( "FloydWarshall Shortest path from (" + str1 + " -> " + str2 + ")!" );
        //graph.PrintPath( graph.IndexByData( str1 ), graph.IndexByData( str2 ), AdjacencyMatrixGraph1.PathType.eFloydWarshall );
    }
}


class AdjacencyMatrixGraph1<Data>
{
    final int MAX_WEIGHT = 50;

    int     nodes;
    int     edges;
    boolean directed;

    int adj[][];
    Data values[];

    boolean visited[];
    int path[];
    int pathMatrix[][]; // For Floyd Warshall Algorithm
    int dist[];

    enum DFSSearchType
    {
        ePreOrder,
        ePostOrder,
        eReversePostOrder,
        eSearchNone
    }

    enum PathType
    {
        eTopologicalShortestPath,
        eBFSShortestPath,
        eDijikstraShortestPath,
        eBellmanFordShortestPath,
        eAStarShortestPath,
        eFloydWarshallShortestPath,
        ePathNone
    }

    public AdjacencyMatrixGraph1( boolean _directed, int _nodes )
    {
        nodes = _nodes;
        directed = _directed;
        edges = 0;
        adj = new int[ nodes ][ nodes ];
        values = (Data[])(new Object[ nodes ]);

        for ( int i = 0; i < nodes; i++ ) {
            for ( int j = 0; j < nodes; j++ ) {
                adj[i][j] = Integer.MAX_VALUE;
            }
        }

        visited = new boolean[ nodes ];
        path = new int[ nodes ];
        pathMatrix = new int[ nodes ][ nodes ];
        dist = new int[ nodes ];

        ClearPathData();
    }

    private static Random generator = new Random( 1000 );
    public AdjacencyMatrixGraph1( boolean _directed, int _nodes, int _edges )
    {
        this( _directed, _nodes );

        while ( edges != _edges ) {
            int x = generator.nextInt( nodes );
            int y = generator.nextInt( nodes );
            int weight = generator.nextInt( MAX_WEIGHT );
            if ( x == y || adj[ x ][ y ] != Integer.MAX_VALUE || adj[ y ][ x ] != Integer.MAX_VALUE ) {
                continue;
            }
            InsertEdge( x, y, weight );
        }

        // Store strings in all the nodes.
        for ( int i = 0; i < values.length; i++ ) {
            values[ i ] = ( Data ) Utility.RandWord( generator.nextFloat() );
        }
    }

    int Search( boolean depthFirstSearch, DFSSearchType searchType,
                int start, Data val, boolean print )
    {
        return Traverse( depthFirstSearch, searchType, start, val, print );
    }

    void FindPath( int start, int end, Data val, PathType pathType )
    {
        switch ( pathType ) {

            case eBFSShortestPath:
                BreadthFirstSearch( start, val == null ? values[ end ] : val, false );
                break;

            case eDijikstraShortestPath:
                DijikstraShortestPath( start );
                break;

            case eBellmanFordShortestPath:
                BellmanFordShortestPath( start );
                break;

            case eAStarShortestPath:
                break;

            case eFloydWarshallShortestPath:
                FloydWarshallShortestPath();
                break;
        }
    }

    int Traverse( boolean depthFirstSearch, DFSSearchType searchType,
                  int start, Data val, boolean print )
    {
        if ( depthFirstSearch ) {
            DepthFirstSearch( start, val, searchType, print );
        }

        BreadthFirstSearch( start, val, print );

        return 0;
    }

    boolean Directed() { return directed; }
    int Edges() { return edges; }
    int Nodes() { return nodes; }

    boolean Simple() { return false; }
    boolean Acyclic() { return false; }

    boolean Duplicates()
    {
        HashMap<Data, Boolean> map = new HashMap<>();

        for ( int node = 0; node < nodes; node++ ) {
            if ( map.get( values[ node ] ) == null ) {
                map.put( values[node], true );
            } else {
                return true;
            }
        }

        return false;
    }

    int Degree( int x )
    {
        int count = 0;
        for ( int row = 0; row < nodes; row++ ) {
            for ( int col = 0; col < nodes; col++ ) {
                if ( adj[row][col] == x ) {
                    count++;
                }
            }
        }
        return count;
    }

    boolean ContainsData( Data val )
    {
        // Search through the whole graph to find the data.
        if ( Search( true, DFSSearchType.ePreOrder, 0, val, false ) != -1 ) {
            return true;
        }

        return false;
    }

    void InsertEdge( int x, int y, int weight )
    {
        if ( adj[x][y] == Integer.MAX_VALUE ) {
            edges++;
        }

        adj[x][y] = weight;

        if ( !directed ) {
            adj[ y ][ x ] = weight;
        }
    }

    void RemoveEdge( int x, int y )
    {
        adj[x][y] = 0;
        values[x] = null;
        edges--;

        if ( !directed ) {
            adj[ x ][ y ] = 0;
        }
    }

    int Weight( int x, int y ) { return adj[x][y]; }
    void SetWeight( int x, int y, int weight ) { adj[x][y] = weight; }

    Data Value( int x ) { return values[x]; }
    void SetValue( int x, Data val ) { values[ x ] = val; }

    int[] Neighbors( int x )
    {
        int count = 0;
        for ( int i = 0; i < nodes; i++ ) {
            if ( adj[x][i] != Integer.MAX_VALUE ) {
                count++;
            }
        }

        int neighbors[] = new int[count];
        count = 0;
        for ( int i = 0; i < nodes; i++ ) {
            if ( adj[x][i] != Integer.MAX_VALUE ) {
                neighbors[count++] = i;
            }
        }

        return neighbors;
    }

    boolean Adjacent( int x, int y )  { return adj[x][y] != 0; }

    void DepthFirstSearch( int start, Data val, DFSSearchType searchType, boolean print ) {}

    void BreadthFirstSearch( int start, Data val, boolean print )
    {
        ClearPathData();

        ArrayDeque<Integer> queue = new ArrayDeque<>( 100 );
        visited[start] = true;
        queue.add( start );
        while ( !queue.isEmpty() ) {
            Integer v = queue.remove();
            int neighbors[] = Neighbors( v );
            for ( int w : neighbors ) {
                if ( !visited[ w ] ) {
                    path[w] = v;
                    visited[w] = true;
                    queue.add( w );
                }
            }
        }
    }

    void PrintPath( int x, int y, PathType pathType )
    {
        FindPath( x, y, null, pathType );

        int totalDist = 0;

        if ( pathType == PathType.eBFSShortestPath ||
             pathType == PathType.eDijikstraShortestPath ||
             pathType == PathType.eBellmanFordShortestPath ) {
            if ( visited[ y ] == false && pathType != PathType.eBellmanFordShortestPath ) {
                System.out.println( "There is no path (" + x + " -> " + y + ")!" );
                return;
            }

            System.out.println( "Path from (" + x + " -> " + y + ") is:" );
            String pathStr = (String)values[ y ];
            int p = y;
            while ( path[ p ] != x ) {
                pathStr = values[ path[ p ] ] + " " + pathStr;

                if ( !directed ) {
                    totalDist += adj[ path[ p ] ][ p ];
                }
                else {
                    totalDist += adj[ p ][ path[p] ];
                }

                p = path[ p ];
            }

            if ( !directed ) {
                totalDist += adj[ p ][ x ];
            }
            else {
                totalDist += adj[ x ][ p ];
            }

            System.out.println( values[ x ] + " " + pathStr );
            System.out.println( "Total Distance is:" + totalDist );
        }
        else if ( pathType == PathType.eFloydWarshallShortestPath ) {
            System.out.println( "Path from (" + x + " -> " + y + ") is:" );
            String pathStr = Integer.toString( y );
            int p = y;
            while ( pathMatrix[ x ][ p ] != x ) {
                pathStr = pathMatrix[ x ][ p ] + " " + pathStr;
                p = pathMatrix[ x ][ p ];
            }
            System.out.println( x + " " + pathStr );
        }
    }

    boolean HasPath( int x, int y, PathType pathType )
    {
        switch ( pathType ) {
            case eBFSShortestPath:
                BreadthFirstSearch( x, values[ y ], true );
                break;

            case eDijikstraShortestPath:
                DijikstraShortestPath( x );
                break;

            case eBellmanFordShortestPath:
                BellmanFordShortestPath( x );
                break;

            case eAStarShortestPath:
                break;

            case eFloydWarshallShortestPath:
                FloydWarshallShortestPath();
                break;
        }

        if ( pathType == PathType.eBFSShortestPath ||
             pathType == PathType.eDijikstraShortestPath ||
             pathType == PathType.eBellmanFordShortestPath ) {
            return visited[ y ];
        }
        else if ( pathType == PathType.eFloydWarshallShortestPath ) {
            return pathMatrix[x][y] != Integer.MAX_VALUE;
        }

        return false;
    }

    void ClearPathData()
    {
        for ( int i = 0; i < nodes; i++ ) {
            for ( int j = 0; j < nodes; j++ ) {
                pathMatrix[i][j] = Integer.MAX_VALUE;
            }
        }

        for ( int i = 0; i < nodes; i++ ) {
            visited[ i ] = false;
            path[ i ] = Integer.MAX_VALUE;
            dist[ i ] = Integer.MAX_VALUE;
        }
    }

    int IndexByData( Data val )
    {
        for ( int i = 0; i < values.length; i++ ) {
            if ( val.equals( values[ i ] ) ) {
                return i;
            }
        }

        return -1;
    }

    void QuickPrint( DFSSearchType searchType ) { DepthFirstSearch( 0, null, searchType, true ); }

    static AdjacencyMatrixGraph1 ReadGraph( boolean directed, ArrayList<String> rawGraph )
    {
        String[] edge = rawGraph.get(0).split( " " );
        int nodeCount = Integer.parseInt( edge[0] );
        int edgeCount = Integer.parseInt( edge[1] );

        AdjacencyMatrixGraph1<String> graph = new AdjacencyMatrixGraph1<>( directed, nodeCount );

        for( int i = 0;i < edgeCount; i++ ) {
            edge = rawGraph.get( i ).split( " " );
            graph.InsertEdge( Integer.parseInt( edge[ 0 ] ),
                              Integer.parseInt( edge[ 1 ] ),
                              Integer.parseInt( edge[ 2 ] ) );
        }

        return graph;
    }

    void PrintGraph()
    {
        // Create a start dictionary, and every time we draw, add to start list. If we are
        // drawing an undirected graph, and we have already drawn the edge, don't draw it again.
        HashMap<Integer, Boolean> found = new HashMap();

        if ( directed ) {
            System.out.println( "digraph AdjListGraph {" );
        }
        else {
            System.out.println( "graph AdjListGraph {" );
        }

        for ( int row = 0; row < nodes; row++ ) {
            for ( int col = 0; col < nodes; col++ ) {
                if ( adj[row][col] != Integer.MAX_VALUE ) {
                    if ( directed ) {
                        System.out.println( "\t" + values[ row ] + " -> " + values[ col ] +
                                            " [label=" + adj[ row ][ col ] + "];" );
                    }
                    else {
                        if ( !found.containsKey( col ) ) {
                            System.out.println( "\t" + values[ row ] + " -- " + values[ col ] +
                                                " [label=" + adj[ row ][ col ] + "];" );
                            found.put( row, true );
                        }
                    }
                }
            }
        }
    }

    void DijikstraShortestPath( int start )
    {
        ClearPathData();

        dist[start] = 0;
        int distance;
        int v = start;

        while ( visited[ v ] == false ) {
            visited[v] = true;
            int neighbors[] = Neighbors( v );
            for ( int w : neighbors ) {
                if ( dist[w] > ( dist[v] + adj[v][w] ) ) {
                    dist[w] = dist[v] + adj[v][w];
                    path[w] = v;
                }
            }

            v = 0;
            distance = Integer.MAX_VALUE;
            for ( int i = 0; i < nodes; i++ ) {
                if ( (visited[i] == false) && (distance > dist[i]) ) {
                    distance = dist[i];
                    v = i;
                }
            }
        }
    }

    void BellmanFordShortestPath( int start )
    {
        ClearPathData();

        dist[ start ] = 0;
        ArrayDeque<Integer> process = new ArrayDeque<>( 100 );
        process.add( start );

        while ( !process.isEmpty() ) {
            int v = process.remove();
            int neighbors[] = Neighbors( v );
            for ( int w : neighbors ) {
                if ( ( dist[ v ] + adj[ v ][ w ] ) < dist[ w ] ) {
                    dist[ w ] = dist[ v ] + adj[ v ][ w ];
                    path[ w ] = v;
                    process.add( w );
                }
            }
        }

        // Check for negative weight cycles.
        for ( int i = 0; i < nodes; i++ ) {
            for ( int j = 0; j < nodes; j++ ) {
                if ( adj[i][j] != Integer.MAX_VALUE ) {
                    if ( ( dist[ i ] + adj[ i ][ j ] ) < dist[ j ] ) {
                        System.out.println( "Graph contains a negative-weight cycle!" );
                    }
                }
            }
        }
    }

    void FloydWarshallShortestPath()
    {
        ClearPathData();

        int distToK;

        for ( int pathI = 0; pathI < nodes; pathI++ ) {
            for ( int pathJ = 0; pathJ < nodes; pathJ++ ) {
                if ( adj[pathI][pathJ] != Integer.MAX_VALUE ) {
                    pathMatrix[ pathI ][ pathJ ] = pathI;
                }
            }
        }

        for (int i=0; i<nodes; i++)
            pathMatrix[i][i] = i;

        for ( int k = 0; k < nodes; k++ ){
            for ( int i = 0; i < nodes; i++ ) {
                for ( int j = 0; j < nodes; j++ ) {
                    distToK = adj[i][k] + adj[k][j];
                    if ( distToK < adj[i][j] ) {
                        adj[i][j] = distToK;
                        pathMatrix[i][j] = pathMatrix[k][j];
                    }
                }
            }
        }
    }

    void AStarShortestPath() {}

    void PrimmMinSpanningTree( int start )
    {
        ClearPathData();

        dist[start] = 0;
        int distance;
        int v = start;

        while ( visited[ v ] == false ) {
            visited[v] = true;
            int neighbors[] = Neighbors( v );
            for ( int w : neighbors ) {
                if ( ( dist[w] > adj[v][w] ) && ( visited[v] == false ) ) {
                    dist[w] = dist[v] + adj[v][w];
                    path[w] = v;
                }
            }

            v = 0;
            distance = Integer.MAX_VALUE;
            for ( int i = 0; i < nodes; i++ ) {
                if ( (visited[i] == false) && (distance > dist[i]) ) {
                    distance = dist[i];
                    v = i;
                }
            }
        }
    }

    void KruskalsMinSpanningTree() {}
    void TopologicalSort() {}
}