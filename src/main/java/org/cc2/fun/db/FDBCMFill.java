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
import org.cc2.type.CCTypes;
import org.cc2.type.ICCType;

/**
 *
 * @author William
 */
public class FDBCMFill implements BiConsumer<PreparedStatement, Map<String, Object>> {

    @SuppressWarnings("unchecked")
    @Override
    public void accept(PreparedStatement ps, Map<String, Object> cm) {
        List<Object> cols = CC.as(List.class, cm, "$cols");
        if (cols != null) {
            int idx = 1;
            for (Object o : cols) {
                String k = (String) o;
                Map m = CC.as(Map.class, cm, k);
                proc_set_ps(ps, idx++, m);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void proc_set_ps(PreparedStatement ps, int idx, Map m) {
        try {
            ICCType type = CC.as(ICCType.class, m, "type");
            Object value = m.get("value");
            type.setPS(ps, idx, value);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
