package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;

import static java.util.Arrays.asList;

import static com.google.common.collect.Iterables.transform;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.sequence;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toLowerCaseFunction;

final class Utilities {
  private static final boolean restore = false;

  private Utilities() {}

  static String actionName(final Object ... components) {
    return key(transform(asList(components), toLowerCaseFunction()));
  }

  static String actionPath(final Object ... components) {
    StringBuilder builder = new StringBuilder(16 * components.length);

    for (Object component: components) {
      for (String item: sequence(component.toString())) {
        builder.append(item.toLowerCase()).append("/");
      }
    }

    return builder.substring(0, builder.length() - 1);
  }

  /**
   * Alias for {@code System.currentTimeMillis()}.
   */
  static long currentTime() {
    return System.currentTimeMillis();
  }

  /**
   * Returns the referenced part or {@code null} if the part was not instantiated.
   */
  static IWorkbenchPart dereferencePart(final IWorkbenchPartReference reference) {
    return reference.getPart(restore);
  }

  /**
   * Returns the referenced view or {@code null} if the view was not instantiated.
   */
  static IViewPart dereferenceView(final IViewReference reference) {
    return reference.getView(restore);
  }


  /**
   * Returns the referenced editor or {@code null} if the editor was not instantiated.
   */
  static IEditorPart dereferenceEditor(final IEditorReference reference) {
    return reference.getEditor(restore);
  }
}
