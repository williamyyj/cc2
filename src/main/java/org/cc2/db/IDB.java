/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cc2.db;

import java.sql.SQLException;
import java.util.List;
import org.cc2.ICCDP;

/**
 *
 * @author William
 * @param <M> 資料模型
 */
public interface IDB<M> extends ICCDP<M> {
   public String base();
   public String database();
   public String schema();
   public String catalog();
   public M row(String sql , Object ... params) throws SQLException ; 
   public List<M> rows(String sql , Object ... params) throws SQLException ; 
   public Object fun(String sql , Object ... params) throws SQLException ; 
   public int execut(String sql , Object ... params) throws SQLException ; 
   public int[] batch(String sql, List<Object[]> data) throws SQLException ; 
}
