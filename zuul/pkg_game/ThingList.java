package zuul.pkg_game;

import com.sun.javafx.collections.ObservableMapWrapper;

import java.util.HashMap;

/**
 * This class is used to represent a list of things such as items, personages...
 *
 * @param <T> The type of the elements of the list.
 */
public class ThingList<T> extends ObservableMapWrapper<String, T> {
    public ThingList() {
        super(new HashMap<>());
    }

    /**
     * Gets a string with all the keys.
     *
     * @return The keys string.
     */
    public String getKeysString() {
        return String.join(", ", this.keySet());
    }
}
