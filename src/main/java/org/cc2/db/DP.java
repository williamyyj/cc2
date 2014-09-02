/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2.db;

import java.io.File;
import org.cc2.CCMap;
import org.cc2.ICCDP;
import org.cc2.ICCMap;
import org.cc2.type.CCTypes;
import org.cc2.util.CCCache;

/**
 *
 * @author William
 */
public class DP implements ICCDP<ICCMap> {

    private DB db;

    public DP(DB db) {
        this.db = db;
    }

    @Override
    public Object action(String fid, String aid, ICCMap params) throws Exception {
        ICCMap qm = QueryModel.parser_command(this, fid, aid, params);
        return db.action(qm);
    }

    @Override
    public CCTypes types() {
        return db.types();
     }

    @Override
    public String base() {
        return db.base() ; 
    }

    @Override
    public ICCMap metadata(String fid, String aid) {
        File f = new File(base(), "module/" + fid + ".dao");
        ICCMap dao = CCCache.load_map(f, "UTF-8");
       return dao.as(ICCMap.class, aid);
    }




}
