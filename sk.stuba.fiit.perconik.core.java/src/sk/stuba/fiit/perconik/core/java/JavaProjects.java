package sk.stuba.fiit.perconik.core.java;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;

import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaExceptions;

import static com.google.common.base.Preconditions.checkState;

public final class JavaProjects {
  private JavaProjects() {}

  public static IJavaProject create(final IProject project) {
    checkState(isJavaProject(project));

    return JavaCore.create(project);
  }

  public static boolean inOutputLocation(final IProject project, final IResource resource) {
    if (isJavaProject(project)) {
      return inOutputLocation(create(project), resource);
    }

    return false;
  }

  public static boolean inOutputLocation(final IJavaProject project, final IResource resource) {
    return getOutputLocation(project).isPrefixOf(resource.getFullPath());
  }

  private static IPath getDefaultLocation(final IJavaProject project, final String key) {
    IPreferenceStore store = PreferenceConstants.getPreferenceStore();

    if (store.getBoolean(PreferenceConstants.SRCBIN_FOLDERS_IN_NEWPROJ)) {
      String name = store.getString(key);

      return project.getProject().getFullPath().append(name);
    }

    return project.getProject().getFullPath();
  }

  public static IPath getDefaultSourceLocation(final IJavaProject project) {
    return getDefaultLocation(project, PreferenceConstants.SRCBIN_SRCNAME);
  }

  public static IPath getDefaultOutputLocation(final IJavaProject project) {
    return getDefaultLocation(project, PreferenceConstants.SRCBIN_BINNAME);
  }

  public static IPath getOutputLocation(final IJavaProject project) {
    try {
      return project.getOutputLocation();
    } catch (JavaModelException e) {
      throw JavaExceptions.propagate(e);
    }
  }

  public static boolean isJavaProject(final IProject project) {
    try {
      return project.hasNature(JavaCore.NATURE_ID);
    } catch (CoreException e) {
      throw JavaExceptions.propagate(e);
    }
  }
}
