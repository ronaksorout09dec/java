import java.util.*;

public class HashMapType{
    public static void main(String[] args){
        HashMap<Integer, String> map = new HashMap<>();
        map.put(3, "Priya");
        map.put(1, "Ajay");
        map.put(7, "Piyush");
        map.put(5, "Ajeet");
        map.put(2, "Ronak");

        System.out.println("HashMap class map looks like :-" + map);

        LinkedHashMap<Integer, String> map1 = new LinkedHashMap<>();
        map1.put(3, "Priya");
        map1.put(1, "Ajay");
        map1.put(7, "Piyush");
        map1.put(5, "Ajeet");
        map1.put(2, "Ronak");

        System.out.println("LinkedHashMap class map looks like :-" + map1);


        TreeMap<Integer, String> map2 = new TreeMap<>();
        map2.put(3, "Priya");
        map2.put(1, "Ajay");
        map2.put(7, "Piyush");
        map2.put(5, "Ajeet");
        map2.put(2, "Ronak");

        System.out.println("TreeMap class map looks like :-" + map2);
    }
}