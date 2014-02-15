package me.radik.task;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by radik on 11/02/14.
 * @param <T> Type of items in Iterator
 */
public interface FrequencyCounter<T> {

    /**
     *
     * Returns the top 10 most frequent T t when the iterator is exhausted.
     * If iterator has less than 10 different items, method returns {@link java.util.Collection}
     * containing less than 10 items.
     * @param iterator
     * @return collection of most frequent T items
     */
    Collection<T> getMostFrequent(Iterator<T> iterator);

    /**
     *
     * Returns the top <i>count</i> most frequent T items when the iterator is exhausted.
     * If iterator has less than <i>count<i/> different items, method returns {@link java.util.Collection}
     * containing less than <i>count</i> items.
     * @param iterator
     * @param count
     * @return collection of most frequent items
     */
    Collection<T> getMostFrequent(Iterator<T> iterator, int count);
}
