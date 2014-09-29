package org.cc2.eval;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import org.cc2.CC;



/**
 * @author William
 */

public class CCEVItem extends CCEval implements ICCEvalValue<String> {

    @Override
    public Object process(Map root, String src) {
        String item = sys_params(root,src);
   
        Matcher m_cmd = p_cmd.matcher(item);
        if (m_cmd.find()) {
            Object obj = eval_cmd(root, m_cmd.group(2));
            return (obj != null) ? obj : item;
        } else {
            StringBuffer sb = new StringBuffer();
            Matcher m = p_tag.matcher(item);
            while (m.find()) {
                Object obj = CCPath.path(root,"$" + m.group(1));
                String re_text = (obj == null) ? "" : obj.toString();
                re_text = m.quoteReplacement(re_text); //
                m.appendReplacement(sb, re_text);
            }
            m.appendTail(sb);
            return sb.toString();
        }
    }

    /*
        取代系統使用參數
    */
    protected String sys_params(Map root, String src){
        String ret = src ; 
        Map params = CC.as(Map.class, root,"$params");
        if (params != null) {
            ret = ret.replace("$act",  CC.as(String.class, root,"$act"));
        }
       return  ret.replace("$fid", CC.as(String.class, root, "$fid"));
    }
    
    
    public Object eval_cmd(Map root, String line) {
       // System.out.println("===== line : " + line );
        String[] cmds = parse_csv(line);
        String cmd = cmds[0];
        switch (cmd) {
            case "load":
                return ev_load.process(root, cmds);
           // case "dao":
           //     return ev_dao.process(root, cmds);
            default:
                Object obj = CCPath.path(root,"$"+cmd);
                return (obj != null) ? eval_item(root,obj) : null;
        }
    }

    public String[] parse_csv(String csvLine) {
        List<String> ret = new ArrayList();
        Matcher m = p_csv.matcher(csvLine);
        while (m.find()) {
            String item = m.group(1);
            if (item != null) {
                ret.add(item);
            } else {
                ret.add(m.group(2));
            }
        }
        if (ret.size() > 0) {
            return ret.toArray(new String[ret.size()]);
        } else {
            return new String[0];
        }
    }

}
