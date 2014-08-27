/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cc2.fun.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cc2.type.ICCType;


/**
 *
 * @author William
 */
public class FDBRSRow implements BiFunction<List<Map<String,Object>>, ResultSet, Map<String,Object>>{

    @Override
    public Map<String, Object> apply(List<Map<String, Object>> meta, ResultSet rs) {
        Map<String,Object> row = new HashMap<>();
        meta.forEach(p->{
            try {
                String alias = (String) p.get("alias");
                ICCType<?> type = (ICCType<?>) p.get("type");
                row.put(alias, type.getRS(rs, alias));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        return row ; 
    }
   
}
