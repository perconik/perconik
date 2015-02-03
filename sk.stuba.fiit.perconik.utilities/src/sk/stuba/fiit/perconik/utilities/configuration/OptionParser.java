package sk.stuba.fiit.perconik.utilities.configuration;

import com.google.common.primitives.UnsignedInteger;
import com.google.common.primitives.UnsignedLong;

public interface OptionParser {
  public Boolean parseBoolean(Object object);

  public Byte parseByte(Object object);

  public Short parseShort(Object object);

  public Integer parseInteger(Object object);

  public Long parseLong(Object object);

  public UnsignedInteger parseUnsignedInteger(Object object);

  public UnsignedLong parseUnsignedLong(Object object);

  public Float parseFloat(Object object);

  public Double parseDouble(Object object);

  public Character parseCharacter(Object object);

  public String parseString(Object object);
}
