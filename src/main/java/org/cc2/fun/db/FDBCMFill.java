/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2.fun.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cc2.CC;
import org.cc2.ICCParam;
import org.cc2.ICCType;

/**
 *
 * @author William
 */
public class FDBCMFill implements BiConsumer<PreparedStatement,List<ICCParam>> {

    @SuppressWarnings("unchecked")
    @Override
    public void accept(PreparedStatement ps, List<ICCParam> params) {
        if(params!=null && params.size()>0){
            int idx = 1 ; 
            for(ICCParam p : params){
                try {
                    p.type().setPS(ps, idx++, p.value());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
