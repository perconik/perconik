package sk.stuba.fiit.perconik.data.store;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.data.content.Content;

public interface Store extends AutoCloseable {
  public Content load(String path, @Nullable Content request) throws Exception;

  public void save(String path, @Nullable Content resource) throws Exception;

  @Override
  public void close() throws Exception;
}
