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
public class mysql_init implements ICCInit {

    public void __init__(Map self) throws Exception {
        self.put(Types.DATE,new CCDateType());
        self.put(Types.TIME,new CCDateType());
        self.put(Types.TIMESTAMP,new CCDateType());
    }
    
}
