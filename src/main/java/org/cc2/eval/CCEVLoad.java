package org.cc2.eval;

import java.io.File;
import java.util.Map;
import org.cc2.ICCMap;
import org.cc2.util.CCCache;



/**
 * @author William
 */
public class CCEVLoad extends CCEval implements ICCEvalValue<String[]> {

    @Override
    public Object process(Map root, String[] src) {
       // String base = root._string("$path", "./dp");
        String base = null ; 
        String fid = src[1].trim(); // fid
        String path = src[2].trim();
        String suffix = (src.length>3) ? src[3] : "json";
        Object ret = get_native(base, fid, path, suffix);
        return eval_item(root, ret);
    }
    
   public  Object get_native(String base, String fid, String ccpath, String suffix) {
        File f = new File(base, fid + "." + suffix);
        System.out.println("===== file : " + f);
        if (f.exists()) {
            ICCMap m = CCCache.load_map(f, "UTF-8");
            return CCPath.path(m, ccpath);
        }
        throw new RuntimeException("Can't find  " + f + "@" + ccpath);
    }


}
