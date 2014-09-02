/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cc2.fun.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cc2.CCMap;
import org.cc2.ICCMap;
import org.cc2.ICCParam;
import org.cc2.ICCType;


/**
 *
 * @author William
 */
public class FDBRSRow implements BiFunction<List<ICCParam>, ResultSet, ICCMap>{

    @Override
    public ICCMap apply(List<ICCParam> params, ResultSet rs) {
        CCMap row = new CCMap();
        if(params!=null){
            params.forEach( p->{
                try {
                    row.put(p.name(), p.type().getRS(rs, p.name()) );
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
             }); 
        }
        return row ; 
    }
   
}
