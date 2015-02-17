package sk.stuba.fiit.perconik.utilities.configuration;

import java.io.Serializable;
import java.util.Map.Entry;

import com.google.common.base.Equivalence;

import static com.google.common.base.Objects.equal;

final class OptionEquivalence extends Equivalence<Entry<String, Object>> implements Serializable {
  private static final long serialVersionUID = 0L;

  static final OptionEquivalence INSTANCE = new OptionEquivalence();

  private OptionEquivalence() {}

  static final class KeyEquivalence extends Equivalence<String> implements Serializable {
    private static final long serialVersionUID = 0L;

    static final KeyEquivalence INSTANCE = new KeyEquivalence();

    private KeyEquivalence() {}

    @Override
    protected boolean doEquivalent(final String a, final String b) {
      return equalKeys(a, b);
    }

    @Override
    protected int doHash(final String s) {
      return hashKey(s);
    }

    @SuppressWarnings("static-method")
    private Object readResolve() {
      return INSTANCE;
    }
  }

  static final class ValueEquivalence extends Equivalence<Object> implements Serializable {
    private static final long serialVersionUID = 0L;

    static final ValueEquivalence INSTANCE = new ValueEquivalence();

    private ValueEquivalence() {}

    @Override
    protected boolean doEquivalent(final Object a, final Object b) {
      return equalValues(a, b);
    }

    @Override
    protected int doHash(final Object o) {
      return hashValue(o);
    }

    @SuppressWarnings("static-method")
    private Object readResolve() {
      return INSTANCE;
    }
  }

  static boolean equalKeys(final String a, final String b) {
    return equal(a, b);
  }

  static boolean equalValues(final Object a, final Object b) {
    return a == b || (a != null && b != null && a.toString().equals(b.toString()));
  }

  static int hashKey(final String s) {
    return s != null ? s.hashCode() : 0;
  }

  static int hashValue(final Object o) {
    return o != null ? o.toString().hashCode() : 0;
  }

  @Override
  protected boolean doEquivalent(final Entry<String, Object> a, final Entry<String, Object> b) {
    return a == b || (a != null && b != null && equalKeys(a.getKey(), b.getKey()) && equalValues(a.getValue(), b.getValue()));
  }

  @Override
  protected int doHash(final Entry<String, Object> option) {
    return hashKey(option.getKey()) ^ hashValue(option.getValue());
  }

  @SuppressWarnings("static-method")
  private Object readResolve() {
    return INSTANCE;
  }
}
