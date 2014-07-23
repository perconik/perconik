package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.core.resources.IResourceChangeEvent;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.AbstractResourceListener;
import sk.stuba.fiit.perconik.core.listeners.ResourceListener;

/**
 * An abstract adapter class for a {@code ResourceListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code ResourceListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see ResourceListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class ResourceAdapter extends AbstractResourceListener {
  /**
   * Constructor for use by subclasses.
   */
  protected ResourceAdapter() {}

  public void resourceChanged(IResourceChangeEvent event) {}
}
