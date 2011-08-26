package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class GroupContainer<K,V> {
  private final HashMap<K, ArrayList<V>> map;

  public GroupContainer() {
    map = new HashMap<K, ArrayList<V>>();
  }
  
  public Iterable<V> getAll() {
    // Concat
    ArrayList<V> tmp = new ArrayList<V>(count());
    for (ArrayList<V> a : map.values()) {
      tmp.addAll(a);
    }
    
    return tmp;
  }
  
  public int count() {
    int tmp = 0;
    for (ArrayList<V> a : map.values()) {
      tmp += a.size();
    }
    
    return tmp;
  }

  public Iterable<V> get(K key) {
    return map.get(key);
  }

  public Iterable<V> get(K[] keys) {
    ArrayList<V> tmp = new ArrayList<V>();
    for (K key : keys) {
      tmp.addAll(map.get(key));
    }
    return tmp;
  }

  public void remove(Collection<V> list) {
    for (ArrayList<V> a : map.values()) {
      a.removeAll(list);
    }
  }
}
