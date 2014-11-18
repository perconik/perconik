package sk.stuba.fiit.perconik.eclipse.jdt.core;

import java.util.LinkedList;

import com.google.common.base.Joiner;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragmentRoot;

import static com.google.common.collect.Lists.newLinkedList;

public final class ClassFiles {
  private ClassFiles() {}

  public static IPath path(final IClassFile file) {
    LinkedList<String> segments = newLinkedList();

    IJavaElement element = file;

    do {
      JavaElementType type = JavaElementType.valueOf(element);

      if (type == JavaElementType.PACKAGE_FRAGMENT_ROOT) {
        IPackageFragmentRoot root = (IPackageFragmentRoot) element;

        String segment = !root.isExternal() ? element.getPath().toString() : root.getElementName();

        if (segment.startsWith("/")) {
          segment = segment.substring(1);
        }

        segments.addFirst(segment);

        break;
      }

      String segment = element.getElementName();

      if (type == JavaElementType.PACKAGE_FRAGMENT) {
        segment = segment.replace('.', '/');
      }

      segments.addFirst(segment);
    } while ((element = element.getParent()) != null);

    return new Path(Joiner.on('/').skipNulls().join(segments));
  }
}
