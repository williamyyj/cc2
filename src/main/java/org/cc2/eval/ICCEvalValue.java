package org.cc2.eval;

import java.util.Map;

/**
 * @author William
 */

public interface ICCEvalValue<E> {
    public Object process(Map root, E src) ;
}
