/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cc2.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import org.cc2.ICCDP;

/**
 *
 * @author William
 * @param <M> 資料模型
 */
public interface IDB<M> extends ICCDP<M> {
   public String database();
   public String schema();
   public String catalog();
   public Connection connection() throws Exception ; 
   public void close() throws Exception ; 
   public void __release(ResultSet rs, PreparedStatement ps) throws Exception;
}
