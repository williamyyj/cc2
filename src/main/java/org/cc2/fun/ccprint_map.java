/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2.fun;

import java.util.Map;
import org.cc2.CC;
import org.cc2.ICCPrint;

/**
 *
 * @author William
 */
public class ccprint_map implements ICCPrint<Map<String, Object>> {

    @Override
    public String print(Map<String, Object> m, String base, String indent) {
        if (m == null) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        String next = (base != null) ? base + indent : indent;
        sb.append("{\n");
        m.forEach((k, v) -> {
            CC.indent(sb, next, CC.json(k));
            sb.append(" : ").append(CC.json(v, next, indent));
            sb.append(",\n");
        });
        if (m.size() > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append('\n');
        CC.indent(sb, base, "}");
        return sb.toString();
    }

}
