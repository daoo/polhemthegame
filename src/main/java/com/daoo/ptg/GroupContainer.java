/*
 * Copyright (c) 2009-2011 Daniel Oom, see license.txt for more info.
 */

package com.daoo.ptg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class GroupContainer<K,V> {
  private final HashMap<K, ArrayList<V>> map;

  public GroupContainer() {
    map = new HashMap<K, ArrayList<V>>();
  }
  
  private ArrayList<V> getMakeDefault(K key) {
    ArrayList<V> value = map.get(key);
    
    if (value == null) {
      ArrayList<V> tmp = new ArrayList<V>();
      map.put(key, tmp);
      return tmp; 
    } else {
      return map.get(key);
    }
  }
  
  public int count() {
    int tmp = 0;
    for (ArrayList<V> a : map.values()) {
      tmp += a.size();
    }
    
    return tmp;
  }
  
  public Iterable<V> getAll() {
    // Concat
    ArrayList<V> tmp = new ArrayList<V>(count());
    for (ArrayList<V> a : map.values()) {
      tmp.addAll(a);
    }
    
    return tmp;
  }

  public Iterable<V> get(K key) {
    return getMakeDefault(key);
  }

  public Iterable<V> get(K[] keys) {
    ArrayList<V> tmp = new ArrayList<V>();
    for (K key : keys) {
      tmp.addAll(getMakeDefault(key));
    }
    return tmp;
  }

  public void remove(Collection<V> list) {
    for (ArrayList<V> a : map.values()) {
      a.removeAll(list);
    }
  }

  public void add(K key, V value) {
    getMakeDefault(key).add(value);
  }
}
