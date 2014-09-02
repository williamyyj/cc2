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
import org.cc2.ICCParam;
import org.cc2.ICCType;
import org.cc2.type.CCParam;
import org.cc2.type.CCTypes;

/**
 *
 * @author William
 */
public class FDBRSMeta implements BiFunction<CCTypes, ResultSet, List<ICCParam>> {

    @Override
    @SuppressWarnings("unchecked")
    public List<ICCParam> apply(CCTypes types, ResultSet rs) {
        List<ICCParam> ret = new ArrayList<>();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int len = rsmd.getColumnCount();
            for (int i = 1; i <= len; i++) {
                String name  = rsmd.getColumnName(i);        
                int jdbc = rsmd.getColumnType(i);
                String sqlType = rsmd.getColumnTypeName(i);
                ICCType<?> type = types.type(jdbc, sqlType);
                ret.add(new CCParam(type,name,null));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ret;
    }

}
