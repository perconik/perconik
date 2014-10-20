package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;

final class Utilities {
  private Utilities() {}

  /**
   * Alias for {@code System.currentTimeMillis()}.
   */
  static long currentTime() {
    return System.currentTimeMillis();
  }

  /**
   * Returns the editor referenced by this object
   * or {@code null} if the editor was not instantiated.
   */
  static IEditorPart dereferenceEditor(final IEditorReference reference) {
    return reference.getEditor(false);
  }
}
