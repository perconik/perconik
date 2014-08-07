package sk.stuba.fiit.perconik.activity.data;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;

import javax.annotation.Nullable;

public interface Store extends Closeable {
  public Content load(Path path, @Nullable Content request) throws Exception;

  public void save(Path path, @Nullable Content data) throws Exception;

  @Override
  public void close() throws IOException;
}
