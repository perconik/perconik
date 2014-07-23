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

import static sk.stuba.fiit.perconik.activity.data.base.Utilities.pathOrNull;
import static sk.stuba.fiit.perconik.activity.data.base.Utilities.urlOrNull;

public final class StandardSystemProbe implements SystemProbe {
  public StandardSystemProbe() {}

  public final SystemData system() {
    SystemData data = new SystemData();

    data.setJava(new JavaData());
    data.java.setVendor(JAVA_VENDOR.value());
    data.java.setVendorUrl(urlOrNull(JAVA_VENDOR_URL.value()));
    data.java.setVersion(JAVA_VERSION.value());
    data.java.setSpecification(new SpecificationData());
    data.java.specification.setName(JAVA_SPECIFICATION_NAME.value());
    data.java.specification.setVendor(JAVA_SPECIFICATION_VENDOR.value());
    data.java.specification.setVersion(JAVA_SPECIFICATION_VERSION.value());
    data.java.setVirtualMachine(new VirtualMachineData());
    data.java.virtualMachine.setName(JAVA_VM_NAME.value());
    data.java.virtualMachine.setVendor(JAVA_VM_VENDOR.value());
    data.java.virtualMachine.setVersion(JAVA_VM_VERSION.value());
    data.java.virtualMachine.setSpecification(new SpecificationData());
    data.java.virtualMachine.specification.setName(JAVA_VM_SPECIFICATION_NAME.value());
    data.java.virtualMachine.specification.setVendor(JAVA_VM_SPECIFICATION_VENDOR.value());
    data.java.virtualMachine.specification.setVersion(JAVA_VM_SPECIFICATION_VERSION.value());
    data.java.setHome(pathOrNull(JAVA_HOME.value()));
    data.java.setCompiler(JAVA_COMPILER.value());
    data.java.setClassPath(pathOrNull(JAVA_CLASS_PATH.value()));
    data.java.setLibraryPath(pathOrNull(JAVA_LIBRARY_PATH.value()));
    data.java.setExtensionDirectories(pathOrNull(JAVA_EXT_DIRS.value()));
    data.java.setTemporaryDirectory(pathOrNull(JAVA_IO_TMPDIR.value()));

    data.setOperatingSystem(new OperatingSystemData());
    data.operatingSystem.setArchitecture(OS_ARCH.value());
    data.operatingSystem.setName(OS_NAME.value());
    data.operatingSystem.setVersion(OS_VERSION.value());

    data.setUser(new UserData());
    data.user.setName(USER_NAME.value());
    data.user.setHome(pathOrNull(USER_HOME.value()));
    data.user.setDirectory(pathOrNull(USER_DIR.value()));

    return data;
  }
}
