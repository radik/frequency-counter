package me.radik.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This implementation of {@link me.radik.task.FrequencyCounter} counts frequency of elements from iterator.
 * All work done in memory, if {@link java.lang.OutOfMemoryError} thrown during counting,
 * consider increasing the heap size.
 */
public class InMemoryFrequencyCounter<T> implements FrequencyCounter<T> {

    private class CountValuePair<T> implements Comparable<CountValuePair<T>> {
        private T value;
        private Integer count;

        private CountValuePair(T value, Integer count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public int compareTo(CountValuePair<T> pair) {
            if (pair == null){
                return 1;
            }
            return pair.getCount().compareTo(getCount());
        }

        private Integer getCount() {
            return count;
        }

        private T getValue() {
            return value;
        }
    }


    @Override
    public Collection<T> getMostFrequent(Iterator<T> iterator) {
        return getMostFrequent(iterator, 10);
    }

    @Override
    public Collection<T> getMostFrequent(Iterator<T> iterator, int count) {

        Set<T> itemsSet = new HashSet<T>();
        List<T> itemsList = new ArrayList<T>();
        List<CountValuePair<T>> countValueList = new ArrayList<CountValuePair<T>>();

        while (iterator.hasNext()) {
            T item = iterator.next();
            itemsList.add(item);
            itemsSet.add(item);
        }

        for (T item : itemsSet) {
            countValueList.add(new CountValuePair<T>(item, Collections.frequency(itemsList, item)));
        }

        Collections.sort(countValueList);

        List<T> result = new ArrayList<T>();
        int i = 0;
        int valueListSize = countValueList.size();

        while (i < count && i < valueListSize) {
            result.add(countValueList.get(i).getValue());
            i++;
        }

        return result;
    }
}
