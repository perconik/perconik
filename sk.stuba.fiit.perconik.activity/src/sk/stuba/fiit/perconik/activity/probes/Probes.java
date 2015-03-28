package sk.stuba.fiit.perconik.activity.probes;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nullable;

import com.google.common.base.Supplier;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Preconditions.checkNotNull;

public final class Probes {
  private Probes() {}

  private static final class CachedProbe<T> extends ForwardingProbe<T> implements Serializable {
    private static final long serialVersionUID = 0L;

    private final Probe<T> delegate;

    private final AtomicBoolean probed;

    private T value;

    CachedProbe(final Probe<T> delegate) {
      this.delegate = checkNotNull(delegate);
      this.probed = new AtomicBoolean(false);
    }

    @Override
    protected Probe<T> delegate() {
      return this.delegate;
    }

    @Override
    public T get() {
      if (this.probed.compareAndSet(false, true)) {
        this.value = this.delegate.get();
      }

      return this.value;
    }
  }

  private static final class SynchronizedProbe<T> implements Probe<T>, Serializable {
    private static final long serialVersionUID = 0L;

    private final Probe<T> delegate;

    private final Object mutex;

    SynchronizedProbe(final Probe<T> delegate, final Object mutex) {
      this.delegate = checkNotNull(delegate);
      this.mutex = (mutex == null) ? this : mutex;
    }

    @Override
    public boolean equals(@Nullable final Object object) {
      if (object == this) {
        return true;
      }

      synchronized (this.mutex) {
        return this.delegate.equals(object);
      }
    }

    @Override
    public int hashCode() {
      synchronized (this.mutex) {
        return this.delegate.hashCode();
      }
    }

    @Override
    public String toString() {
      synchronized (this.mutex) {
        return this.delegate.toString();
      }
    }

    private void writeObject(final ObjectOutputStream stream) throws IOException {
      synchronized (this.mutex) {
        stream.defaultWriteObject();
      }
    }

    public T get() {
      synchronized (this.mutex) {
        return this.delegate.get();
      }
    }
  }

  public static <T> Probe<T> cachedProbe(final Probe<T> probe) {
    return new CachedProbe<>(probe);
  }

  public static <T> Probe<T> synchronizedProbe(final Probe<T> probe) {
    return new SynchronizedProbe<>(probe, null);
  }

  private static final class ConstantProbe<T> implements Probe<T>, Serializable {
    private static final long serialVersionUID = 0L;

    private final T value;

    ConstantProbe(final T value) {
      this.value = value;
    }

    @Override
    public boolean equals(@Nullable final Object object) {
      return object == this || ((object instanceof ConstantProbe) && equal(this.value, ((ConstantProbe<?>) object).value));
    }

    @Override
    public int hashCode() {
      return this.value != null ? this.value.hashCode() : 0;
    }

    @Override
    public String toString() {
      return "forConstant(" + this.value + ")";
    }

    public T get() {
      return this.value;
    }
  }

  private static final class SupplierProbe<T> implements Probe<T>, Serializable {
    private static final long serialVersionUID = 0L;

    private final Supplier<T> supplier;

    SupplierProbe(final Supplier<T> supplier) {
      this.supplier = checkNotNull(supplier);
    }

    @Override
    public boolean equals(@Nullable final Object object) {
      return object == this || ((object instanceof SupplierProbe) && equal(this.supplier, ((SupplierProbe<?>) object).supplier));
    }

    @Override
    public int hashCode() {
      return this.supplier.hashCode();
    }

    @Override
    public String toString() {
      return "forSupplier(" + this.supplier + ")";
    }

    public T get() {
      return this.supplier.get();
    }
  }

  public static <T> Probe<T> forConstant(final T value) {
    return new ConstantProbe<>(value);
  }

  public static <T> Probe<T> forSupplier(final Supplier<T> supplier) {
    return new SupplierProbe<>(supplier);
  }
}
