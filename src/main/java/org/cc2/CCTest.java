/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cc2;

/**
 *
 * @author William
 */
public class CCTest {
    public static void main(String[] args){
        String prjId = "/prj/baphiq";
        CCMap m = CCCache.load_map(CCConst.base(prjId), "cfg.json");
        System.out.println(m);
    }
}
