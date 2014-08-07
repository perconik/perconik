package com.gratex.perconik.uaca;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

import javax.annotation.Nullable;
import javax.ws.rs.client.WebTarget;

import org.osgi.framework.Version;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

import com.gratex.perconik.uaca.preferences.UacaPreferences;
import com.gratex.perconik.uaca.preferences.UacaPreferences.Keys;
import com.gratex.perconik.uaca.ui.UacaMessageDialogs;

import static java.lang.String.format;

import static com.google.common.base.Strings.isNullOrEmpty;

final class UacaReporter {
  private UacaReporter() {
    throw new AssertionError();
  }

  private static final class ObjectHelper {
    private static final ObjectMapper mapper;

    private static final ObjectWriter writer;

    private static final JavaType type;

    static {
      mapper = new ObjectMapper();

      mapper.registerModule(new Module());
      mapper.registerModule(new GuavaModule());

      mapper.setPropertyNamingStrategy(new Naming());

      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      mapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);

      mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

      writer = mapper.writer(new Printer());

      type = TypeFactory.defaultInstance().constructMapType(LinkedHashMap.class, String.class, Object.class);
    }

    private ObjectHelper() {
      throw new AssertionError();
    }

    private static final class Module extends SimpleModule {
      private static final long serialVersionUID = 0L;

      public Module() {
        super(PackageVersion.VERSION);

        this.addSerializer(Path.class, ToStringSerializer.instance);
        this.addSerializer(Version.class, ToStringSerializer.instance);

        this.addDeserializer(Path.class, new PathDeserializer());
        this.addDeserializer(Version.class, new VersionDeserializer());
      }

      @Override
      public final boolean equals(Object o) {
        return this == o;
      }

      @Override
      public final int hashCode() {
        return Module.class.hashCode();
      }

      private static final class PathDeserializer extends StdScalarDeserializer<Path> {
        private static final long serialVersionUID = 0L;

        public PathDeserializer() {
          super(Path.class);
        }

        @Override
        public final Path deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JsonProcessingException {
          return Paths.get(parser.getValueAsString());
        }
      }

      private static final class VersionDeserializer extends StdScalarDeserializer<Version> {
        private static final long serialVersionUID = 0L;

        public VersionDeserializer() {
          super(Version.class);
        }

        @Override
        public final Version deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JsonProcessingException {
          return Version.parseVersion(parser.getValueAsString());
        }
      }
    }

    private static final class Naming extends PropertyNamingStrategyBase {
      private static final long serialVersionUID = 0L;

      private static final LowerCaseWithUnderscoresStrategy strategy = new LowerCaseWithUnderscoresStrategy();

      public Naming() {}

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

    private static final class Printer extends DefaultPrettyPrinter {
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

    public static final String toString(@Nullable final Object object) throws Exception {
      return writer.writeValueAsString(mapper.convertValue(object, type));
    }
  }

  static final void logRequest(final WebTarget target, @Nullable final Object request) {
    if (!UacaPreferences.getShared().isEventLoggerEnabled()) {
      return;
    }

    try {
      UacaConsole.getInstance().notice(format("%s%n%s", target.getUri(), ObjectHelper.toString(request)));
    } catch (Exception e) {
      UacaConsole.getInstance().error(e, "UacaProxy: Unable to format object");
    }
  }

  static final void logError(final String message, @Nullable final Exception failure) {
    if (!UacaPreferences.getShared().isErrorLoggerEnabled()) {
      return;
    }

    UacaConsole.getInstance().error(failure, message);
  }

  static final void displayError(final String message, @Nullable final Exception failure) {
    if (!UacaPreferences.getShared().getPreferenceStore().getBoolean(Keys.displayErrors)) {
      return;
    }

    UacaMessageDialogs.openError(Keys.displayErrors, isNullOrEmpty(message) ? failure.getMessage() : message);
  }
}
