package testdstructures;

/**
 * Created by jhansen on 5/12/2015.
 */
public class Bits
{
    private int m_val;
    
    public Bits( int val ) { m_val = val; }

    public int Val() { return m_val; }
    public void Setval( int val ) { m_val = val; }
    
    public static void Run()
    {}

    public void Print()
    {
        System.out.println( "Val is:" + m_val + " Binary is: " + Integer.toBinaryString( m_val ) );
    }

    public int GetBit( int bit )
    {
        return m_val & ( 1 << bit );
    }

    public void SetBit( int bit )
    {
        m_val |= ( 1 << bit );
    }

    public void ClearBit( int bit )
    {
        m_val &= ~( 1 << bit );
    }

    public void FlipBit( int bit )
    {
        m_val ^= ( 1 << bit );
    }

    public void UpdateBit( int bit, int on )
    {
        m_val &= ~( 1 << bit ); // Clear the bit we want.
        m_val |= ( on << bit ); // Or it with the shifted bit value.
    }

    public void ClearLowBits( int lowBit )
    {
        int mask = ~(-1 >>> (31 - lowBit));
        m_val &= mask;
    }

    public void ClearHighBits( int highBit )
    {
        int mask = ( 1 << highBit ) - 1;
        m_val &= mask;
    }

    public void ClearBitRange( int start, int end )
    {
        int range = ( end - start ) + 1;
        int mask = ~(~(-1 << range) << start);

        // Move the mask over start amount and mask.
        m_val &= mask;

        System.out.println( "Temp mask is" + Integer.toBinaryString( mask ) );
    }

    public int CountBits()
    {
        int count = 0;

        while ( m_val != 0 ) {
            if ( ( m_val & 1 ) > 0 ) {
                count++;
            }
            m_val = m_val >> 1;
        }

        return count;
    }
}
