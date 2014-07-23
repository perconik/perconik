package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.jgit.events.IndexChangedEvent;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.GitIndexListener;

/**
 * An abstract adapter class for a {@code GitIndexListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code GitIndexListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see GitIndexListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class GitIndexAdapter extends Adapter implements GitIndexListener {
  /**
   * Constructor for use by subclasses.
   */
  protected GitIndexAdapter() {}

  public void onIndexChanged(IndexChangedEvent event) {}
}
