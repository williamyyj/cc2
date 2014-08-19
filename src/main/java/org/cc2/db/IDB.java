/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cc2.db;

import java.util.List;
import org.cc2.ICCDP;

/**
 *
 * @author William
 * @param <M> 資料模型
 */
public interface IDB<M> extends ICCDP<M> {
    public M row(M mq) throws Exception ; 
    public List<M> rows(M mq) throws Exception ; 
    public Object fun(M mq) throws Exception ; 
    public int save(M mq) throws Exception ; 
    public int delete(M mq) throws Exception ; 
}
