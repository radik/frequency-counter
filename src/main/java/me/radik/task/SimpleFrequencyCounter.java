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
            T item = iterator.next();
            map.put(item, map.containsKey(item)? map.get(item) + 1: 1);
        }

        Map<T, Integer> sortedMap = new TreeMap<T, Integer>(new ValueComparator(map));
        sortedMap.putAll(map);
        List<T> result = new ArrayList<T>();

        Iterator<T> keysIterator = sortedMap.keySet().iterator();

        int i = 0;

        while (keysIterator.hasNext() && i < count){
            result.add(keysIterator.next());
            i++;
        }

        return result;
    }

}
