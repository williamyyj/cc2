/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2.fun.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import org.cc2.type.CCTypes;
import org.cc2.type.ICCType;

/**
 *
 * @author William
 */
public class FDBRSMeta implements BiFunction<CCTypes, ResultSet, List<Map<String, Object>>> {

    @Override
    public List<Map<String, Object>> apply(CCTypes types, ResultSet rs) {
        List<Map<String, Object>> ret = new ArrayList<>();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int len = rsmd.getColumnCount();
            for (int i = 1; i <= len; i++) {
                Map<String,Object> m = new HashMap<>();
                String name  = rsmd.getColumnName(i);
                m.put("name", name);
                m.put("alias", name);
                int jdbc = rsmd.getColumnType(i);
                m.put("jdbc", jdbc);
                String sqlType = rsmd.getColumnTypeName(i);
                m.put("sqlType", sqlType);
                ICCType<?> type = types.type(jdbc, sqlType);
                m.put("type", type);
                ret.add(m);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ret;
    }

}
