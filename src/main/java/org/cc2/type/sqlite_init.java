/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2.type;


import java.sql.Types;
import java.util.Map;
import org.cc2.ICCInit;


/**
 *
 * @author william
 *  這部份改成annotation
 *
 */
public class sqlite_init implements ICCInit {

    @Override
    public void __init__(Map self) throws Exception {
        self.put("datetime",new sqlite_date());
        self.put(ICCType.dt_date, new sqlite_date());
    }
    
}