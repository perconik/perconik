package com.gratex.perconik.activity.ide;

import sk.stuba.fiit.perconik.environment.Environment;

final class Internals {
  static final boolean debugFlag = Environment.debug;

  static final int unknonwnPid = -1;

  private Internals() {}

  static int pid() {
    try {
      return Environment.getProcessIdentifier();
    } catch (RuntimeException e) {
      return unknonwnPid;
    }
  }
}
