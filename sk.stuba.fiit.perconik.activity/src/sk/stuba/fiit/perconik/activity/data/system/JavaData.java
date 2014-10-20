package sk.stuba.fiit.perconik.activity.data.system;

import java.net.URL;
import java.nio.file.Path;

import sk.stuba.fiit.perconik.data.AnyStructuredData;

public class JavaData extends AnyStructuredData {
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

  public JavaData() {}

  public void setVendor(final String vendor) {
    this.vendor = vendor;
  }

  public void setVendorUrl(final URL vendorUrl) {
    this.vendorUrl = vendorUrl;
  }

  public void setVersion(final String version) {
    this.version = version;
  }

  public void setSpecification(final SpecificationData specification) {
    this.specification = specification;
  }

  public void setVirtualMachine(final VirtualMachineData virtualMachine) {
    this.virtualMachine = virtualMachine;
  }

  public void setHome(final Path home) {
    this.home = home;
  }

  public void setCompiler(final String compiler) {
    this.compiler = compiler;
  }

  public void setClassPath(final Path classPath) {
    this.classPath = classPath;
  }

  public void setLibraryPath(final Path libraryPath) {
    this.libraryPath = libraryPath;
  }

  public void setExtensionDirectories(final Path extensionDirectories) {
    this.extensionDirectories = extensionDirectories;
  }

  public void setTemporaryDirectory(final Path temporaryDirectory) {
    this.temporaryDirectory = temporaryDirectory;
  }

  public String getVendor() {
    return this.vendor;
  }

  public URL getVendorUrl() {
    return this.vendorUrl;
  }

  public String getVersion() {
    return this.version;
  }

  public SpecificationData getSpecification() {
    return this.specification;
  }

  public VirtualMachineData getVirtualMachine() {
    return this.virtualMachine;
  }

  public Path getHome() {
    return this.home;
  }

  public String getCompiler() {
    return this.compiler;
  }

  public Path getClassPath() {
    return this.classPath;
  }

  public Path getLibraryPath() {
    return this.libraryPath;
  }

  public Path getExtensionDirectories() {
    return this.extensionDirectories;
  }

  public Path getTemporaryDirectory() {
    return this.temporaryDirectory;
  }
}
