package me.radik.task;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by radik on 13/02/14.
 * Comparator for sorting {@link java.util.Map} by value.
 */
public class ValueComparator<T> implements Comparator<T> {
    private Map<T, Integer> _map;
    private boolean _descend;

    /**
     * @param map Instance of {@link java.util.Map} which sorting by this comparator.
     */
    public ValueComparator(Map<T, Integer> map) {
        this(map, true);
    }


    /**
     * @param map Instance of {@link java.util.Map} which sorting by this comparator.
     * @param descend Order for sorting.
     */
    public ValueComparator(Map<T, Integer> map, boolean descend) {
        _map = map;
        _descend = descend;
    }

    @Override
    public int compare(T key1, T key2) {
        return _descend ? _map.get(key2).compareTo(_map.get(key1)) : _map.get(key1).compareTo(_map.get(key2));
    }
}
