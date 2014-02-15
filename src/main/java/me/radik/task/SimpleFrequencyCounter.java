package me.radik.task;

import java.util.*;

/**
 * Created by radik on 11/02/14.
 */
public class SimpleFrequencyCounter implements FrequencyCounter {

    @Override
    public Collection<String> getMostFrequent(Iterator<String> iterator) {
        return getMostFrequent(iterator, 10);
    }

    @Override
    public Collection<String> getMostFrequent(Iterator<String> iterator, int count) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        while (iterator.hasNext()){
            String word = iterator.next();
            map.put(word, map.containsKey(word)? map.get(word) + 1: 1);
        }

        Map<String, Integer> sortedMap = new TreeMap<String, Integer>(new ValueComparator(map));
        sortedMap.putAll(map);
        List<String> result = new ArrayList<String>();

        Iterator<String> wordsIterator = sortedMap.keySet().iterator();

        int i = 0;

        while (wordsIterator.hasNext() && i < count){
            result.add(wordsIterator.next());
            i++;
        }

        return result;
    }
}
