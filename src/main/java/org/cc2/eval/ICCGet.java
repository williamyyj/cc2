

package org.cc2.eval;

import java.util.Optional;

/**
 * @author William
 * @param <M>
 */

@FunctionalInterface
public interface ICCGet<M> {
    public Object  get(M m , String k );
}
