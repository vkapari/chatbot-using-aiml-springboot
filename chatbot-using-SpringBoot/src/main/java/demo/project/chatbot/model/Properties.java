package demo.project.chatbot.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Bot Properties
*/

public class Properties extends HashMap<String, String> {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(Properties.class);
    /**
     * get the value of a bot property.
     *
     * @param key property name
     * @return   property value or a string indicating the property is undefined
     */
    public String get(String key) {
        String result = super.get(key);
        if (result == null) return MagicStrings.unknown_property_value;
        else return result;
    }

    /**
     * Read bot properties from an input stream.
     *
     * @param in    Input stream
     */
    public void getPropertiesFromInputStream(InputStream in)  {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        //Read File Line By Line
        try {
        while ((strLine = br.readLine()) != null)   {
            if (strLine.contains(":")) {
                String property = strLine.substring(0, strLine.indexOf(":"));
                String value = strLine.substring(strLine.indexOf(":")+1);
                put(property, value);
            }
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Read bot properties from a file.
     *
     * @param filename   file containing bot properties
     */
    public void getProperties (String filename) {
        log.info("Get Properties: "+filename);
        try {
            // Open the file that is the first
            // command line parameter
            File file = new File(filename);
            if (file.exists()) {
                log.info("Exists: "+filename);
                FileInputStream fstream = new FileInputStream(filename);
                // Get the object
                getPropertiesFromInputStream(fstream);
                //Close the input stream
                fstream.close();
            }
        } catch (Exception e){//Catch exception if any
            log.error("Cannot get properties from '" + filename + "': " + e, e);
        }
    }
}