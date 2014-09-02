package org.cc2.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.cc2.CCMap;
import org.cc2.ICCMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CCConfig extends CCMap {

    protected Logger log = LoggerFactory.getLogger(CCConfig.class);

    public CCConfig() {
        this(null);
    }

    public CCConfig(String base) {
        this(base, "cfg", "json");  // $base/cfg.json		
    }

    // $base/file_name 
    public CCConfig(String base, String file_name, String suffix) {
        super();
        base(base); // setting base
        init(file_name + "." + suffix);
    }

    private void init(String file_name) {
        File cfg_path = new File(base(), file_name);
        ICCMap m = CCCache.load_map(cfg_path, "UTF-8");
        if (m instanceof Map) {
            this.putAll((Map<? extends String, ?>) m);
        }
    }

    private String base(String dv) {
        if (!this.containsKey("$base")) {
            String base = System.getProperty("base", dv);
            base = (base != null) ? base : ".";
            try {
                base = new File(base).getCanonicalPath();
                this.put("$base", base);
            } catch (IOException ex) {
                log.error("Can't find : " + base);
            }
        }
        return this._string("$base");
    }

    public String base() {
        return base(null);
    }

    public String path(String prefix) {
        return base() + "/" + _string(prefix);
    }

    private void load(String id) {
        ICCMap m = CCCache.load_map(new File(base(), id + ".json"), "UTF-8");
        if (m != null) {
            put(id, m);
        }
    }

   
    private File file(String prefix, String id) {
        String path = path(prefix);
        return new File(path, id + ".json");
    }
 
}
