/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2.eval;

import java.util.List;
import java.util.Map;
import org.cc2.CCList;
import org.cc2.ICCList;
import static org.cc2.eval.CCEval.eval_item;



/**
 * @author William
 */

public class CCEVList implements ICCEvalValue<List> {

    @Override
    public Object process(Map root, List src) {
        return  new CCList(src);
    }

}
