package sk.stuba.fiit.perconik.activity.uaca;

import java.nio.file.Path;

import javax.annotation.Nullable;

import com.gratex.perconik.uaca.SharedUacaProxy;

import sk.stuba.fiit.perconik.activity.data.Content;
import sk.stuba.fiit.perconik.activity.data.Store;

public final class UacaProxy extends SharedUacaProxy implements Store {
  public UacaProxy() {
  }

  public final Content load(final Path path, @Nullable final Content request) {
    throw new UnsupportedOperationException();
  }

  public final void save(final Path path, @Nullable final Content data) {
    this.send("generic/event", UacaData.of(path, data));
  }
}
