package org.cc2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Pattern;

public class CCList extends ArrayList<Object> implements ICCList {

    protected boolean isIndent = true;

    public CCList() {
        super();
    }

    @SuppressWarnings("unchecked")
    public CCList(Collection c) {
        super(c);
    }

    public CCList(Object array) {
        super();
        if (array != null && array.getClass().isArray()) {
            int length = Array.getLength(array);
            for (int i = 0; i < length; i += 1) {
                add(Array.get(array, i));
            }
        } else {
            throw new RuntimeException("CCList initial value should be a string or collection or array.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        this.forEach(o -> sb.append(CC.json(o)).append(','));
        if (size() > 0) {
            sb.setLength(sb.length() - 1);
        }
        sb.append(']');
        return sb.toString();
    }

    public String toString(String indent) {
        return CC.json_list.print(this, null, indent);
    }

    public static void main(String[] args) {
        CCList cl = new CCList(new Object[]{1, true, 3, "4", 5, 6, 7, 8, 9, 10, new Date()});
        System.out.println(cl.toString("  "));
    }

}
