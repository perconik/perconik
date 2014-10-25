package sk.stuba.fiit.perconik.activity.uaca;

import javax.annotation.Nullable;

import com.gratex.perconik.uaca.SharedUacaProxy;

import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.store.Store;

public final class UacaProxy extends SharedUacaProxy implements Store<Object> {
  public UacaProxy() {}

  public Content load(final String path, @Nullable final Object request) {
    throw new UnsupportedOperationException();
  }

  public void save(final String path, @Nullable final Object resource) {
    this.send("generic/event", UacaDataWrapper.of(path, resource));
  }
}
