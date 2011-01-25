package GLaDOS2011.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.microedition.io.Connector;
import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.microedition.io.FileConnection;

/**
 * This class allows us to read/write files. This contains code from the
 * GRT Framework located at http://code.google.com/p/grtframework/
 * @author Ben
 */
public class FileActions {
    
    // <editor-fold defaultstate="collapsed" desc="public static string FileActions.formatData(String data)">
    /**
     * This method formats data from the sensors in preparation for writing to a log file.
     * It is obviously not functional right now.
     * @param data The data from the sensors
     * @return The formatted data
     */
    public static String formatData(String data) {
        return "";
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="public static string FileActions.readFile(String filename)">
    /**
     * This method reads the file located at filename and returns its contents in a string.
     * @param filename Name of file to read
     * @return Text of file
     */
    public static String readFile(String filename) {
        String url = "file:///" + filename;
        String contents = "";
        try {
            //Open file connection
            FileConnection c = (FileConnection) Connector.open(url);
            //Read file contents into contents
            BufferedReader buf = new BufferedReader(new InputStreamReader(c.openInputStream()));
            String line = "";
            while ((line = buf.readLine()) != null) {
                contents += line + "\n";
            }
            //Close file connection
            c.close();
        } catch (IOException e) {
            //Most likely file not found exception
            e.printStackTrace();
        }
        return contents;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="public static void FileActions.writeFile(String filename, String contents)">
    /**
     * This method writes the contents of contents to the file located at filename.
     * @param filename Name of file to write
     * @param contents Contents to write
     */
    public static void writeFile(String filename, String contents) {
        String url = "file:///" + filename;
        try {
            //Open file connection
            FileConnection c = (FileConnection) Connector.open(url);
            //Write contents to file
            OutputStreamWriter writer = new OutputStreamWriter(c.openOutputStream());
            writer.write(contents);
            //Close file connection
            c.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // </editor-fold>
}