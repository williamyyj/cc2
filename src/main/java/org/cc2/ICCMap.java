/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author William
 */
public interface ICCMap extends Map<String, Object> {

    default <T> T  as ( Function<Object,Optional<T>> fun, Object k , T dv){
       return fun.apply(get(k)).orElse(dv);
    }



}
