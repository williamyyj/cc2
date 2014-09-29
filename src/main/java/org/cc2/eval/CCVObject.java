package org.cc2.eval;

import java.util.Map;
import java.util.Set;
import org.cc2.CCMap;
import static org.cc2.eval.CCEval.cmd_eval;
import static org.cc2.eval.CCEval.eval_item;



/**
 * @author William
 */
public class CCVObject implements ICCEvalValue<Map> {

    @Override
    public Object process(Map root, Map src) {
        Map m = new CCMap(); 
        Map work = new CCMap(src);
        try {
            eval_node(root,work,m);
            Set<String> names = work.keySet();
            for (String n : names) {
                Object o = work.get(n);
                m.put(n, eval_item(root, o));
            }
            //eval_after(root,work,m);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return m;
    }
    
     public void eval_node(Map root, Map work, Map target) throws Exception {
        if (work != null && work.containsKey(cmd_eval)) {
            Object ext_item = work.get(cmd_eval) ;
            Object item = eval_item(root, ext_item);  //    ${load,xxxxx}   
            if (item instanceof Map) {
                target.putAll((Map)item);            
            } else if (item instanceof String) {
                String cmd = (String) item;
                switch (cmd) {
                   // case "dao": en_dao.process(root, work, target) ; break ; 
                   // case "meta": en_meta.process(root, work, target) ; break ; 
                }
            }
            work.remove(cmd_eval);
        }
    }
    

}
