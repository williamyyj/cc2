package org.cc2;

import org.cc2.type.CCTypes;

/**
 * @author William 取代IDB提供不同資料來源資料異動
 */
public interface ICCDP {
    
    public Object action(String fid, String aid, ICCMap params) throws Exception ;
    
    public CCTypes types();

    public String base();
    
    public ICCMap metadata(String fid, String aid)  ;
    
}
