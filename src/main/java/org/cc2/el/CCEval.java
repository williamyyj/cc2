/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2.el;

import java.util.regex.Pattern;


/**
 * @author William
 */
public class CCEval {
    
    protected static Pattern p_tag = Pattern.compile("\\$\\{([^\\}]+)\\}");   // ${}
    protected static Pattern p_cmd = Pattern.compile("^(\\$\\{)([^\\}]+)(\\})$");
    protected static Pattern p_csv = Pattern.compile("\"([^\"]*)\"|(?<=,|^)([^,]*)(?:,|$)");
    protected final static String ret_data = "$ret";
    public final static String cmd_eval = "$eval";
    public static ICCEvalValue ev_obj = new CCEVObject();
    public static ICCEvalValue ev_list = new CCEVList();
    public static ICCEvalValue ev_item = new CCEVItem();
    public static ICCEvalValue ev_dao = new CCEVDao();   // ev_dao 和 en_dao 差異
    public static ICCEvalValue ev_load = new CCEVLoad();
    public static ICCEvalNode en_dao = new CCENDao();
    public static ICCEvalNode en_meta = new CCENMeta();
    
    
    public static Object ccpath(ICCMap root, String ccquery){
        return eval_item(root, root.ccpath(ccquery));
    }
    
    public static Object eval_item(ICCMap root, Object o) {
        if (o instanceof ICCMap) {
            return ev_obj.process(root, (ICCMap) o);
        } else if (o instanceof ICCList) {
            return ev_list.process(root, (ICCList) o);
        } else if (o instanceof String) {
            return ev_item.process(root, (String) o);
        }
        return o;
    }
    
}
