package me.radik.task;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by radik on 13/02/14.
 */
public class ValueComparator implements Comparator<String> {
    private Map<String, Integer> _map;

    public ValueComparator(Map<String, Integer> map) {
        _map = map;
    }

    @Override
    public int compare(String key1, String key2) {
        return _map.get(key2).compareTo(_map.get(key1));
    }
}
