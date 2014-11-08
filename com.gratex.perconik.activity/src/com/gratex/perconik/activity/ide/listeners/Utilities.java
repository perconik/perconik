package com.gratex.perconik.activity.ide.listeners;

import javax.annotation.Nullable;

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
   * Returns the reference editor or {@code null} if the editor was not instantiated.
   */
  static IEditorPart dereferenceEditor(final IEditorReference reference) {
    return reference.getEditor(false);
  }

  /**
   * Helper method to avoid compiler {@code null} warnings.
   */
  static boolean isNull(@Nullable final Object object) {
    return object == null;
  }
}
