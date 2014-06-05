package com.xjd.test.collection;

import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapTest {

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		TreeMap map = new TreeMap();
		
		
		map.put("JJJJJ", null);
		map.put("DDDDD", null);
		map.put("AAAAA", null);
		map.put("ZZZZZ", null);
		
		for (Entry<Object, Object> entry : (Set<Entry<Object, Object>>)map.entrySet()) {
			System.out.println(entry.getKey());
		}
	}

}
