package org.cc2.eval;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import org.cc2.ICCMap;

public class CCPath {
    
    public static ICCGet<Object> as = (Object m,String k)->{            
        if(m==null){
            return null ; 
        } else if ( m instanceof Map){
            return ((Map)m).get(k);
        } else if (m instanceof List){
            return  ((List)m).get(Integer.parseInt(k.trim()));
        }
        return null ; 
    };
        
    public static Object path(Map m, String query, ICCGet<Object> fun) {
        String[] items = query.split(":");
        Object p = m;
        for (String key : items) {
            p = fun.get(p, key);
        }
        return p;
    }

    public static Object path(Map root, String cp) {
        return path(root,cp,as);
    }

}