package sk.stuba.fiit.perconik.activity.data.system;

import java.net.URL;
import java.nio.file.Path;
import sk.stuba.fiit.perconik.activity.data.AnyStructuredData;

public class JavaData extends AnyStructuredData
{
	protected String vendor;
	
	protected URL vendorUrl;
	
	protected String version;
	
	protected SpecificationData specification;

	protected VirtualMachineData virtualMachine;
	
	protected Path home;

	protected String compiler;

	protected Path classPath;
	
	protected Path libraryPath;
	
	protected Path extensionDirectories;
	
	protected Path temporaryDirectory;
	
	public JavaData()
	{
	}

	public void setVendor(String vendor)
	{
		this.vendor = vendor;
	}

	public void setVendorUrl(URL vendorUrl)
	{
		this.vendorUrl = vendorUrl;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public void setSpecification(SpecificationData specification)
	{
		this.specification = specification;
	}

	public void setVirtualMachine(VirtualMachineData virtualMachine)
	{
		this.virtualMachine = virtualMachine;
	}

	public void setHome(Path home)
	{
		this.home = home;
	}

	public void setCompiler(String compiler)
	{
		this.compiler = compiler;
	}

	public void setClassPath(Path classPath)
	{
		this.classPath = classPath;
	}

	public void setLibraryPath(Path libraryPath)
	{
		this.libraryPath = libraryPath;
	}

	public void setExtensionDirectories(Path extensionDirectories)
	{
		this.extensionDirectories = extensionDirectories;
	}

	public void setTemporaryDirectory(Path temporaryDirectory)
	{
		this.temporaryDirectory = temporaryDirectory;
	}

	public String getVendor()
	{
		return this.vendor;
	}

	public URL getVendorUrl()
	{
		return this.vendorUrl;
	}

	public String getVersion()
	{
		return this.version;
	}

	public SpecificationData getSpecification()
	{
		return this.specification;
	}

	public VirtualMachineData getVirtualMachine()
	{
		return this.virtualMachine;
	}

	public Path getHome()
	{
		return this.home;
	}

	public String getCompiler()
	{
		return this.compiler;
	}

	public Path getClassPath()
	{
		return this.classPath;
	}

	public Path getLibraryPath()
	{
		return this.libraryPath;
	}

	public Path getExtensionDirectories()
	{
		return this.extensionDirectories;
	}

	public Path getTemporaryDirectory()
	{
		return this.temporaryDirectory;
	}
}
