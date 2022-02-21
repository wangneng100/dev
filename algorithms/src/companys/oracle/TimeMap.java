package companys.oracle;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//leetcode 981
//https://leetcode.com/problems/time-based-key-value-store/
public class TimeMap {
    Map<String, TreeMap<Integer,String>> map;

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {

        if(!map.containsKey(key)) {
            TreeMap<Integer,String> tree= new TreeMap<>();
            tree.put(timestamp, value);
            map.put(key, tree);
        } else {
            map.get(key).put(timestamp, value);
        }

    }
    
    public String get(String key, int timestamp) {
        if(!map.containsKey(key)) {
            return "";
        }
        if(map.get(key).floorEntry(timestamp) == null) return "";
        return map.get(key).floorEntry(timestamp).getValue();
    }
}
