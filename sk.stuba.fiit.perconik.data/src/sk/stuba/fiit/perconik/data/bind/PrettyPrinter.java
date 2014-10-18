package sk.stuba.fiit.perconik.data.bind;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

public final class PrettyPrinter extends DefaultPrettyPrinter {
  private static final long serialVersionUID = 0L;

  public PrettyPrinter() {}

  @Override
  public final PrettyPrinter createInstance() {
    return new PrettyPrinter();
  }

  @Override
  public final void writeObjectFieldValueSeparator(final JsonGenerator generator) throws IOException, JsonGenerationException {
    generator.writeRaw(": ");
  }
}
