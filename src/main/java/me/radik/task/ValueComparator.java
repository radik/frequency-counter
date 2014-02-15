package me.radik.task;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by radik on 13/02/14.
 */
public class ValueComparator<T> implements Comparator<T> {
    private Map<T, Integer> _map;

    public ValueComparator(Map<T, Integer> map) {
        _map = map;
    }

    @Override
    public int compare(T key1, T key2) {
        return _map.get(key2).compareTo(_map.get(key1));
    }
}
