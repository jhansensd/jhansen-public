package testdstructures;

import java.util.ArrayList;

/**
 * Created by hansj058 on 5/1/2015.
 */
public class LegacyGraphManager
{
    public LegacyGraphBase CreateGraph( LegacyGraphBase.GraphType eGraphType, boolean directed )
    {
        switch ( eGraphType )
        {
            case eAdjacencyListType:
                return new LegacyAdjacencyListGraph<Integer>( directed );

           /* case eAdjacencyMatrixType:
                return new AdjacencyMatrixGraph( directed );

            case eObjectPointerType:
                return new ObjectGraph( directed );
                */
        }

        return null;
    }

    public void Run( ArrayList<String> input )
    {
        LegacyGraphBase adjListGraph = CreateGraph( LegacyGraphBase.GraphType.eAdjacencyListType, false );
        adjListGraph.ReadGraph( false, input );
        adjListGraph.RemoveNode( 12, false, null );
        adjListGraph.WriteGraph();
    }

    public LegacyGraphBase CombineGraph()
    {
        return null;
    }
    public LegacyGraphBase ConvertGraph( LegacyGraphBase graph )
    {
        return null;
    }
}
