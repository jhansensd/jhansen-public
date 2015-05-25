package practiceruns;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by jhansen on 5/16/2015.
 */
public class Utility
{
    static public ArrayList<String> wordList = new ArrayList<>();

    static void ReadWordList( String fileName )
    {
        Path file = Paths.get( fileName );

        try {
            BufferedReader reader = Files.newBufferedReader( file, Charset.forName( "US-ASCII" ) );
            String line;
            while( (line = reader.readLine()) != null) {
                wordList.add( line );
            }
        }
        catch( IOException io ) {
            System.err.format("IOException: %s%n", io );
        }
    }

    static public String RandWord( float rand )
    {
        int index = (int)(wordList.size() * rand);
        return wordList.get( index );
    }
}
