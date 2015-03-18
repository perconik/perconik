package sk.stuba.fiit.perconik.data.type.content;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.annotation.Nullable;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.std.NonTypedScalarSerializerBase;

import sk.stuba.fiit.perconik.data.bind.Mapper;

import static com.google.common.base.Strings.isNullOrEmpty;

public class AnyContentKeySerializer extends NonTypedScalarSerializerBase<String> {
  public AnyContentKeySerializer() {
    super(String.class);
  }

  @Override
  public void acceptJsonFormatVisitor(final JsonFormatVisitorWrapper visitor, final JavaType type) throws JsonMappingException {
    if (visitor != null) {
      visitor.expectStringFormat(type);
    }
  }

  @Override
  public void serialize(final String value, final JsonGenerator generator, final SerializerProvider provider) throws IOException, JsonGenerationException {
    SerializationConfig configuration = Mapper.getShared().getSerializationConfig();
    PropertyNamingStrategyBase strategy = (PropertyNamingStrategyBase) configuration.getPropertyNamingStrategy();

    generator.writeFieldName(strategy.translate(value));
  }

  @Override
  public JsonNode getSchema(final SerializerProvider provider, final Type typeHint) {
    return createSchemaNode("string", true);
  }

  @Override
  public boolean isEmpty(@Nullable final String value) {
    return isNullOrEmpty(value);
  }
}
