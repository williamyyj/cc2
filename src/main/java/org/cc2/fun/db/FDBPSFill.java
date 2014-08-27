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
import java.util.function.BiConsumer;


/**
 *
 * @author William
 */
public class FDBPSFill implements BiConsumer<PreparedStatement, Object[]> {

    private boolean pmd_support = true;

    @Override
    public void accept(PreparedStatement ps, Object[] params) {
        ParameterMetaData pmd = null;
        if (params != null) {
            try {
                 //  參數檢查
                pmd = ps.getParameterMetaData();
                int stmtCount = pmd.getParameterCount();
                int paramsCount = params.length;
                if (stmtCount != paramsCount) {
                    throw new SQLException("Wrong number of parameters: expected " + stmtCount + ", was given " + paramsCount);
                }
                // 設定參數
                for (int i = 0; i < params.length; i++) {
                    Object o = params[i];
                    if (o != null) {
                        ps.setObject(i+1, o);
                    } else {
                        int sqlType = Types.VARCHAR;
                        if (!pmd_support) {
                            sqlType = pmd.getParameterType(i+1);
                        }
                        ps.setNull(i+1, sqlType);
                    }
                }
            } catch (Exception e) {
                pmd_support = false;
            }
        }
    }
}
