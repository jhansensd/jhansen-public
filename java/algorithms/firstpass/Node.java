package testdstructures;

/**
 * Created by jhansen on 5/13/2015.
 */
public class Node<DataType> implements Cloneable
{
    private DataType m_data;
    private Node m_next;

    public DataType Data() { return m_data; }
    public Node Next() { return m_next; }

    public void SetData( DataType data ) { m_data = data; }
    public void SetNext( Node next ) { m_next = next; }

    public Node( DataType data, Node next )
    {
        m_data = data;
        m_next = next;
    }

    public void AppendToTail( DataType data )
    {
        Node end = new Node( data, null );
        Node curr = this;
        while ( curr.Next() != null ) {
            curr = curr.Next();
        }
        curr.SetNext( end );
    }

    public int IntData()
    {
        return ((Integer)m_data).intValue();
    }

    public void Clear( DataType clearData )
    {
        m_data = clearData;
        m_next = null;
    }

    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}