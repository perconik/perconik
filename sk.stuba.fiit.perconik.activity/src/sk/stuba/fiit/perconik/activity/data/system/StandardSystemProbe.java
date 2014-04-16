package sk.stuba.fiit.perconik.activity.data.system;

import static com.google.common.base.StandardSystemProperty.JAVA_CLASS_PATH;
import static com.google.common.base.StandardSystemProperty.JAVA_COMPILER;
import static com.google.common.base.StandardSystemProperty.JAVA_EXT_DIRS;
import static com.google.common.base.StandardSystemProperty.JAVA_HOME;
import static com.google.common.base.StandardSystemProperty.JAVA_IO_TMPDIR;
import static com.google.common.base.StandardSystemProperty.JAVA_LIBRARY_PATH;
import static com.google.common.base.StandardSystemProperty.JAVA_SPECIFICATION_NAME;
import static com.google.common.base.StandardSystemProperty.JAVA_SPECIFICATION_VENDOR;
import static com.google.common.base.StandardSystemProperty.JAVA_SPECIFICATION_VERSION;
import static com.google.common.base.StandardSystemProperty.JAVA_VENDOR;
import static com.google.common.base.StandardSystemProperty.JAVA_VENDOR_URL;
import static com.google.common.base.StandardSystemProperty.JAVA_VERSION;
import static com.google.common.base.StandardSystemProperty.JAVA_VM_NAME;
import static com.google.common.base.StandardSystemProperty.JAVA_VM_SPECIFICATION_NAME;
import static com.google.common.base.StandardSystemProperty.JAVA_VM_SPECIFICATION_VENDOR;
import static com.google.common.base.StandardSystemProperty.JAVA_VM_SPECIFICATION_VERSION;
import static com.google.common.base.StandardSystemProperty.JAVA_VM_VENDOR;
import static com.google.common.base.StandardSystemProperty.JAVA_VM_VERSION;
import static com.google.common.base.StandardSystemProperty.OS_ARCH;
import static com.google.common.base.StandardSystemProperty.OS_NAME;
import static com.google.common.base.StandardSystemProperty.OS_VERSION;
import static com.google.common.base.StandardSystemProperty.USER_DIR;
import static com.google.common.base.StandardSystemProperty.USER_HOME;
import static com.google.common.base.StandardSystemProperty.USER_NAME;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import sk.stuba.fiit.perconik.utilities.net.UniformResources;

public final class StandardSystemProbe implements SystemProbe
{
	public StandardSystemProbe()
	{
	}

	public final SystemData system()
	{
		SystemData data = new SystemData();
		
		data.java = new JavaData();
		data.java.vendor = JAVA_VENDOR.value();
		data.java.vendorUrl = url(JAVA_VENDOR_URL.value());
		data.java.version = JAVA_VERSION.value();
		data.java.specification = new SpecificationData();
		data.java.specification.name = JAVA_SPECIFICATION_NAME.value();
		data.java.specification.vendor = JAVA_SPECIFICATION_VENDOR.value();
		data.java.specification.version = JAVA_SPECIFICATION_VERSION.value();
		data.java.virtualMachine = new VirtualMachineData();
		data.java.virtualMachine.name = JAVA_VM_NAME.value();
		data.java.virtualMachine.vendor = JAVA_VM_VENDOR.value();
		data.java.virtualMachine.version = JAVA_VM_VERSION.value();
		data.java.virtualMachine.specification = new SpecificationData();
		data.java.virtualMachine.specification.name = JAVA_VM_SPECIFICATION_NAME.value();
		data.java.virtualMachine.specification.vendor = JAVA_VM_SPECIFICATION_VENDOR.value();
		data.java.virtualMachine.specification.version = JAVA_VM_SPECIFICATION_VERSION.value();
		data.java.home = path(JAVA_HOME.value());
		data.java.compiler = JAVA_COMPILER.value();
		data.java.classPath = path(JAVA_CLASS_PATH.value());
		data.java.libraryPath = path(JAVA_LIBRARY_PATH.value());
		data.java.extensionDirectories = path(JAVA_EXT_DIRS.value());
		data.java.temporaryDirectory = path(JAVA_IO_TMPDIR.value());
		data.operatingSystem = new OperatingSystemData();
		data.operatingSystem.architecture = OS_ARCH.value();
		data.operatingSystem.name = OS_NAME.value();
		data.operatingSystem.version = OS_VERSION.value();
		data.user = new UserData();
		data.user.name = USER_NAME.value();
		data.user.home = path(USER_HOME.value());
		data.user.directory = path(USER_DIR.value());
		
		return data;
	}

	private static final Path path(final String value)
	{
		try
		{
			return Paths.get(value);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	private static final URL url(final String value)
	{
		try
		{
			return UniformResources.newUrl(value);
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
