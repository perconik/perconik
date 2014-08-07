package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;

final class Utilities {
  private Utilities() {
    throw new AssertionError();
  }

  /**
   * Alias for {@code System.currentTimeMillis()}.
   */
  static final long currentTime() {
    return System.currentTimeMillis();
  }

  /**
   * Returns the editor referenced by this object
   * or {@code null} if the editor was not instantiated.
   */
  static final IEditorPart dereferenceEditor(final IEditorReference reference) {
    return reference.getEditor(false);
  }
}
