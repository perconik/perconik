package sk.stuba.fiit.perconik.core.java;

import java.util.LinkedList;

import javax.annotation.Nullable;

import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;

import static com.google.common.collect.Lists.newLinkedList;

public final class JavaElements {
  private JavaElements() {}

  public static IJavaElement parent(@Nullable final IJavaElement element) {
    return element != null ? element.getParent() : null;
  }

  public static LinkedList<IJavaElement> ancestors(@Nullable IJavaElement element) {
    final LinkedList<IJavaElement> ancestors = newLinkedList();

    while (element != null) {
      ancestors.add(element = element.getParent());
    }

    return ancestors;
  }

  public static LinkedList<IJavaElement> downToRoot(@Nullable IJavaElement element) {
    LinkedList<IJavaElement> branch = newLinkedList();

    if (element != null) {
      do {
        branch.add(element);
      } while ((element = element.getParent()) != null);
    }

    return branch;
  }

  public static IResource resource(@Nullable final IJavaElement element) {
    return element != null ? element.getResource() : null;
  }

  public static IResource correspondingResource(@Nullable final IJavaElement element) {
    try {
      return element != null ? element.getCorrespondingResource() : null;
    } catch (JavaModelException e) {
      return JavaExceptions.handle(e);
    }
  }

  public static IResource underlyingResource(@Nullable final IJavaElement element) {
    try {
      return element != null ? element.getUnderlyingResource() : null;
    } catch (JavaModelException e) {
      return JavaExceptions.handle(e);
    }
  }
}
