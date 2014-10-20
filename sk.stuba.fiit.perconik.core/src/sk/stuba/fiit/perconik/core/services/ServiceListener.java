package sk.stuba.fiit.perconik.core.services;

/**
 * A mirror of {@code com.google.common.util.concurrent.Service.Listener}.
 * Use when the mirrored class name clashes with core {@code Listener}.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class ServiceListener extends Service.Listener {
  /**
   * Constructor for use by subclasses.
   */
  protected ServiceListener() {}
}
