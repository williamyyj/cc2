/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author william
 */
public interface ICCType<E> {

    public final static String dt_var = "var";
    public final static String dt_bool = "bool";
    public final static String dt_int = "int";
    public final static String dt_long = "long";
    public final static String dt_double = "double";
    public final static String dt_date = "date";
    public final static String dt_string = "string";
    public final static String dt_blob = "blob";
    public final static String dt_clob = "clob";
    public final static String dt_array = "array";
    public final static String dt_object = "object";
    public final static String fmt_datetime = "yyyy-MM-dd HH:mm:ss";
    public final static String fmt_date = "yyyy-MM-dd";

    public String dt();

    public Class<?> nativeClass();

    public int dt_sql();

    public E value(Object o);

    public E value(Object o, E dv);

    public E getRS(ResultSet rs, String name) throws SQLException;

    public void setPS(PreparedStatement ps, int idx, Object value) throws SQLException;

    public String json_value(Object value);

    public String sql_value(Object value);

}
