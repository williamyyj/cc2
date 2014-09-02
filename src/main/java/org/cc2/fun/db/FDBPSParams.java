/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2.fun.db;

import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import org.cc2.ICCParam;
import org.cc2.ICCType;
import org.cc2.type.CCParam;
import org.cc2.type.CCTypes;
import org.cc2.type.CCVarType;

/**
 *
 * @author William
 */
public class FDBPSParams implements BiFunction<PreparedStatement, Object[], List<ICCParam>> {

    private ICCType<Object> var_type = new CCVarType();

    @SuppressWarnings("unchecked")
    @Override
    public List<ICCParam> apply(PreparedStatement ps, Object[] params) {
        List<ICCParam> ret = new ArrayList<>();
        ParameterMetaData pmd = null;
        if (params != null) {
            //  參數檢查
            System.out.println("===== check params length ....");
            int stmtCount = 0;
            try {
                pmd = ps.getParameterMetaData();
                stmtCount = pmd.getParameterCount();
            } catch (Exception e) {
                stmtCount = params.length;
            }
            if (stmtCount != params.length) {
                throw new RuntimeException("Wrong number of parameters: expected " + stmtCount + ", was given " + params.length);
            }
            // 建立
            int idx = 1;
            for (Object o : params) {
                if (o instanceof Date){
                      Date d = (Date) o;
                      java.sql.Timestamp ts = new java.sql.Timestamp(d.getTime());
                    ret.add(new CCParam(var_type,null,ts));
                } else {
                    ret.add(new CCParam(var_type,null,o));
                }
            }
        }
        return ret;
    }

}
