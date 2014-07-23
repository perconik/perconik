package sk.stuba.fiit.perconik.activity.data.eclipse;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;
import org.osgi.framework.Version;

import sk.stuba.fiit.perconik.activity.data.AnyStructuredData;
import sk.stuba.fiit.perconik.osgi.framework.BundleState;
import sk.stuba.fiit.perconik.utilities.MoreMaps;

public class BundleData extends AnyStructuredData
{
	protected long identifier;
	
	protected String category;

	protected String name;
	
	protected String symbolicName;

	protected String vendor;

	protected Version version;

	protected String description;

	protected String copyright;

	protected String activator;

	protected String classPath;
	
	protected String nativeCode;
	
	protected BundleState state;

	public BundleData()
	{
	}

	protected BundleData(Bundle bundle)
	{
		if (bundle == null)
		{
			return;
		}
		
		Map<String, String> headers = MoreMaps.fromDictionary(bundle.getHeaders());
		
		this.setIdentifier(bundle.getBundleId());
		this.setCategory(headers.get(Constants.BUNDLE_CATEGORY));
		this.setName(headers.get(Constants.BUNDLE_NAME));
		this.setSymbolicName(bundle.getSymbolicName());
		this.setVendor(headers.get(Constants.BUNDLE_VENDOR));
		this.setVersion(bundle.getVersion());
		this.setDescription(headers.get(Constants.BUNDLE_DESCRIPTION));
		this.setCopyright(headers.get(Constants.BUNDLE_COPYRIGHT));

		this.setActivator(headers.get(Constants.BUNDLE_ACTIVATOR));
		this.setClassPath(headers.get(Constants.BUNDLE_CLASSPATH));
		this.setNativeCode(headers.get(Constants.BUNDLE_NATIVECODE));
				
		this.setState(BundleState.valueOf(bundle.getState()));
	}

	public static BundleData of(Bundle bundle)
	{
		return new BundleData(bundle);
	}
	
	public static List<BundleData> of(Iterable<Bundle> bundles)
	{
		List<BundleData>  data = Lists.newArrayList();
		
		for (Bundle bundle: bundles)
		{
			data.add(new BundleData(bundle));
		}
		
		return data;
	}

	public void setIdentifier(long identifier)
	{
		this.identifier = identifier;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setSymbolicName(String symbolicName)
	{
		this.symbolicName = symbolicName;
	}

	public void setVendor(String vendor)
	{
		this.vendor = vendor;
	}

	public void setVersion(Version version)
	{
		this.version = version;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setCopyright(String copyright)
	{
		this.copyright = copyright;
	}

	public void setActivator(String activator)
	{
		this.activator = activator;
	}

	public void setClassPath(String classPath)
	{
		this.classPath = classPath;
	}

	public void setNativeCode(String nativeCode)
	{
		this.nativeCode = nativeCode;
	}

	public void setState(BundleState state)
	{
		this.state = state;
	}

	public long getIdentifier()
	{
		return this.identifier;
	}

	public BundleState getState()
	{
		return this.state;
	}

	public String getCategory()
	{
		return this.category;
	}

	public String getName()
	{
		return this.name;
	}

	public String getSymbolicName()
	{
		return this.symbolicName;
	}

	public String getVendor()
	{
		return this.vendor;
	}

	public Version getVersion()
	{
		return this.version;
	}

	public String getDescription()
	{
		return this.description;
	}

	public String getCopyright()
	{
		return this.copyright;
	}

	public String getActivator()
	{
		return this.activator;
	}

	public String getClassPath()
	{
		return this.classPath;
	}

	public String getNativeCode()
	{
		return this.nativeCode;
	}
}
