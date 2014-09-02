package org.cc2.db;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.cc2.CC;
import org.cc2.CCList;
import org.cc2.CCMap;
import org.cc2.ICCDP;
import org.cc2.ICCMap;
import org.cc2.util.CCCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author William 整合DBJO ${base}/dp/xxxx.dao 統一使用
 *
 * ${ rem , xxxxxxxxxxxxxxxxxxxxxx}
 *
 * ${ op:fld_name:dt} 動態欄位 fld_name op map(fld_name) ${range:fld_name:dt}
 * fld_name beteen map(fld_name_1) and map(fld_name_2) ${like: fld_name} only
 * 字串欄位 會利用 IDB 的 base() 取到 base 位置
 *
 *
 */
public class QueryModel {

    private final static Logger log = LoggerFactory.getLogger(QueryModel.class);
    private final static Pattern p = Pattern.compile("\\$\\{([^\\}]+)\\}");

    public static ICCMap parser_command(ICCDP dp, String fid, String aid, ICCMap row) throws Exception {
        File f = new File(dp.base(), "module/" + fid + ".dao");
        ICCMap dao = CCCache.load_map(f, "UTF-8");      
        String cmd = QueryItem.get_command(dao, aid);
        System.out.println(cmd);
        ICCMap model = new CCMap();
        model.put(CC.dp_params, new CCList());     
        Matcher m = p.matcher(cmd);
        StringBuffer sql = new StringBuffer();
        while (m.find()) {
            String item = m.group(1);
            m.appendReplacement(sql, ""); // 直接清空
            if (!item.startsWith("rem")) {
                if (dao.containsKey(item)) {
                    String cmd_item = QueryItem.get_command(dao, item); //這部份不支援 recursive
                    sql.append(cmd_item);
                } else {
                    QueryItem.process_item(dp, sql, model, row, item);
                }
            }
        }
        m.appendTail(sql);
        model.put(CC.dp_sql, sql);
        return model;
    }

    public static ICCMap parser_command(ICCDP dp, String cmd, ICCMap row) throws Exception {
        ICCMap model = new CCMap();
        model.put(CC.dp_params, new CCList());     
        System.out.println(cmd);
        Matcher m = p.matcher(cmd);
        StringBuffer sql = new StringBuffer();
        while (m.find()) {
            String item = m.group(1);
            m.appendReplacement(sql, ""); // 直接清空
            QueryItem.process_item(dp, sql, model, row, item);
        }
        m.appendTail(sql);
         model.put(CC.dp_sql, sql);
        return model;
    }

}
