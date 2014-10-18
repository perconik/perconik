package sk.stuba.fiit.perconik.data.bind;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;

public final class NamingStrategy extends PropertyNamingStrategyBase {
  private static final long serialVersionUID = 0L;

  private static final LowerCaseWithUnderscoresStrategy strategy = new LowerCaseWithUnderscoresStrategy();

  public NamingStrategy() {}

  @Override
  public final String translate(final String input) {
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
