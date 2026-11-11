import java.util.*;

public class HashMapeg{
    public static void main (String [] args){
        HashMap<Integer,String> students = new HashMap<>();
        students.put(1,"Anu");
        students.put(2,"banu");
        students.put(3,"charlie");
        students.put(4,"daniel");

        for(int key : students.keySet()){
            System.out.println(key + " "+students.get(key));
        }
    }
}