package sk.stuba.fiit.perconik.core.resources;

import java.util.Set;

import sk.stuba.fiit.perconik.core.FilteringListener;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

final class Handlers {
  private Handlers() {}

  private static final class SafeHandler<T> implements Handler<T> {
    private final Handler<T> handler;

    private final Class<T> type;

    public SafeHandler(final Handler<T> handler, final Class<T> type) {
      this.handler = checkNotNull(handler);
      this.type = checkNotNull(type);
    }

    private T check(final T object) {
      return this.type.cast(checkNotNull(object));
    }

    public void register(final T object) {
      this.handler.register(check(object));
    }

    public void unregister(final T object) {
      this.handler.unregister(check(object));
    }

    @Override
    public String toString() {
      return this.handler.toString();
    }
  }

  static <E extends Enum<E> & IntegralConstant> int mask(final FilteringListener<E> listener) {
    Set<E> types = listener.getEventTypes();

    checkState(types != null && !types.isEmpty());

    return IntegralConstantSupport.constantsAsInteger(types);
  }

  static <T> Handler<T> safe(final Handler<T> handler, final Class<T> type) {
    return new SafeHandler<>(handler, type);
  }
}
