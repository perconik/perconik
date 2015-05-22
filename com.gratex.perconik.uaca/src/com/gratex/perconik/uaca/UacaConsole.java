package com.gratex.perconik.uaca;

import com.gratex.perconik.uaca.plugin.Activator;

import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.core.runtime.TimeHookConsole;

public final class UacaConsole extends TimeHookConsole {
  private static final UacaConsole shared = new UacaConsole();

  private UacaConsole() {}

  public static UacaConsole getShared() {
    return shared;
  }

  @Override
  protected PluginConsole delegate() {
    return Activator.defaultInstance().getConsole();
  }
}
