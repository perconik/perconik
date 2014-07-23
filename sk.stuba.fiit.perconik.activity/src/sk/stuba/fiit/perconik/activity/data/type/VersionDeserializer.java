package sk.stuba.fiit.perconik.activity.data.type;

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
  public Version deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
    return Version.parseVersion(parser.getValueAsString());
  }
}
