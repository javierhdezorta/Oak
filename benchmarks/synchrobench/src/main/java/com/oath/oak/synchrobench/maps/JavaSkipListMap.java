package com.oath.oak.synchrobench.maps;

import com.oath.oak.synchrobench.contention.abstractions.CompositionalOakMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListMap;

public class JavaSkipListMap<K, V> implements CompositionalOakMap<K, V> {

    private ConcurrentSkipListMap<MyBuffer, MyBuffer> skipListMap;

    public JavaSkipListMap() {
        skipListMap = new ConcurrentSkipListMap<>();
    }

    @Override
    public boolean getOak(Object key) {
        return skipListMap.get(key) != null;
    }

    @Override
    public void putOak(Object key, Object value) {
        skipListMap.put((MyBuffer) key, (MyBuffer) value);
    }

    @Override
    public boolean putIfAbsentOak(Object key, Object value) {
        return skipListMap.putIfAbsent((MyBuffer) key, (MyBuffer) value) == null;
    }

    @Override
    public void removeOak(Object key) {
        skipListMap.remove(key);
    }

    @Override
    public boolean computeIfPresentOak(K key) {
        return false;
    }

    @Override
    public void computeOak(K key) {

    }

    @Override
    public boolean ascendOak(K from, int length) {
        Iterator iter = skipListMap.tailMap((MyBuffer) from, true).keySet().iterator();
        int i = 0;
        while (iter.hasNext() && i < length) {
            i++;
            iter.next();
        }
        return i == length;
    }

    @Override
    public boolean descendOak(K from, int length) {
        Iterator iter = skipListMap.descendingMap().tailMap((MyBuffer) from, true).keySet().iterator();
        int i = 0;
        while (iter.hasNext() && i < length) {
            i++;
            iter.next();
        }
        return i == length;
    }

    @Override
    public void clear() {
        skipListMap = new ConcurrentSkipListMap<>();
    }

    @Override
    public int size() {
        return skipListMap.size();
    }
}
