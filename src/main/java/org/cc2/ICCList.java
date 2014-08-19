/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2;

import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author William
 */
public interface ICCList extends List<Object> {

    default <T> T as(BiFunction<Object, T, T> fun, int k, T dv) {
        return fun.apply(get(k), dv);
    }
    
     default int _int(int k) { return as(CC._int,k,0) ; } 
    
    default long _long(int k) { return as(CC._long,k,0L) ; } 
    
    default double _double(int k) { return as(CC._double,k,0.0) ; } 

    default Date _date(int k) { return as(CC._date,k,new Date()) ; } 
    
}
