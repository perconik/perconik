package sk.stuba.fiit.perconik.data.bind;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;

public abstract class NamingStrategy extends PropertyNamingStrategyBase {
  private static final long serialVersionUID = 0L;

  NamingStrategy() {}

  public static final class Default extends NamingStrategy {
    private static final long serialVersionUID = 0L;

    public Default() {}

    @Override
    public String translate(final String input) {
      return input;
    }
  }

  public static final class LowerUnderscore extends NamingStrategy {
    private static final long serialVersionUID = 0L;

    private static final LowerCaseWithUnderscoresStrategy strategy = new LowerCaseWithUnderscoresStrategy();

    public LowerUnderscore() {}

    @Override
    public String translate(final String input) {
      if (input == null) {
        return null;
      }

      String result = input;

      if (input.charAt(0) == '_') {
        result = "_" + result;
      }

      return strategy.translate(result);
    }
  }
}
