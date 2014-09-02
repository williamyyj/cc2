/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2;

import org.cc2.util.CCCache;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author William
 */
public class CCTest {

    public CCTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
        String prjId = "prj/baphiq";
        String base = CCConst.base(prjId);
        ICCMap m = CCCache.load_map(base, "cfg", "json");
        System.out.println(m.toString("\t"));
    }

    @Test
    public void print_list() {
        CCList cl = new CCList(new Object[]{1, true, 3, "4", 5, 6, 7, 8, 9, 10, new Date()});
        System.out.println(cl.toString("  "));
    }
    
}
