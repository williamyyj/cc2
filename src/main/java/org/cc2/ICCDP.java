/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cc2;

/**
 * @author William
 *   取代IDB提供不同資料來源資料異動
 */
public interface ICCDP<M> {
    public Object execute(M mq) throws Exception ; 
}
