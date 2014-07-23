package sk.stuba.fiit.perconik.core.listeners;

import java.util.EnumSet;
import java.util.Set;

import sk.stuba.fiit.perconik.core.AbstractFilteringListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType;

/**
 * An abstract implementation of {@code ResourceListener}.
 * 
 * @see ResourceListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractResourceListener extends AbstractFilteringListener<ResourceEventType> implements ResourceListener {
  protected AbstractResourceListener() {
    super(EnumSet.allOf(ResourceEventType.class));
  }

  protected AbstractResourceListener(final Set<ResourceEventType> types) {
    super(types);
  }
}
