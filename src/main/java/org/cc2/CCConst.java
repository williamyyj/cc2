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
public class CCConst {
    public final String act_fid = "$fid";
    public final String act_aid = "$aid";
    
    public static String root = "C:/Users/William/Dropbox/resources";
    
    public static String base(String prjId){
        return root + "/" + prjId ; 
    }
}
