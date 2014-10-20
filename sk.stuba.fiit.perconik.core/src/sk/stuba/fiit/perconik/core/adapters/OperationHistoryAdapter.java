package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.core.commands.operations.OperationHistoryEvent;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.OperationHistoryListener;

/**
 * An abstract adapter class for a {@code OperationHistoryListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 *
 * <p>Extend this class to create a {@code OperationHistoryListener}
 * and override the methods for the events of interest.
 *
 * @see Adapter
 * @see OperationHistoryListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class OperationHistoryAdapter extends Adapter implements OperationHistoryListener {
  /**
   * Constructor for use by subclasses.
   */
  protected OperationHistoryAdapter() {}

  public void historyNotification(final OperationHistoryEvent event) {}
}
