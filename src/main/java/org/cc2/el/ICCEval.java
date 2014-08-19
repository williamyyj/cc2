/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cc2.el;

import org.cc2.CCException;

/**
 *
 * @author William
 */
public interface ICCEval<RET,M> {
    public RET eval(M model, String expression) throws CCException ; 
}
