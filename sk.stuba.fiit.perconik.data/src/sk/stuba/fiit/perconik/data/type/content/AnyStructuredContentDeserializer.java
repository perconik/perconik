package sk.stuba.fiit.perconik.data.type.content;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;

import sk.stuba.fiit.perconik.data.AnyStructuredData;

public final class AnyStructuredContentDeserializer extends UntypedObjectDeserializer {
  private static final long serialVersionUID = 0L;

  public AnyStructuredContentDeserializer() {}

  @Override
  protected Object mapObject(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
    return AnyStructuredData.of(Map.class.cast(super.mapObject(parser, context)));
  }
}
