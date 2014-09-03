package org.cc2;

import org.cc2.type.CCTypes;

/**
 * @author William 取代IDB提供不同資料來源資料異動
 * @param <PARAM> fid/actId
 */
public interface ICCDP<PARAM> {
    
    public Object action(String fid, String aid, PARAM params) throws Exception ;
    
    public CCTypes types();

    public String base();
    
    public ICCMap metadata(String fid, String aid)  ;
    
}
