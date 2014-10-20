package sk.stuba.fiit.perconik.core.java.dom;

import java.util.List;

import javax.annotation.Nullable;

import uk.ac.open.crc.intt.IdentifierNameTokeniser;
import uk.ac.open.crc.intt.IdentifierNameTokeniserFactory;

import sk.stuba.fiit.perconik.utilities.function.ListCollector;

import static java.util.Arrays.asList;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

public abstract class Tokenizer implements ListCollector<String, String> {
  private final IdentifierNameTokeniser tokenizer;

  Tokenizer(final IdentifierNameTokeniser tokenizer) {
    this.tokenizer = checkNotNull(tokenizer);
  }

  public static Tokenizer create(final IdentifierNameTokeniser tokenizer) {
    return new Unknown(tokenizer);
  }

  public static Tokenizer create(final IdentifierNameTokeniserFactory factory) {
    return new Known(factory);
  }

  private static final class Known extends Tokenizer {
    private final String settings;

    Known(final IdentifierNameTokeniserFactory factory) {
      this(factory.toString(), factory.create());
    }

    Known(final String settings, final IdentifierNameTokeniser tokenizer) {
      super(tokenizer);

      checkArgument(!isNullOrEmpty(settings));

      this.settings = settings;
    }

    @Override
    public String toString() {
      return "tokenizer(" + this.settings + ")";
    }
  }

  private static final class Unknown extends Tokenizer {
    Unknown(final IdentifierNameTokeniser tokenizer) {
      super(tokenizer);
    }

    @Override
    public String toString() {
      return "tokenizer(?)";
    }
  }

  public List<String> apply(final String input) {
    return asList(this.tokenizer.tokenise(input));
  }

  @Override
  public final boolean equals(@Nullable final Object o) {
    if (o instanceof Tokenizer) {
      Tokenizer other = (Tokenizer) o;

      return this.tokenizer.equals(other.tokenizer);
    }

    return false;
  }

  @Override
  public final int hashCode() {
    return this.tokenizer.hashCode();
  }

  @Override
  public abstract String toString();
}
