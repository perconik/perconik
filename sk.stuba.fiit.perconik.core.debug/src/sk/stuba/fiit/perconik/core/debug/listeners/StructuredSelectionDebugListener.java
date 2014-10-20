package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.StructuredSelectionListener;

public final class StructuredSelectionDebugListener extends AbstractDebugListener implements StructuredSelectionListener {
  public StructuredSelectionDebugListener() {}

  public StructuredSelectionDebugListener(final DebugConsole console) {
    super(console);
  }

  public void selectionChanged(final IWorkbenchPart part, final IStructuredSelection selection) {
    this.printHeader("Structured selection changed");
    this.printStructuredSelection(selection);
  }

  private void printStructuredSelection(final IStructuredSelection selection) {
    this.put(Debug.dumpStructuredSelection(selection));
  }
}
