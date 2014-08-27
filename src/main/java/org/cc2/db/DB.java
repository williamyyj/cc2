package org.cc2.db;

import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import org.cc2.CCMap;
import org.cc2.fun.db.FDBPSFill;
import org.cc2.fun.db.FDBRSMeta;
import org.cc2.fun.db.FDBRSRow;
import org.cc2.type.CCTypes;

/**
 * @author William
 */
public class DB extends DBConfig {

    private BiFunction<CCTypes, ResultSet, List<Map<String, Object>>> fdb_rs_meta;
    private BiFunction<List<Map<String, Object>>, ResultSet, Map<String, Object>> fdb_rs_row;
    private BiConsumer<PreparedStatement, Object[]> fdb_ps_fill;

    public DB() {
        this(null, "db");
    }

    public DB(String base, String id) {
        super(base, id);
        __init__();
    }

    private void __init__() {
        fdb_rs_meta = new FDBRSMeta();
        fdb_rs_row = new FDBRSRow();
        fdb_ps_fill = new FDBPSFill();
    }

    public List<Map<String, Object>> rows(String sql, Object... params) throws SQLException {
        List<Map<String, Object>> ret = new ArrayList<>();
        PreparedStatement ps = connection().prepareStatement(sql);
        ResultSet rs = null;
        try {
            fdb_ps_fill.accept(ps, params);
            rs = ps.executeQuery();
            List<Map<String, Object>> rs_meta = fdb_rs_meta.apply(types, rs);
            while (rs.next()) {
                ret.add(fdb_rs_row.apply(rs_meta, rs));
            }
        } finally {
            __release(rs, ps);
        }
        return ret;
    }

    public Map<String, Object> row(String sql, Object... params) throws SQLException {
        PreparedStatement ps = connection().prepareStatement(sql);
        ResultSet rs = null;
        try {
            fdb_ps_fill.accept(ps, params);
            rs = ps.executeQuery();
            List<Map<String, Object>> rs_meta = fdb_rs_meta.apply(types, rs);
            return (rs.next()) ? fdb_rs_row.apply(rs_meta, rs) : null;
        } finally {
            __release(rs, ps);
        }
    }

    public int execute(String sql, Object... params) throws SQLException {
        PreparedStatement ps = connection().prepareStatement(sql); 
        try {
            fdb_ps_fill.accept(ps, params);
            return  ps.executeUpdate();
        } finally {
            __release(null, ps);
        }
    }

    public static void main(String[] args) throws Exception {
        String base = System.getProperty("base", "C:\\Users\\William\\Dropbox\\resources\\prj\\sonix");
        DB db = new DB(base, "db");
        try {
            List<Map<String, Object>> rows = db.rows("select * from fae where fae_id = ? ", 2);
            rows.forEach(System.out::println);
           /// Map<String, Object> row = db.row("select * from fae where fae_id = ? ", 3);
            //System.out.println(row.get("FAE_MT"));
            //db.execute("UPDATE fae SET FAE_MT = ?  WHERE FAE_ID = ? ", new Date(), 3);
        } finally {
            db.close();
        }
    }

}
