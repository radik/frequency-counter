package me.radik.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * This implementation of {@link me.radik.task.FrequencyCounter} counts frequency of elements from iterator.
 * All work done in memory, if {@link java.lang.OutOfMemoryError} thrown during counting,
 * consider increasing the heap size.
 * Internally uses {@link java.util.concurrent.ForkJoinPool} and {@link java.util.concurrent.RecursiveTask}.
 */
public class ParallelFrequencyCounter<T> implements FrequencyCounter<T> {
    @Override
    public Collection<T> getMostFrequent(Iterator<T> iterator) {
        return getMostFrequent(iterator, 10);
    }

    @Override
    public Collection<T> getMostFrequent(Iterator<T> iterator, int count) {
        List<T> items = new ArrayList<T>();
        while (iterator.hasNext()) {
            items.add(iterator.next());
        }

        FrequencyCountTask fcTask = new FrequencyCountTask(items);
        ForkJoinPool pool = new ForkJoinPool();
        Map<T, Integer> rateMap = pool.invoke(fcTask);

        Map<T, Integer> sortedMap = new TreeMap<T, Integer>(new ValueComparator<T>(rateMap));
        sortedMap.putAll(rateMap);
        List<T> result = new ArrayList<T>();

        Iterator<T> keysIterator = sortedMap.keySet().iterator();

        int i = 0;

        while (keysIterator.hasNext() && i < count) {
            result.add(keysIterator.next());
            i++;
        }

        return result;
    }

    private class FrequencyCountTask extends RecursiveTask<Map<T, Integer>> {
        private final List<T> _data;
        private final int _start;
        private final int _end;
        private int _threshold = 1000;

        private FrequencyCountTask(List<T> data, int start, int end) {
            _data = data;
            _start = start;
            _end = end;
        }

        private FrequencyCountTask(List<T> data) {
            this(data, 0, data.size());
        }

        private Map<T, Integer> computeDirectly() {
            Map<T, Integer> result = new HashMap<T, Integer>();
            for (int i = _start; i < _end; i++) {
                int newCount = result.containsKey(_data.get(i)) ? result.get(_data.get(i)) + 1 : 1;
                result.put(_data.get(i), newCount);
            }
            return result;
        }

        private Map<T, Integer> merge(Map<T, Integer> source, Map<T, Integer> dest) {
            Map<T, Integer> result = new HashMap<T, Integer>(dest);
            for (T key : source.keySet()) {
                if (result.containsKey(key)) {
                    result.put(key, result.get(key) + source.get(key));
                } else {
                    result.put(key, source.get(key));
                }
            }
            return result;
        }

        @Override
        protected Map<T, Integer> compute() {
            int length = _end - _start;
            if (length < _threshold) {
                return computeDirectly();
            }

            int split = length / 2;

            FrequencyCountTask left = new FrequencyCountTask(_data, _start, _start + split);
            FrequencyCountTask right = new FrequencyCountTask(_data, _start + split, _end);
            left.fork();
            return merge(right.compute(), left.join());
        }
    }
}
