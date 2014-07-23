package sk.stuba.fiit.perconik.core;

/**
 * An abstract implementation of {@link Registrable} with empty hook methods.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractRegistrable implements Registrable {
  /**
   * Constructor for use by subclasses.
   */
  protected AbstractRegistrable() {}

  public void preRegister() {}

  public void postRegister() {}

  public void preUnregister() {}

  public void postUnregister() {}
}
