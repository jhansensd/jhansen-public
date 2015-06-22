package testdstructures;

import java.util.ArrayList;

/**
 * Created by hansj058 on 5/1/2015.
 */
public class AdjacencyMatrixGraph<Data>
{
    public static void Run()
    {}

    int     edgeCount;
    int     nodeCount;
    boolean directed;

    int adj[][];
    String nodes[];

    enum DFSSearchType
    {
      ePreOrder,
      ePostOrder,
      eReversePostOrder,
      eSearchNone
    }

    enum PathType
    {
      eTopologicalPath,
      eBFSShortestPath,
      eDijikstraShortestPath,
      eAStarShortestPath,
      eBelmanFordShortestPath,
      ePathNone
    }

    public AdjacencyMatrixGraph( boolean _directed, boolean _weighted )
    {
        directed = _directed;
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

    boolean Directed() { return directed; }
    int EdgeCount() { return edgeCount; }
    int NodeCount() { return nodeCount; }

    boolean IsSimple() { return false; }
    boolean IsAcyclic() { return false; }
    boolean HasDuplicates() { return false; };

    int GetDegree( int x ) { return 0; }
    boolean ContainsEdge( int x, int y )  { return false; }
    void InsertEdge( int x, int y, int weight, Data data, boolean directed ) {}
    void RemoveEdge( int x, int y ) {}

    int Weight( int x ) { return 0; }
    void SetWeight( int x ) { }

    String Label( int x ) { return null; }
    void SetLabel( int x ) { }

    int[] Neighbors( int x ) { return null;}
    boolean Adjacent( int x ) { return false;}

    void DepthFirstSearch( int start, int val, DFSSearchType searchType, boolean print ) {}
    void BreadthFirstSearch( int start, int val, boolean print ) {}

    void QuickPrint() {}
    void ReadGraph( boolean directed, ArrayList<String> rawGraph ) {}
    void WriteGraph() {}

    void KruskalMinSpanningTree() {}
    void PrimmMinSpanningTree() {}
    void TopologicalSort() {}
    void DijikstraShortestPath() {}
    void AStarShortestPath() {}
    void BellmanFord() {}
    void UnweightedShortestPath() {}
}