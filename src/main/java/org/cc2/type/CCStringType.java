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

public class CCStringType extends CCBaseType<String> {

    public String dt() {
        return dt_string;
    }

    public String value(Object o, String dv) {
        return (o!=null) ? o.toString() : dv;
    }

    public String getRS(ResultSet rs, String name) throws SQLException {
        return rs.getString(name);
    }

    public void setPS(PreparedStatement ps, int idx, Object value) throws SQLException {
       if(value==null){
            ps.setNull(idx, Types.VARCHAR);
        } else {
            ps.setString(idx, value.toString());
        }
    }

    public Class<?> nativeClass() {
        return String.class;
    }

    public int dt_sql() {
        return Types.VARCHAR;
    }


    public String sql_value(Object value) {
        if(value!=null && !"null".equalsIgnoreCase((value.toString()))){
            String ret = value.toString();
            ret = ret.replaceAll("'","''");
            return "'" + ret + "'";

        }
        return "NULL";
    }


}
