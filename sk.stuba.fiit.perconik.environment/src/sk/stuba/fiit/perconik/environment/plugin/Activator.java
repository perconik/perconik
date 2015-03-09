package sk.stuba.fiit.perconik.environment.plugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

import org.osgi.framework.BundleContext;

import sk.stuba.fiit.perconik.environment.java.JavaVerificationException;
import sk.stuba.fiit.perconik.environment.java.JavaVerifier;

/**
 * The <code>Activator</code> class controls the plug-in life cycle.
 *
 * <p><b>Warning:</b> Users should not explicitly instantiate this class.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Activator extends Plugin {
  /**
   * The plug-in identifier.
   */
  public static final String PLUGIN_ID = "sk.stuba.fiit.perconik.environment";

  /**
   * The shared instance.
   */
  private static volatile Activator plugin;

  /**
   * The constructor.
   */
  public Activator() {}

  /**
   * Gets the shared instance.
   * @return the shared instance or {@code null}
   */
  public static Activator defaultInstance() {
    return plugin;
  }

  public static JavaVerifier getJavaVerifier() {
    return JavaVerifier.JAVA_7;
  }

  public void verifyJava() throws JavaVerificationException {
    try {
      getJavaVerifier().verify();
    } catch (RuntimeException e) {
      String message = "Unable to verify Java version";

      this.getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, message));
    }
  }

  private void verifyJavaInternal() {
    try {
      this.verifyJava();
    } catch (Exception e) {
      String message = e.getMessage();

      this.getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, message));
    }
  }

  /**
   * Starts this plug-in.
   *
   * <p><b>Warning:</b> Users must never explicitly call this method.
   */
  @Override
  public void start(final BundleContext context) throws Exception {
    super.start(context);

    plugin = this;

    this.verifyJavaInternal();
  }

  /**
   * Stops this plug-in.
   *
   * <p><b>Warning:</b> Users must never explicitly call this method.
   */
  @Override
  public void stop(final BundleContext context) throws Exception {
    plugin = null;

    super.stop(context);
  }
}
