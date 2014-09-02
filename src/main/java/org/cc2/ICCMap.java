/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author William
 */
public interface ICCMap extends Map<String, Object> {

    default <T> T as(BiFunction<Object, T, T> fun, String k, T dv) {
        return fun.apply(get(k), dv);
    }

    @SuppressWarnings("unchecked")
    default <T> T as(Class<T> c, String k) {
        return (T) get(k);
    }

    default int _int(String k) {
        return as(CC._int, k, 0);
    }

    default long _long(String k) {
        return as(CC._long, k, 0L);
    }

    default double _double(String k) {
        return as(CC._double, k, 0.0);
    }

    default Date _date(String k) {
        return as(CC._date, k, new Date());
    }

    default String _string(String k) {
        return this.getOrDefault(k, "").toString();
    }

    default String _string(String k, String dv) {
        return this.getOrDefault(k, dv).toString();
    }
    
    public String toString(String indent) ; 

}
