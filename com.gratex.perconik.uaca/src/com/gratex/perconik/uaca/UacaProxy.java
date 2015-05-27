package com.gratex.perconik.uaca;

import javax.annotation.Nullable;

public interface UacaProxy extends AutoCloseable {
  public void send(final String path, @Nullable Object request);
}
