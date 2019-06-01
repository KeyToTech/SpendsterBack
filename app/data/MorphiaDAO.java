package data;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

/**
 * MorphiaDAO
 * Resolves ambiguous reference to overloaded definition,
 * when being called from scala
 *
 * @author Bohdan Ustiak (bogdan.ustyak@gmail.com)
 */
public class MorphiaDAO<T> {

    public <T> Key<T> save(Datastore datastore, T t) {
        return datastore.save(t);
    }
}
