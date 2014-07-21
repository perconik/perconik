package sk.stuba.fiit.perconik.activity.data.eclipse;

import org.eclipse.core.runtime.IProduct;

import sk.stuba.fiit.perconik.activity.data.AnyStructuredData;

public class ProductData extends AnyStructuredData
{
	protected String application;

	protected String identifier;
	
	protected String name;
	
	protected String description;
	
	protected BundleData bundle;
	
	public ProductData()
	{
	}

	protected ProductData(IProduct product)
	{
		if (product == null)
		{
			return;
		}
		
		this.setApplication(product.getApplication());
		this.setIdentifier(product.getId());
		this.setName(product.getName());
		this.setDescription(product.getDescription());
		this.setBundle(BundleData.of(product.getDefiningBundle()));
	}

	public static ProductData of(IProduct product)
	{
		return new ProductData(product);
	}

	public void setApplication(String application)
	{
		this.application = application;
	}

	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setBundle(BundleData bundle)
	{
		this.bundle = bundle;
	}

	public String getApplication()
	{
		return this.application;
	}

	public String getIdentifier()
	{
		return this.identifier;
	}

	public String getName()
	{
		return this.name;
	}

	public String getDescription()
	{
		return this.description;
	}

	public BundleData getBundle()
	{
		return this.bundle;
	}
}
