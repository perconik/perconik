package sk.stuba.fiit.perconik.data.store;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.data.content.Content;

public interface Store<R> extends AutoCloseable {
  public Content load(String path, @Nullable R request) throws Exception;

  public void save(String path, @Nullable R resource) throws Exception;

  @Override
  public void close() throws Exception;
}
