/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cc2.fun.db;

import java.sql.ResultSet;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author William
 */
public class FunLoadRS implements Function<ResultSet,Map<String,Object>>{

    @Override
    public Map<String, Object> apply(ResultSet t) {
        return null ; 
    }
    
}
