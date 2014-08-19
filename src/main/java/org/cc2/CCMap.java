package org.cc2;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class CCMap extends HashMap<String, Object> implements ICCMap {

    protected boolean isIndent = true;

    public CCMap() {
        super();
    }

    @SuppressWarnings("unchecked")
    public CCMap(Map m) {
        super();
        if (m != null) {
            m.forEach((k, v) -> put(k.toString(), v));
        }
    }

    public Optional<Map<String, Object>> model() {
        return Optional.of(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        this.forEach((k, v) -> {
            sb.append('"').append(k).append('"').append(':').append(CC.json(v)).append(',');
        });
        if (size() > 0) {
            sb.setLength(sb.length() - 1);
        }
        sb.append('}');
        return sb.toString();
    }
    
    public String toString(String indent) {
        return CC.json_map.print(this, null, indent);
    }

    public static void main(String[] args){
        CCMap m = new CCMap();
        m.put("xx", "1000");
        System.out.println(m.toString("\t"));
    }



}
