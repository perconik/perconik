package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.CommandExecutionListener;

/**
 * An abstract adapter class for a {@code CommandExecutionListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 *
 * <p>Extend this class to create a {@code CommandExecutionListener}
 * and override the methods for the events of interest.
 *
 * @see Adapter
 * @see CommandExecutionListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class CommandExecutionAdapter extends Adapter implements CommandExecutionListener {
  /**
   * Constructor for use by subclasses.
   */
  protected CommandExecutionAdapter() {}

  public void preExecute(final String id, final ExecutionEvent event) {}

  public void postExecuteSuccess(final String id, final Object result) {}

  public void postExecuteFailure(final String id, final ExecutionException exception) {}

  public void notDefined(final String id, final NotDefinedException exception) {}

  public void notEnabled(final String id, final NotEnabledException exception) {}

  public void notHandled(final String id, final NotHandledException exception) {}
}
