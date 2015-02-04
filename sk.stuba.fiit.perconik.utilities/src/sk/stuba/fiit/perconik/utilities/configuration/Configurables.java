package sk.stuba.fiit.perconik.utilities.configuration;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Configurables {
  private Configurables() {}

  public static Options compound(final Options primary, final Options secondary) {
    return new CompoundOptions(primary, secondary);
  }

  public static Options compound(final Iterable<Options> options) {
    return new CompoundOptions(options);
  }
}
