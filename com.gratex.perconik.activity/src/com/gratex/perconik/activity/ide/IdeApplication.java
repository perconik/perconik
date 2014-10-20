package com.gratex.perconik.activity.ide;

import org.eclipse.core.runtime.IProduct;

import sk.stuba.fiit.perconik.eclipse.core.runtime.Products;

public final class IdeApplication {
  private static final IdeApplication instance = new IdeApplication();

  private final int pid;

  private final boolean debug;

  private final IProduct product;

  private IdeApplication() {
    this.pid = Internals.pid();
    this.debug = Internals.debug;
    this.product = Products.getProduct();
  }

  public static IdeApplication getInstance() {
    return instance;
  }

  public boolean isDebug() {
    return this.debug;
  }

  public int getPid() {
    return this.pid;
  }

  public String getName() {
    return Products.getName(this.product);
  }

  public String getVersion() {
    return Products.getVersion(this.product).toString();
  }
}
