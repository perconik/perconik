package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;

import static java.util.Arrays.asList;

import static com.google.common.collect.Iterables.transform;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.sequence;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toLowerCaseFunction;

final class Utilities {
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
   * Returns the editor referenced by this object
   * or {@code null} if the editor was not instantiated.
   */
  static IEditorPart dereferenceEditor(final IEditorReference reference) {
    return reference.getEditor(false);
  }
}
