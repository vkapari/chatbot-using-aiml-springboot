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
 * Manage client predicates
 *
 */
public class Predicates extends HashMap<String, String> {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(Predicates.class);
	

	/**
     * save a predicate value
     *
     * @param key         predicate name
     * @param value       predicate value
     * @return            predicate value
     */
    @Override
	public String put(String key, String value) {
        if (MagicBooleans.trace_mode) log.info("Setting predicate "+key+" to "+value);
        return super.put(key, value);
    }

    /**
     * get a predicate value
     *
     * @param key predicate name
     * @return    predicate value
     */
    public String get(String key) {
        String result = super.get(key);
        if (result == null) return MagicStrings.unknown_predicate_value;
        else return result;
    }

    /**
     * Read predicate default values from an input stream
     *
     * @param in input stream
     */
    public void getPredicateDefaultsFromInputStream (InputStream in)  {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        try {
            //Read File Line By Line
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

    /** read predicate defaults from a file
     *
     * @param filename        name of file
     */
    public void getPredicateDefaults (String filename) {
        try{
            // Open the file that is the first
            // command line parameter
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fstream = new FileInputStream(filename);
                // Get the object
                getPredicateDefaultsFromInputStream(fstream);
                fstream.close();
            }
        }catch (Exception e){//Catch exception if any
            log.error("Cannot get predicate defaults from '" + filename + "': " + e, e);
        }
    }
}