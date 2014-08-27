/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cc2;

/**
 *
 * @author William
 * @param <CTRL>
 * @param <RET>
 */
public interface ICCFunction<CTRL,RET> {
    
    public RET apply(CTRL ctrl, Object ... params) throws Exception ; 

}
