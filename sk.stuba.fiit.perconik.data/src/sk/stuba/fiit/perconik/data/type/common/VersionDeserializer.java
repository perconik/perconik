package sk.stuba.fiit.perconik.data.type.common;

import java.io.IOException;

import org.osgi.framework.Version;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

public class VersionDeserializer extends StdScalarDeserializer<Version> {
  private static final long serialVersionUID = 0L;

  public VersionDeserializer() {
    super(Version.class);
  }

  @Override
  public Version deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JsonProcessingException {
    return Version.parseVersion(parser.getValueAsString());
  }
}
