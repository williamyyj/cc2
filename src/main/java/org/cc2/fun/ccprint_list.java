/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cc2.fun;

import java.util.Collection;
import java.util.List;
import org.cc2.CC;
import org.cc2.ICCPrint;

/**
 * @author William
 */

public class ccprint_list implements ICCPrint<Collection<Object>>  {

    @Override
    public String print(Collection<Object> list, String base, String indent) {
       if(list==null) return "[]";
        StringBuilder sb = new StringBuilder();
        String next = (base == null) ? indent : base + indent;
        sb.append("[\n");
        list.forEach(
            (o) -> {
                CC.indent(sb, next, CC.json(o, next, indent));
                sb.append(",\n");
            }
        );
        if (list.size() > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append('\n');
        CC.indent(sb, base, "]");
        return sb.toString();    
    }
    
}
