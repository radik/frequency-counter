package me.radik.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by radik on 11/02/14.
 */
public class SimpleFrequencyCounter<T> implements FrequencyCounter<T> {

    @Override
    public Collection<T> getMostFrequent(Iterator<T> iterator) {
        return getMostFrequent(iterator, 10);
    }

    @Override
    public Collection<T> getMostFrequent(Iterator<T> iterator, int count) {
        Map<T, Integer> map = new HashMap<T, Integer>();
        while (iterator.hasNext()){
            T word = iterator.next();
            map.put(word, map.containsKey(word)? map.get(word) + 1: 1);
        }

        Map<T, Integer> sortedMap = new TreeMap<T, Integer>(new ValueComparator(map));
        sortedMap.putAll(map);
        List<T> result = new ArrayList<T>();

        Iterator<T> wordsIterator = sortedMap.keySet().iterator();

        int i = 0;

        while (wordsIterator.hasNext() && i < count){
            result.add(wordsIterator.next());
            i++;
        }

        return result;
    }

}
