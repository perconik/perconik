package sk.stuba.fiit.perconik.activity.data;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import sk.stuba.fiit.perconik.data.AnyStructuredData;

public class ObjectData extends AnyStructuredData {
  protected ClassData type;

  protected HashData hash;

  protected String string;

  public ObjectData() {}

  protected ObjectData(final Object object) {
    if (object == null) {
      return;
    }

    this.setType(ClassData.of(object));
    this.setHash(HashData.of(object));
    this.setString(object.toString());
  }

  public static ObjectData of(final Object object) {
    return new ObjectData(object);
  }

  @JsonSetter("class")
  public void setType(final ClassData type) {
    this.type = type;
  }

  public void setHash(final HashData hash) {
    this.hash = hash;
  }

  public void setString(final String string) {
    this.string = string;
  }

  @JsonGetter("class")
  public ClassData getType() {
    return this.type;
  }

  public HashData getHash() {
    return this.hash;
  }

  public String getString() {
    return this.string;
  }
}
