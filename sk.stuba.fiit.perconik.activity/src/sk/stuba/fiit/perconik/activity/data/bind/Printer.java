package sk.stuba.fiit.perconik.activity.data.bind;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

public final class Printer extends DefaultPrettyPrinter {
  private static final long serialVersionUID = 0L;

  public Printer() {}

  @Override
  public final Printer createInstance() {
    return new Printer();
  }

  @Override
  public final void writeObjectFieldValueSeparator(final JsonGenerator generator) throws IOException, JsonGenerationException {
    generator.writeRaw(": ");
  }
}
