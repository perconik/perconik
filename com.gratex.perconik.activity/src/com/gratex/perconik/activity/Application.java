package com.gratex.perconik.activity;

import org.eclipse.core.runtime.IProduct;
import sk.stuba.fiit.perconik.eclipse.core.runtime.Products;

public final class Application
{
	private static final Application instance = new Application();
	
	private final int pid;
	
	private final IProduct product;
	
	private Application()
	{
		this.pid     = Internals.pid();
		this.product = Products.getProduct();
	}
	
	public static final Application getInstance()
	{
		return instance;
	}
	
	public final int getPid()
	{
		return this.pid;
	}
	
	public final String getName()
	{
		return Products.getName(this.product);
	}
	
	public final String getVersion()
	{
		return Products.getVersion(this.product).toString();
	}
}
