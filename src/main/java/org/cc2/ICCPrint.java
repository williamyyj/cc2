/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cc2;

/**
 *
 * @author William
 * @param <Out>
 * @param <Model>
 * 
 */
public interface ICCPrint<Model> {
    public String print(Model m, String base, String indent);
}
