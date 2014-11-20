package sk.stuba.fiit.perconik.activity.serializers;

public interface ConfigurableSerializer {
  public interface Option {}

  public enum StandardOption implements Option {
    TREE;
  }
}
