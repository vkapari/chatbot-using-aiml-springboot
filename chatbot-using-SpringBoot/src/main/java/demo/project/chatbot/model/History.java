package demo.project.chatbot.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * History object to maintain history of input, that request and response
 *
 * @param <T>    type of history object
 */
public class History<T> {
	private static final Log log = LogFactory.getLog(History.class);
    private Object[] history;
    private String name;

    /**
     * Constructor with default history name
     */
    public History () {
        this("unknown");
    }

    /**
     * Constructor with history name
     *
     * @param name    name of history
     */
    public History(String name) {
        this.name = name;
        history = new Object[MagicNumbers.max_history];
    }

    /**
     * add an item to history
     *
     * @param item    history item to add
     */
    public void add(T item) {
        for (int i = MagicNumbers.max_history-1; i > 0; i--) {
              history[i] = history[i-1];
        }
        history[0] = item;
    }

    /**
     * get an item from history
     *
     * @param index       history index
     * @return            history item
     */
    public T get (int index) {
        if (index < MagicNumbers.max_history) {
            if (history[index] == null) return null;
            else return (T)history[index];
        }
        else return null;
    }

    /**
     * get a String history item
     *
     * @param index    history index
     * @return         history item
     */
    public String getString (int index) {
        if (index < MagicNumbers.max_history) {
            if (history[index] == null) return MagicStrings.unknown_history_item;
            else return (String)history[index];
        }
        else return null;
    }

    /**
     * print history
     */
    public void printHistory() {
        int i;
        for (i = 0; get(i) != null; i++) {
            log.info(name+"History "+(i+1)+" = "+get(i));
            log.info("{}"+ String.valueOf(get(i).getClass()).contains("History"));
            if (String.valueOf(get(i).getClass()).contains("History")) ((History)get(i)).printHistory();
        }
    }
}
