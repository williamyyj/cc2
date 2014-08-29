/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cc2.db;

import java.util.List;
import java.util.Map;
import org.cc2.ICCDP;

/**
 *
 * @author William
 */
public class Dao implements ICCDP<Map<String,Object>>{
    
    private final DB db;
    
    public Dao(DB db){
        this.db = db ; 
    }

    @Override
    public Map<String, Object> row(Map<String, Object> mq) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Map<String, Object>> rows(Map<String, Object> mq) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T fun(Class<T> c, Map<String, Object> mq) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int execute(Map<String, Object> mq) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] execute(List<Map<String, Object>> mqs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
