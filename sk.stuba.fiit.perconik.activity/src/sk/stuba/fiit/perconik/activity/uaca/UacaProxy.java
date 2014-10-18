package sk.stuba.fiit.perconik.activity.uaca;

import javax.annotation.Nullable;

import com.gratex.perconik.uaca.SharedUacaProxy;

import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.store.Store;

public final class UacaProxy extends SharedUacaProxy implements Store {
  public UacaProxy() {
  }

  public final Content load(final String path, @Nullable final Content request) {
    throw new UnsupportedOperationException();
  }

  public final void save(final String path, @Nullable final Content data) {
    this.send("generic/event", UacaDataWrapper.of(path, data));
  }
}
