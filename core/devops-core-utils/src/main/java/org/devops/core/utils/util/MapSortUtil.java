package org.devops.core.utils.util;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * map排序
 */
public class MapSortUtil {

/*    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("nine", 9);
        map.put("six", 6);
        map.put("name", 6);
        map.put("eight", 8);
        map.put("zero", 0);
        map.put("one", 1);
        map.put("four", 4);
        map.put("two", 2);

        // 根据map中的key值排序
        map = sortByKeyReverseOrder(map);
        System.out.println(map);
    }*/

    /**
     * 根据值排序，正序
     *
     * @param map
     * @return
     */
    public static <K, V extends Comparable> Map<K, V> sortByValue(Map<K, V> map) {
        return sortKvMap(map, Entry.comparingByValue());
    }

    /**
     * 根据值排序，倒叙
     *
     * @param map
     * @return
     */
    public static <K, V extends Comparable> Map<K, V> sortByValueReverseOrder(Map<K, V> map) {
        return sortKvMap(map, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
    }


    /**
     * 根据键排序，正序
     *
     * @param map
     * @return
     */
    public static <K extends Comparable,V> Map<K, V> sortByKey(Map<K, V> map) {
        return sortKvMap(map, Entry.comparingByKey());
    }

    /**
     * 根据键排序，倒叙
     *
     * @param map
     * @return
     */
    public static <K extends Comparable,V> Map<K, V> sortByKeyReverseOrder(Map<K, V> map) {
        return sortKvMap(map, (o1, o2) -> o2.getKey().compareTo(o1.getKey()));
    }


    private static <K, V> Map<K, V> sortKvMap(Map<K, V> map, Comparator<Entry<K,V>> c) {
        // 获取entrySet
        Set<Entry<K, V>> mapEntries = map.entrySet();

        // 使用链表来对集合进行排序，使用LinkedList，利于插入元素
        List<Entry<K, V>> result = new LinkedList<>(mapEntries);
        // 自定义比较器来比较链表中的元素
        // 基于entry的值（Entry.getValue()），来排序链表
        result.sort(c);
        // 将排好序的存入到LinkedHashMap(可保持顺序)中，需要存储键和值信息对到新的映射中。
        Map<K, V> linkMap = new LinkedHashMap<>();
        for (Entry<K, V> newEntry : result) {
            // 根据entrySet()方法遍历linkMap
            linkMap.put(newEntry.getKey(), newEntry.getValue());
        }
        return linkMap;
    }
}
