package sk.stuba.fiit.perconik.preferences;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Splitter;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstractPreferences {
  final Scope scope;

  final IEclipsePreferences data;

  public AbstractPreferences(final Scope scope, final String qualifier) {
    this.scope = checkNotNull(scope);
    this.data = checkNotNull(scope.preferences(qualifier));
  }

  public enum Scope {
    DEFAULT {
      @Override
      public IScopeContext context() {
        return DefaultScope.INSTANCE;
      }
    },

    CONFIGURATION {
      @Override
      public IScopeContext context() {
        return ConfigurationScope.INSTANCE;
      }
    },

    INSTANCE {
      @Override
      public IScopeContext context() {
        return InstanceScope.INSTANCE;
      }
    };

    public abstract IScopeContext context();

    IEclipsePreferences preferences(final String qualifier) {
      return context().getNode(qualifier);
    }
  }

  public static abstract class Initializer extends AbstractPreferenceInitializer {
    protected Initializer() {}
  }

  public static abstract class Keys {
    public static final String separator = ".";

    protected Keys() {
      throw new AssertionError();
    }

    public static final String join(final String ... parts) {
      return Joiner.on(separator).join(parts);
    }

    public static final List<String> split(final String key) {
      return Splitter.on(separator).splitToList(key);
    }
  }

  public final Scope scope() {
    return this.scope;
  }

  public final IEclipsePreferences node() {
    return this.data;
  }

  public final void synchronize() {
    try {
      this.data.sync();
    } catch (Exception e) {
      throw new RuntimeException("Unable to synchronize preferences", e);
    }
  }

  public final void clear() {
    try {
      this.data.clear();
    } catch (Exception e) {
      throw new RuntimeException("Unable to clear preferences", e);
    }
  }

  public final void flush() {
    try {
      this.data.flush();
    } catch (Exception e) {
      throw new RuntimeException("Unable to flush preferences", e);
    }
  }

  @Override
  public String toString() {
    return this.toStringHelper().toString();
  }

  protected ToStringHelper toStringHelper() {
    ToStringHelper helper = MoreObjects.toStringHelper(this);

    helper.add("scope", this.scope);
    helper.add("data", this.data);

    return helper;
  }
}
