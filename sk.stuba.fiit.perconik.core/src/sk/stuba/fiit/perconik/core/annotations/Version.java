package sk.stuba.fiit.perconik.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import sk.stuba.fiit.perconik.core.Registrable;

/**
 * Marks a {@link Registrable} with a version identifier.
 *
 * <p>See {@link org.osgi.framework.Version#toString() toString()} method
 * of class {@link org.osgi.framework.Version Version} for more details about
 * the version format.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Version {
  /**
   * Version identifier as string.
   */
  String value();
}
