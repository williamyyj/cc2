


package org.cc2.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.cc.org.apache.tomcat.jdbc.pool.PoolProperties;
import org.cc2.CC;
import org.cc2.util.CCConfig;
import org.cc2.CCMap;
import org.cc2.type.CCTypes;

/**
 *
 * @author William
 */
public class DBConfig extends CCConfig {

    protected static Map<String, DataSource> mds;
    private String dsId ;
    protected CCMap db_cfg ;
    protected CCTypes types ; 
    
    
    public DBConfig(){
        this(null,"db");
    }
    
    public  DBConfig(String base, String id ){
        super(base);
        __init__(id);
    }
    
    private void __init__(String id){
       db_cfg = (CCMap) get(id);
        if(db_cfg==null){
            throw new RuntimeException("Can't find tag id");
        }
        dsId = db_cfg._string("id","dsId"); 
        types = new CCTypes(db_cfg._string("database"));
    }

    public Connection connection() throws Exception {
        Connection conn = (Connection) db_cfg.get(CC.dp_conn);
        if (conn == null) {
            conn = getDataSource(dsId).getConnection();
            db_cfg.put(CC.dp_conn,conn);
        }
        return conn;
    }

    protected DataSource getDataSource(String id) {
        DataSource old = mds().get(id);
        if (old == null) {
            try {
                PoolProperties p = new PoolProperties();
                p.setUrl(db_cfg._string("url"));
                p.setDriverClassName(db_cfg._string("driver"));
                p.setUsername(db_cfg._string("user"));
                p.setPassword(db_cfg._string("password"));
                p.setJmxEnabled(true);
                p.setTestWhileIdle(false);
                p.setTestOnBorrow(true);
                p.setValidationQuery("SELECT 1");
                p.setTestOnReturn(false);
                p.setValidationInterval(30000);
                p.setTimeBetweenEvictionRunsMillis(30000);
                p.setMaxActive(100);
                p.setInitialSize(3);
                p.setMaxWait(10000);
                p.setRemoveAbandonedTimeout(60);
                p.setMinEvictableIdleTimeMillis(30000);
                p.setMinIdle(10);
                p.setLogAbandoned(true);
                p.setRemoveAbandoned(true);
                p.setJdbcInterceptors(
                    "org.cc.org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                    + "org.cc.org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
                org.cc.org.apache.tomcat.jdbc.pool.DataSource ds = new org.cc.org.apache.tomcat.jdbc.pool.DataSource();
                ds.setPoolProperties(p);
                mds().put(id, ds);
                old = ds;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return old;
    }

    protected Map<String, DataSource> mds() {
        if (mds == null) {
            mds = new HashMap<>();
        }
        return mds;
    }
    
    public CCTypes types(){
        return types ; 
    }
    
     public void __release(ResultSet rs, PreparedStatement ps) throws SQLException {
        if (rs != null) {
            rs.close();
            rs = null;
        }
        if (ps != null) {
            ps.close();
            ps = null;
        }
    }
    
    public void close() throws Exception{
        Connection conn = (Connection) db_cfg.get(CC.dp_conn);
        if(conn!=null){
            conn.close();
        }
    }
    
    
    

    
    
}
