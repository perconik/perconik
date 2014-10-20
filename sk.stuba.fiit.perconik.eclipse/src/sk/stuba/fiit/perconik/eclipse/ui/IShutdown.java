package sk.stuba.fiit.perconik.eclipse.ui;

import com.google.common.annotations.Beta;

/**
 * Plug-ins that register a shutdown extension will be activated before
 * the {@code Workbench} shuts down and have an opportunity to run code
 * that can not be implemented using the normal contribution mechanisms.
 *
 * <p><b>Warning:</b> not implemented yet.
 *
 * @see org.eclipse.ui.IStartup
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Beta
public interface IShutdown {
  /**
   * Will be called in a separate thread before the workbench shuts down.
   */
  public void earlyShutdown();

  // TODO hook on IWorkbenchListener to implement callback mechanism
}
