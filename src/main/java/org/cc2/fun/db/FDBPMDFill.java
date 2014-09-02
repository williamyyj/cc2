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
import java.util.Arrays;
import java.util.Date;
import java.util.function.BiConsumer;

/**
 *
 * @author William
 */
public class FDBPMDFill implements BiConsumer<PreparedStatement, Object[]> {

    @Override
    public void accept(PreparedStatement ps, Object[] params) {
        boolean pmd_support = true;
        ParameterMetaData pmd = null;
        if (params != null) {
            try {
                //  參數檢查
                System.out.println("===== check params length ....");
                try {
                    pmd = ps.getParameterMetaData();
                    int stmtCount = pmd.getParameterCount();
                    int paramsCount = params.length;
                    if (stmtCount != paramsCount) {
                        throw new SQLException("Wrong number of parameters: expected " + stmtCount + ", was given " + paramsCount);
                    }
                } catch (Exception e) {
                    pmd_support = false;
                }

                // 設定參數
                System.out.println("===== check setting params ");
                for (int i = 0; i < params.length; i++) {
                    Object o = params[i];
                    if (o != null) {
                        proc_set_ps(ps, i + 1, o);
                    } else {
                        int sqlType = Types.VARCHAR;
                        if (!pmd_support) {
                            sqlType = pmd.getParameterType(i + 1);
                        }
                        ps.setNull(i + 1, sqlType);
                    }
                }
            } catch (Exception e) {
                pmd_support = false;
                e.printStackTrace();
            }
        }
    }

    private void proc_set_ps(PreparedStatement ps, int idx, Object o) throws SQLException {
        System.out.println("====== " + idx + ", " + o);
        if (o instanceof Date) {
            Date d = (Date) o;
            java.sql.Timestamp ts = new java.sql.Timestamp(d.getTime());
            ps.setTimestamp(idx, ts);
        } else {
            ps.setObject(idx, o);
        }
    }
}
