/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2.fun;

import java.util.function.Function;

/**
 *
 * @author William
 */
public class text_quote implements Function<String, String> {

    @Override
    public String apply(String text) {
        if (text == null || text.length() == 0) {
            return "\"\"";
        }
        char[] buf = text.toCharArray();
        char b;
        String hhhh;
        int i;
        int len = text.length();
        StringBuilder sb = new StringBuilder(len + 4);
        sb.append('"');
        for (char c : buf) {
            b = c;
            switch (c) {
                case '\\':
                case '"':
                    sb.append('\\');
                    sb.append(c);
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                default:
                    if (c < ' ' || (c >= '\u0080' && c < '\u00a0')
                        || (c >= '\u2000' && c < '\u2100')) {
                        hhhh = "000" + Integer.toHexString(c);
                        sb.append("\\u").append(hhhh.substring(hhhh.length() - 4));
                    } else {
                        sb.append(c);
                    }
            }
        }
        sb.append('"');
        return sb.toString();
    }

}
