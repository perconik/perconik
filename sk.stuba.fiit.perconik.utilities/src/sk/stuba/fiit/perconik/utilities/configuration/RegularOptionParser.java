package sk.stuba.fiit.perconik.utilities.configuration;

import com.google.common.primitives.UnsignedInteger;
import com.google.common.primitives.UnsignedLong;

import static com.google.common.base.Preconditions.checkArgument;

public final class RegularOptionParser implements OptionParser {
  private static final RegularOptionParser INSTANCE = new RegularOptionParser();

  private RegularOptionParser() {}

  public static RegularOptionParser getInstance() {
    return INSTANCE;
  }

  public Boolean parseBoolean(final Object object) {
    return object instanceof Boolean ? (Boolean) object : Boolean.valueOf(object.toString());
  }

  public Byte parseByte(final Object object) {
    return object instanceof Byte ? (Byte) object : Byte.valueOf(object.toString());
  }

  public Short parseShort(final Object object) {
    return object instanceof Short ? (Short) object : Short.valueOf(object.toString());
  }

  public Integer parseInteger(final Object object) {
    return object instanceof Integer ? (Integer) object : Integer.valueOf(object.toString());
  }

  public Long parseLong(final Object object) {
    return object instanceof Long ? (Long) object : Long.valueOf(object.toString());
  }

  public UnsignedInteger parseUnsignedInteger(final Object object) {
    return object instanceof UnsignedInteger ? (UnsignedInteger) object : UnsignedInteger.valueOf(object.toString());
  }

  public UnsignedLong parseUnsignedLong(final Object object) {
    return object instanceof UnsignedLong ? (UnsignedLong) object : UnsignedLong.valueOf(object.toString());
  }

  public Float parseFloat(final Object object) {
    return object instanceof Float ? (Float) object : Float.valueOf(object.toString());
  }

  public Double parseDouble(final Object object) {
    return object instanceof Double ? (Double) object : Double.valueOf(object.toString());
  }

  public Character parseCharacter(final Object object) {
    if (object instanceof Character) {
      return (Character) object;
    }

    String value = object.toString();

    checkArgument(value.length() == 1);

    return Character.valueOf(value.charAt(0));
  }

  public String parseString(final Object object) {
    return object instanceof String ? (String) object : object.toString();
  }
}
