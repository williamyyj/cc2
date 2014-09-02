/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cc2.type;

import org.cc2.ICCParam;
import org.cc2.ICCType;

/**
 *
 * @author William
 */
public class CCParam<T> implements ICCParam<T>{
    
    protected String name ; 
    protected T value ; 
    protected ICCType<T> type ; 
    
    public CCParam(ICCType<T> type , String name, Object value){
        this.name = name ; 
        this.type = type ; 
        this.value = type.check(value); 
    }
    
    @Override
    public String name() {
        return name ; 
     }

    @Override
    public T value() {
        return value ; 
    }


    public ICCType<T> type() {
        return type ; 
    }
    
}
