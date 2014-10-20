package sk.stuba.fiit.perconik.core.listeners;

import java.util.EnumSet;
import java.util.Set;

import sk.stuba.fiit.perconik.core.AbstractFilteringListener;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementEventType;

/**
 * An abstract implementation of {@code JavaElementListener}.
 *
 * @see JavaElementListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractJavaElementListener extends AbstractFilteringListener<JavaElementEventType> implements JavaElementListener {
  protected AbstractJavaElementListener() {
    super(EnumSet.allOf(JavaElementEventType.class));
  }

  protected AbstractJavaElementListener(final Set<JavaElementEventType> types) {
    super(types);
  }
}
