package com.gratex.perconik.uaca;

import java.util.LinkedHashMap;

import javax.annotation.Nullable;
import javax.ws.rs.client.WebTarget;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

import com.gratex.perconik.uaca.preferences.UacaPreferences;
import com.gratex.perconik.uaca.preferences.UacaPreferences.Keys;
import com.gratex.perconik.uaca.ui.UacaMessageDialogs;

final class UacaReporter {
  private UacaReporter() {
    throw new AssertionError();
  }

  private static final class ObjectHelper
  {
    private static final ObjectMapper mapper;

    private static final JavaType type;

    static {
      mapper = new ObjectMapper();

      mapper.registerModule(new GuavaModule());

      mapper.setPropertyNamingStrategy(new Naming());

      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      mapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);

      mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

      type = TypeFactory.defaultInstance().constructMapType(LinkedHashMap.class, String.class, Object.class);
    }

    private ObjectHelper() {
      throw new AssertionError();
    }

    private static final class Naming extends PropertyNamingStrategyBase {
      private static final long serialVersionUID = 0L;

      private static final LowerCaseWithUnderscoresStrategy strategy = new LowerCaseWithUnderscoresStrategy();

      public Naming() {}

      @Override
      public final String translate(String input) {
        if (input == null) {
          return null;
        }

        if (input.charAt(0) == '_') {
          input = "_" + input;
        }

        return strategy.translate(input);
      }
    }

    public static final String toString(@Nullable final Object object) throws Exception {
      return mapper.writeValueAsString(mapper.convertValue(object, type));
    }
  }

  static final void logRequest(final WebTarget target, @Nullable final Object request) {
    if (!UacaPreferences.getShared().isEventLoggerEnabled()) {
      return;
    }

    try {
      UacaConsole.getInstance().notice(String.format("%s%n%s", target.getUri(), ObjectHelper.toString(request)));
    } catch (Exception e) {
      UacaConsole.getInstance().error(e, "Event logger failed");
    }
  }

  static final void logError(final String message, @Nullable final Exception failure) {
    if (!UacaPreferences.getShared().isErrorLoggerEnabled()) {
      return;
    }

    UacaConsole.getInstance().error(failure, message);
  }

  static final void displayError(final String message) {
    if (UacaPreferences.getShared().asPreferenceStore().getBoolean(Keys.displayErrors)) {
      UacaMessageDialogs.openError(Keys.displayErrors, message);
    }
  }
}
