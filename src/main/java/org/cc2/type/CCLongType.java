/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author william
 */
public class CCLongType extends CCBaseType<Long> {

    public String dt() {
        return dt_long;
    }

    public Long value(Object o, Long dv) {
        try {
            if (o instanceof Number) {
                return ((Number) o).longValue();
            } else if (o instanceof String) {
                String str = ((String)o).trim();
                return str.length() > 0 ? Long.parseLong(str) : dv;
            }
        } catch (Exception e) {
            log.warn("Check " + this);
        }
        return dv;
    }

    public Long getRS(ResultSet rs, String name) throws SQLException {
        return rs.getLong(name);
    }

    public void setPS(PreparedStatement ps, int idx, Object value) throws SQLException {
        if (value == null) {
            ps.setNull(idx, Types.DECIMAL);
        } else {
            ps.setLong(idx, value(value,0L));
        }
    }

    public Class<?> nativeClass() {
        return Long.TYPE;
    }
    
    public int dt_sql() {
        return Types.DECIMAL;
    }



}
