package org.cc2.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.BiFunction;
import org.cc2.CC;
import org.cc2.CCMap;
import org.cc2.ICCDP;
import org.cc2.ICCMap;
import org.cc2.type.CCTypes;

/**
 * @author William
 * @param <M> 資料模型
 */
public interface IDB extends ICCDP {

    public String database();

    public String schema();

    public String catalog();

    public Connection connection() throws Exception;

    public void close() throws Exception;

    public PreparedStatement proc_ps(ICCMap cm) throws Exception;

    public ICCMap row(ICCMap cm) throws Exception;

    default ResultSet proc_query(ICCMap cm) throws Exception {
        PreparedStatement ps = proc_ps(cm);
        ResultSet rs = ps.executeQuery();
        cm.put(CC.dp_rs, rs);
        return rs;
    }

    default ICCMap to_cm(String sql, Object... params) {
        ICCMap cm = new CCMap();
        cm.put(CC.dp_sql, sql);
        cm.put(CC.dp_params, params);
        return cm;
    }

    default <T> T query(CCTypes t, ICCMap cm, BiFunction<CCTypes, ResultSet, T> fun) throws Exception {
        try {
            return fun.apply(t, proc_query(cm));
        } finally {
            release(cm);
        }
    }

    default void release(ICCMap cm) throws Exception {
        if (cm.containsKey(CC.dp_rs)) {
            ResultSet rs = cm.as(ResultSet.class, CC.dp_rs);
            rs.close();
        }
        if (cm.containsKey(CC.dp_ps)) {
            PreparedStatement ps = cm.as(PreparedStatement.class, CC.dp_ps);
            ps.close();
        }
    }

}
