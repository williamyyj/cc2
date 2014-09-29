package org.cc2.eval;


import java.util.Map;

/**
 * @author William
 *  replace old node 
 */
public interface ICCEvalNode {

    public void process(Map root, Map src, Map target) throws Exception;    
    
}
