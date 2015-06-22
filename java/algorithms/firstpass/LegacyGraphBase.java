package testdstructures;


import java.util.ArrayList;

/**
 * Created by hansj058 on 5/1/2015.
 */
public abstract class LegacyGraphBase<T>
{
    protected int     m_edgeCount;
    protected int     m_nodeCount;
    protected boolean m_isDirected;

    public class GraphNode<T>
    {
        public T m_data; // Vertex data;

        public GraphNode( T data )
        {
           m_data = data;
        }

        public String toString()
        {
            return "(Node:" + m_data.toString() + ")";
        }
    }

    public class GraphEdge
    {
        public int m_target; // Target vertex.
        public int m_weight; // Target weight.

        public GraphEdge( int target, int weight )
        {
            m_target = target;
            m_weight = weight;
        }

        public String toString()
        {
            return "(" + m_target + "; " + m_weight + ")";
        }
    }

    enum GraphType
    {
        eAdjacencyListType,
        eAdjacencyMatrixType,
        eObjectPointerType,
        eGraphTypeNone
    }

    enum DFSSearchType
    {
        ePreOrderSearch,
        eInOrderSearch,
        ePostOrderSearch,
        eSearchNone
    }

    enum PathType
    {
        eBFSShortestPath,
        eDijikstraShortestPath,
        eAStarShortestPath,
        eBelmanFordShortestPath,
        ePathNone
    }

    public GraphType GetGraphType()
    {
        return GraphType.eGraphTypeNone;
    }

    public LegacyGraphBase( boolean directed )
    {
        m_isDirected = directed;
    }

    int Search( boolean depthFirstSearch, DFSSearchType searchType, int start, int val )
    {
        return Traverse( depthFirstSearch, searchType, -1, val, true );
    }

    void FindPath( int start, int end, int val, PathType pathType )
    {
        switch ( pathType )
        {
            case eBFSShortestPath:
                break;

            case eDijikstraShortestPath:
                break;

            case eAStarShortestPath:
                break;

            case eBelmanFordShortestPath:
                break;
        }
    }

    int Traverse( boolean depthFirstSearch, DFSSearchType searchType, int start, int val, boolean print )
    {
        if ( depthFirstSearch ) {
            DepthFirstSearch( start, val, searchType, print );
        }

        return 0;
    }

    boolean IsDirected()
    {
        return m_isDirected;
    }

    void SetDirected( boolean isDirected )
    {
        m_isDirected = isDirected;
    }

    int GetEdgeCount()
    {
        return m_edgeCount;
    }

    void SetEdgeCount( int edgeCount )
    {
        m_edgeCount = edgeCount;
    }

    int GetNodeCount()
    {
        return m_nodeCount;
    }

    void SetNodeCount( int nodeCount )
    {
        m_nodeCount = nodeCount;
    }

    abstract boolean IsSimple();
    abstract boolean IsAcyclic();
    abstract boolean HasDuplicates();

    abstract int GetDegree( int edgeIndex );
    abstract int GetDegreeByVal( int val );

    abstract boolean ContainsEdge( int val1, int val2 );
    abstract GraphEdge GetEdge( int start, int finish );
    abstract void InsertEdge( int start, int finish, int weight, T data, boolean directed );
    abstract void RemoveEdge( int start, int finish );
    abstract void AddNode( int index, T data );
    abstract void RemoveDataNode( T data );
    abstract void RemoveNode( int index, boolean checkData, T data );

    abstract void DepthFirstSearch( int statt, int val, DFSSearchType searchType, boolean print );
    abstract void BreadthFirstSearch( int start, int val, boolean print );

    abstract void QuickPrint();
    abstract void ReadGraph( boolean directed, ArrayList<String> rawGraph );
    abstract void WriteGraph();

    void KruskalMinSpanningTree() {}
    void PrimmMinSpanningTree() {}
    void TopologicalSort() {}
    void DijikstraShortestPath() {}
    void AStarShortestPath() {}
    void BellmanFord() {}
    void UnweightedShortestPath() {}
}
