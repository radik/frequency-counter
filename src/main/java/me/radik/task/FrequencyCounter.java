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
     * If iterator has less than 10 different words, method returns {@link java.util.Collection}
     * containing less than 10 words.
     * @param iterator
     * @return collection of most frequent words
     */
    Collection<String> getMostFrequent(Iterator<String> iterator);

    /**
     *
     * Returns the top <i>count</i> most frequent strings when the iterator is exhausted.
     * If iterator has less than <i>count<i/> different words, method returns {@link java.util.Collection}
     * containing less than <i>count</i> words.
     * @param iterator
     * @param count
     * @return collection of most frequent words
     */
    Collection<String> getMostFrequent(Iterator<String> iterator, int count);
}
