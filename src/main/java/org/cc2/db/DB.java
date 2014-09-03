package org.cc2.db;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import org.cc2.CC;
import org.cc2.ICCMap;
import org.cc2.ICCParam;
import org.cc2.fun.db.FDBCMFill;
import org.cc2.fun.db.FDBPSParams;
import org.cc2.fun.db.FDBRSMeta;
import org.cc2.fun.db.FDBRSRow;
import org.cc2.type.CCTypes;
import org.cc2.util.CCCache;

/**
 * @author Williamf
 */
public class DB extends DBConfig implements IDB {

    private BiFunction<CCTypes, ResultSet, List<ICCParam>> fdb_rs_meta;
    private BiFunction<List<ICCParam>, ResultSet, ICCMap> fdb_rs_row;
    private BiFunction<PreparedStatement, Object[], List<ICCParam>> fdb_ps_params;
    private BiConsumer<PreparedStatement, List<ICCParam>> fdb_cm_fill;

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
        fdb_ps_params = new FDBPSParams();
        fdb_cm_fill = new FDBCMFill();
    }

    @SuppressWarnings("unchecked")
    @Override
    public PreparedStatement proc_ps(ICCMap cm) throws Exception {
        PreparedStatement ps = connection().prepareStatement(cm._string(CC.dp_sql));
        cm.put(CC.dp_ps, ps);
        List<ICCParam> params = null;
        Object p = cm.get(CC.dp_params);
        if (p != null && p.getClass().isArray()) {
            params = fdb_ps_params.apply(ps, (Object[]) p);
        } else {
            params = (List<ICCParam>) p;
        }
        fdb_cm_fill.accept(ps, params);
        return ps;
    }

    public ICCMap row(String sql, Object... params) throws Exception {
        return row(to_cm(sql, params));
    }

    @Override
    public ICCMap row(ICCMap cm) throws Exception {
        return query(types(), cm, (t, rs) -> {
            try {
                List<ICCParam> rs_meta = fdb_rs_meta.apply(t, rs);
                return (rs.next()) ? fdb_rs_row.apply(rs_meta, rs) : null;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return null;
        });
    }

    public List<ICCMap> rows(String sql, Object... params) throws Exception {
        return rows(to_cm(sql, params));
    }

    public List<ICCMap> rows(ICCMap cm) throws Exception {
        return query(types(), cm, (t, rs) -> {
            List<ICCMap> ret = new ArrayList<>();
            try {
                List<ICCParam> rs_meta = fdb_rs_meta.apply(t, rs);
                while (rs.next()) {
                    ret.add(fdb_rs_row.apply(rs_meta, rs));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return ret;
        });
    }

    public Object fun(String sql, Object... params) throws Exception {
        return fun(to_cm(sql, params));
    }

    public Object fun(ICCMap cm) throws Exception {
        return query(types(), cm, (t, rs) -> {
            try {
                return (rs.next()) ? rs.getObject(1) : 0;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return null;
        });
    }

    public Object action(ICCMap qm) {
        String aid = qm._string(CC.dp_aid);
        String sql = qm._string(CC.dp_sql);
        CC.act act = CC.act.valueOf(aid);
        return null;
    }

    @Override
    public String database() {
        return _string("database");
    }

    @Override
    public String schema() {
        return _string("schema");
    }

    @Override
    public String catalog() {
        return _string("catalog");
    }

    @Override
    public Object action(String fid, String aid, ICCMap params) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ICCMap metadata(String fid, String aid) {
        File f = new File(base(), "module/" + fid + ".dao");
        ICCMap dao = CCCache.load_map(f, "UTF-8");
        return dao.as(ICCMap.class, aid);
    }

    public static void main(String[] args) throws Exception {
        long ts = System.nanoTime();
        String base = System.getProperty("base", "C:\\Users\\William\\Dropbox\\resources\\prj\\sonix");
        DB db = new DB(base, "db");
        try {
            //db.execute("UPDATE fae SET FAE_MT = ?  WHERE FAE_ID = ? ", new Date(), 3);
            Calendar cal = Calendar.getInstance();
            cal.set(2014, 0, 1);
            List<ICCMap> rows = db.rows("select * from fae where fae_mt > ? ", cal.getTime());
            rows.forEach(System.out::println);
            Object count = db.fun("select count(*) from fae");
            System.out.println(count);

        } finally {
            db.close();
        }
        long te = System.nanoTime();
        System.out.println("===== time : " + (te - ts) / 1E9);

    }

}
