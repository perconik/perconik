package sk.stuba.fiit.perconik.eclipse.core.commands;

import javax.annotation.Nullable;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.commands.ICommandService;

import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Static utility methods pertaining to Eclipse commands.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Commands {
  private Commands() {
    throw new AssertionError();
  }

  /**
   * Gets the command service.
   * @return the command service or {@code null} if
   *         the workbench has not been created yet
   */
  public static final ICommandService getCommandService() {
    return getCommandService(Workbenches.getWorkbench());
  }

  /**
   * Gets the command service.
   * @param workbench the workbench, may be {@code null}
   * @return the command service or {@code null} if the workbench
   *         is {@code null} or if the workbench has no command service
   */
  public static final ICommandService getCommandService(@Nullable final IWorkbench workbench) {
    if (workbench == null) {
      return null;
    }

    return (ICommandService) workbench.getService(ICommandService.class);
  }

  /**
   * Waits for the command service.
   * This method blocks until there is an available command service.
   * @see #getCommandService()
   */
  public static final ICommandService waitForCommandService() {
    return waitForCommandService(Workbenches.waitForWorkbench());
  }

  /**
   * Waits for the command service.
   * This method blocks until there is an available command service.
   * @param workbench the workbench, can not be {@code null}
   * @see #getCommandService(IWorkbench)
   */
  public static final ICommandService waitForCommandService(final IWorkbench workbench) {
    checkNotNull(workbench);

    ICommandService service;

    while ((service = getCommandService(workbench)) == null) {}

    return service;
  }
}
