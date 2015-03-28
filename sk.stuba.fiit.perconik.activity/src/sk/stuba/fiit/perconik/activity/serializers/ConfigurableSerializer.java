package sk.stuba.fiit.perconik.activity.serializers;

public interface ConfigurableSerializer<T> extends Serializer<T> {
  public interface Option {
  }

  public enum StandardOption implements Option {
    TREE;
  }
}
