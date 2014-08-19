

package org.cc2.el;

import java.util.Optional;

/**
 * @author William
 * @param <E>
 */

@FunctionalInterface
public interface ICCSafe<E> {
    public Optional<E> as(Object o , E dv );
}
