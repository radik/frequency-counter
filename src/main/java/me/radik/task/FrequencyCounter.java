package me.radik.task;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by radik on 11/02/14.
 */
public interface FrequencyCounter {

    /**
     *
     * Returns the top 10 most frequent strings when the iterator is exhausted.
     * @param iterator
     * @return
     */
    Collection<String> getMostFrequent(Iterator<String> iterator);

    /**
     *
     * Returns the top <i>count</i> most frequent strings when the iterator is exhausted.
     * @param iterator
     * @param count
     * @return
     */
    Collection<String> getMostFrequent(Iterator<String> iterator, int count);
}
