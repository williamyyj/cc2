package org.cc2;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.cc2.fun.ccprint_list;
import org.cc2.fun.ccprint_map;
import org.cc2.fun.text_quote;

/**
 * @author William
 */

public class CC {

    public final static String dp_cmd = "$cmd" ; 
    public final static String dp_fid = "$fid" ; 
    public final static String dp_aid = "$aid" ; 
    public final static String dp_sql = "$sql" ; 
    public final static String dp_params = "$params" ;
    public final static String dp_ret = "$ret" ;
    public final static String dp_error = "$error" ;
    public final static String dp_target = "$target" ; 
    public final static String dp_conn = "$conn" ; 
    
    public enum act {
        list,add,edit,delete,upload,row,rows,xml,csv,json,html
    }
    
   
    
    public static BiFunction<Object, Integer, Integer> _int = (o, dv) -> {
        if (o instanceof Number) {
            return ((Number) o).intValue();
        } else if (o instanceof String) {
            String text = ((String) o).trim();
            return Integer.parseInt(text);
        }
        return dv;
    };

    public static BiFunction<Object, Long, Long> _long = (o, dv) -> {
        if (o instanceof Number) {
            return ((Number) o).longValue();
        } else if (o instanceof String) {
            String text = ((String) o).trim();
            return Long.parseLong(text);
        }
        return dv;
    };

    public static BiFunction<Object, Double, Double> _double = (o, dv) -> {
        if (o instanceof Number) {
            return ((Number) o).doubleValue();
        } else if (o instanceof String) {
            String text = ((String) o).trim();
            return Double.parseDouble(text);
        }
        return dv;
    };

    public static BiFunction<Object, String, String> _string = (o, dv) -> o != null ? o.toString() : dv;
    public static BiFunction<Object, Date, Date> _date = (o, dv) -> (o instanceof Date) ? (Date) o : dv;

    public static Function<String, String> str_to_js = new text_quote();
    public static Function<Number, String> jo_out_num = (o) -> String.valueOf(o);
    public static Function<Date, String> json_date = (o) -> {
        return new SimpleDateFormat("'$date@'yyyyMMddHHmmss").format(o);
    };

    public static ICCPrint<Map<String, Object>> json_map = new ccprint_map();
    public static ICCPrint<Collection<Object>> json_list = new ccprint_list();

    public static String json(Object value) {
        if (value == null || value.equals("null")) {
            return "null";
        } else if (value instanceof Number) {
            return String.valueOf(value);
        } else if (value instanceof Boolean) {
            return value.toString();
        } else if (value instanceof Date) {
            return json_date.apply((Date) value);
        } else if (value instanceof Map) {
            return new CCMap((Map) value).toString();
        } else if (value instanceof Collection) {
            return new CCList((Collection) value).toString();
        } else if (value.getClass().isArray()) {
            return new CCList(value).toString();
        } else {
            return str_to_js.apply(value.toString());
        }
    }

    @SuppressWarnings("unchecked")
    public static String json(Object value, String base, String indent) {
        if (value == null || value.equals("null")) {
            return "null";
        } else if (value instanceof Number) {
            return String.valueOf(value);
        } else if (value instanceof Boolean) {
            return value.toString();
        } else if (value instanceof Date) {
            return json_date.apply((Date) value);
        } else if (value instanceof Map) {
            return json_map.print((Map) value, base, indent);
        } else if (value instanceof Collection) {
            return json_list.print((Collection<Object>) value, base, indent);
        } else if (value.getClass().isArray()) {
            return json_list.print(new CCList(value), base, indent);
        } else {
            return str_to_js.apply(value.toString());
        }
    }

    @SuppressWarnings("unchecked")
    public static void indent(StringBuilder sb, String indent, String value) {
        if (indent != null) {
            sb.append(indent).append(value);
        } else {
            sb.append(value);
        }
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T as(Class<T> c, Map<String, Object> cm, String k) {
        return (T) cm.get(k);
    }

}
