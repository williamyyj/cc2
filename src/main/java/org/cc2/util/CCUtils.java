/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author William
 */
public class CCUtils {

    public static Map get(List<Map> list, String id, Object value) {
        return list.stream()
            .filter(m -> m != null && value.equals(m.get(id)))
            .findFirst()
            .get();
    }

    public static boolean in(Object[] items, Object item) {
        return Arrays.asList(items)
            .stream()
            .filter(o -> o.equals(item))
            .count() > 0;
    }

    public static List<Object> asList(Map m, Object... names) {
        List<Object> ret = new ArrayList<>();
        Arrays.asList(names).forEach(n -> {
            if (m.containsKey(n)) {
                ret.add(m.get(n));
            }
        });
        return ret;
    }
}
