


package org.cc2.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.cc.org.apache.tomcat.jdbc.pool.PoolProperties;
import org.cc2.CCConfig;
import org.cc2.CCMap;
import org.cc2.type.CCTypes;

/**
 *
 * @author William
 */
public class DBConfig extends CCConfig {

    protected static Map<String, DataSource> mds;
    private Connection conn;
    private String dsId ;
    protected CCMap res ;
    protected CCTypes types ; 
    
    
    public DBConfig(){
        this(null,"db");
    }
    
    public  DBConfig(String base, String id ){
        super(base);
        __init__(id);
    }
    
    private void __init__(String id){
       res = (CCMap) get(id);
        if(res==null){
            throw new RuntimeException("Can't find tag id");
        }
        dsId = res._string("id","dsId"); 
        types = new CCTypes(res._string("database"));
    }

    public Connection connection() throws SQLException {
        if (conn == null) {
            conn = getDataSource(dsId).getConnection();
        }
        return conn;
    }

    protected DataSource getDataSource(String id) {
        DataSource old = mds().get(id);
        if (old == null) {
            try {
                PoolProperties p = new PoolProperties();
                p.setUrl(res._string("url"));
                p.setDriverClassName(res._string("driver"));
                p.setUsername(res._string("user"));
                p.setPassword(res._string("password"));
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
    
     protected void __release(ResultSet rs, PreparedStatement ps) throws SQLException {
        if (rs != null) {
            rs.close();
            rs = null;
        }
        if (ps != null) {
            ps.close();
            ps = null;
        }
    }
    
    public void close() throws SQLException{
        if(conn!=null){
            conn.close();
        }
    }
    

    
    
}
