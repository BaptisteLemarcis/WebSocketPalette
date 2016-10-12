package ca.uqac.liara;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Baptiste on 10/11/2016.
 */
public class JSonParser {

    public static String encode(Object[] o){
        String parsed = "{\n";
        for(int i = 0; i < o.length; i++){
            parsed += "\tinput"+(i+1)+":\""+o[i].toString()+"\""+((i==o.length-1)?"":",")+"\n";
        }
        return parsed+"\n}";
    }

    public static Object[] decode(String s){
        ArrayList<Object> obj = new ArrayList<Object>();
        String toRemove = "{\n\t";
        s = s.substring(s.indexOf(toRemove)+toRemove.length());
        s = s.substring(0,s.indexOf("\n}"));
        s = s.replaceAll("\n\t","");
        for(String a : s.split(",")){
            obj.add(a.split(":")[1].replaceAll("\"",""));
        }
        return obj.toArray();
    }

}
