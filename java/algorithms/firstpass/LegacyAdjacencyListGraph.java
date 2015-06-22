package testdstructures;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

/**
 * Created by hansj058 on 5/1/2015.
 */
public class LegacyAdjacencyListGraph<T> extends LegacyGraphBase<T>
{
    static int MAX_NODES  = 100;

    private LinkedList<GraphEdge> m_edges[]   = new LinkedList[ MAX_NODES + 1 ];
    private GraphNode m_nodes[] = new GraphNode[ MAX_NODES + 1 ];

    public LegacyAdjacencyListGraph( boolean directed )
    {
        super( directed );
        for ( int i = 0; i < MAX_NODES; i++ )
        {
            m_edges[i] = new LinkedList<GraphEdge>();
        }
    }

    public GraphType GetGraphType()
    {
        return GraphType.eAdjacencyListType;
    }

    public boolean IsSimple()
    {
        return false;
    }

    public boolean IsAcyclic() { return false; }
    public boolean HasDuplicates() { return false; }

    public int GetDegree( int edgeIndex )
    {
        return m_edges[ edgeIndex ].size();
    }

    public int GetDegreeByVal( int val ) { return 0; }

    public boolean ContainsEdge( int start, int finish )
    {
        return ( GetEdge( start, finish ) != null );
    }

    public GraphEdge GetEdge( int start, int finish )
    {
        for ( GraphEdge edge : m_edges[ start ] ) {
            if ( edge.m_target == finish ) {
                return edge;
            }
        }

        return null;
    }

    public void InsertEdge( int start, int finish, int weight, T data, boolean directed )
    {
        data = (T)( new Integer( start ) );

        if ( GetDegree( start ) == 0 ) {
            GraphNode node = new GraphNode( data );
            m_nodes[ start ] = node;
            m_nodeCount++;
        }

        GraphEdge edge = new GraphEdge( finish, weight );
        m_edges[ start ].add( edge );

        if ( directed == false ) {
            InsertEdge( finish, start, weight, data, true );
        }
    }

    public void RemoveEdge( int start, int finish )
    {
        // Make sure edge exists first.
        if ( GetEdge( start, finish ) == null ) {
            return;
        }

        // Loop through all edges and remove all edges pointing to our starting or ending node.
        for ( int i = 0; i < m_nodeCount; i++ ) {
            for ( int j = 0; j < m_edges[ i ].size(); i++ ) {
                if ( m_edges[ i ].get( j ).m_target == start ||
                     m_edges[ i ].get( j ).m_target == finish )
                {
                    m_edges[ i ].remove( i );
                    --m_edgeCount;
                }
            }
        }

        // Remove both nodes associated with this edge.
        RemoveNode( start, false, null );
        RemoveNode( finish, false, null );
    }

    // Adds a node associated with the index.
    public void AddNode( int index, T data )
    {
        m_nodes[ index ].m_data = data;
        ++m_nodeCount;
    }

    public void RemoveDataNode( T data )
    {
        if ( !HasDuplicates() )
        {
            RemoveNode( -1, true, data );
        }
    }

    public void RemoveNode( int index, boolean checkData, T data )
    {
        // Loop through all edges and remove any edge that has this node as its target still.
        for ( int i = 0; i < m_nodeCount; i++ ) {

            if ( checkData && m_nodes[ i ].m_data == data ) {
                index = i;
            }

            Iterator itr = m_edges[ i ].iterator();

            while( itr.hasNext() ) {

                GraphEdge edge = (GraphEdge)itr.next();

                if ( ( !checkData && ( edge.m_target == index ) ) ||
                     ( !checkData && ( i == index ) ) ) {
                    itr.remove();
                    --m_edgeCount;
                }
            }
        }

        m_nodes[ index ] = null;
        --m_nodeCount;
    }

    public void DepthFirstSearch( int start, int val, DFSSearchType searchType, boolean print )
    {

    }

    public void BreadthFirstSearch( int start, int val, boolean print )
    {

    }

    public void KruskalMinSpanningTree()
    {}

    public void PrimmMinSpanningTree()
    {}

    public void TopologicalSort()
    {}

    public void DijikstraShortestPath()
    {}

    public void AStarShortestPath()
    {}

    public void BellmanFord()
    {}

    public void UnweightedShortestPath()
    {}

    public void QuickPrint()
    {}

    void ReadGraph( boolean directed, ArrayList<String> rawGraph )
    {
        String[] edge = rawGraph.get(0).split( " " );
        int nodeCount = Integer.parseInt( edge[0] );
        int edgeCount = Integer.parseInt( edge[1] );
        SetDirected( directed );
        Integer data = new Integer( -1 );

        for( int i = 1;i <= edgeCount; i++ )
        {
            edge = rawGraph.get(i).split( " " );
            InsertEdge( Integer.parseInt( edge[0] ),
                        Integer.parseInt( edge[1] ),
                        Integer.parseInt( edge[2] ), null, directed );
        }

        /*
        int x, y;
        Scanner sc = new Scanner( System.in );
        SetNodeCount( sc.nextInt() );
        SetEdgeCount( sc.nextInt() );
        SetDirected( directed );

        for( int i = 1;i <= GetEdgeCount(); i++ )
        {
            InsertEdge( sc.nextInt(), sc.nextInt(), sc.nextInt() );
        }*/
    }

    public void WriteGraph()
    {
        // Create a start dictionary, and every time we draw, add to start list. If we are
        // drawing an undirected graph, and we have already drawn the edge, don't draw it again.
        HashMap<Integer, Boolean> found = new HashMap();

        try
        {
            String filePath = "C:/Users/jhansen/Google Drive/interview code/graph-samples/graph.dot";
            FileWriter file = new FileWriter( filePath );
            if ( IsDirected() ) {
                file.write( "digraph AdjListGraph {\n" );
            }
            else {
                file.write( "graph AdjListGraph {\n" );
            }
            for ( int node = 1; node <= GetNodeCount(); node++ ) {
                int degreeCount = GetDegree( node );
                for ( int degree = 0; degree < degreeCount; degree++) {
                    if ( IsDirected() ) {
                        file.write( "\t" + node + " -> "
                                    + m_edges[ node ].get( degree ).m_target + " [label="
                                    + m_edges[ node ].get( degree ).m_weight + "];\n" );
                    }
                    else {
                        // Check the start hash to see if the target was starting before.
                        if ( !found.containsKey( m_edges[ node ].get( degree ).m_target ) ) {
                            file.write( "\t" + node + " -- "
                                        + m_edges[ node ].get( degree ).m_target + " [label="
                                        + m_edges[ node ].get( degree ).m_weight + "];\n" );

                            found.put( node, true );
                        }
                    }
                }
            }
            file.write( "}" );
            file.close();
        }
        catch ( IOException exception )
        {
            System.out.println( "Message is: " + exception.getMessage() );
        }
    }

}
