/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2;

import java.util.List;

/**
 * @author William 取代IDB提供不同資料來源資料異動
 * @param <M>
 */
public interface ICCDP<M> {

    public M row(M mq) throws Exception;

    public List<M> rows(M mq) throws Exception;

    public <T> T fun(Class<T> c, M mq) throws Exception;

    public int execute(M mq) throws Exception;

    public int[] execute(List<M> mqs) throws Exception;
    
}
