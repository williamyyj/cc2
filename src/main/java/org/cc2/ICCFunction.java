/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cc2;

/**
 * @author William
 * @param <RET>
 */

public interface ICCFunction<RET> {
    public RET apply(ICCMap m) throws Exception ; 
}
