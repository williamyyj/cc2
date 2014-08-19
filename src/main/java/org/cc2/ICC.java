/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cc2;

import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 *
 * @author William
 */
public interface ICC<K,V> extends Map<K,V> {
    default int _int(K k){
        Object o = get(k) ; 
        if(o instanceof Number){
            return ((Number)o).intValue();
        } else if( o instanceof String){
            String text = o.toString().trim();
            return Integer.parseInt(text);
        }
        throw new RuntimeException("Can't  cast int : " + o);
    }
    
      default void forEach(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        for (Map.Entry<K, V> entry : entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch(IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }
            action.accept(k, v);
        }
    }
}
