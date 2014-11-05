package sk.stuba.fiit.perconik.activity.data.platform;

import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;
import org.osgi.framework.Version;

import sk.stuba.fiit.perconik.data.AnyStructuredData;
import sk.stuba.fiit.perconik.osgi.framework.BundleState;
import sk.stuba.fiit.perconik.utilities.MoreMaps;

import static com.google.common.collect.Lists.newArrayList;

public class BundleData extends AnyStructuredData {
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

  public BundleData() {}

  protected BundleData(final Bundle bundle) {
    if (bundle == null) {
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

  public static BundleData of(final Bundle bundle) {
    return new BundleData(bundle);
  }

  public static List<BundleData> of(final Iterable<Bundle> bundles) {
    List<BundleData> data = newArrayList();

    for (Bundle bundle: bundles) {
      data.add(new BundleData(bundle));
    }

    return data;
  }

  public void setIdentifier(final long identifier) {
    this.identifier = identifier;
  }

  public void setCategory(final String category) {
    this.category = category;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setSymbolicName(final String symbolicName) {
    this.symbolicName = symbolicName;
  }

  public void setVendor(final String vendor) {
    this.vendor = vendor;
  }

  public void setVersion(final Version version) {
    this.version = version;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public void setCopyright(final String copyright) {
    this.copyright = copyright;
  }

  public void setActivator(final String activator) {
    this.activator = activator;
  }

  public void setClassPath(final String classPath) {
    this.classPath = classPath;
  }

  public void setNativeCode(final String nativeCode) {
    this.nativeCode = nativeCode;
  }

  public void setState(final BundleState state) {
    this.state = state;
  }

  public long getIdentifier() {
    return this.identifier;
  }

  public BundleState getState() {
    return this.state;
  }

  public String getCategory() {
    return this.category;
  }

  public String getName() {
    return this.name;
  }

  public String getSymbolicName() {
    return this.symbolicName;
  }

  public String getVendor() {
    return this.vendor;
  }

  public Version getVersion() {
    return this.version;
  }

  public String getDescription() {
    return this.description;
  }

  public String getCopyright() {
    return this.copyright;
  }

  public String getActivator() {
    return this.activator;
  }

  public String getClassPath() {
    return this.classPath;
  }

  public String getNativeCode() {
    return this.nativeCode;
  }
}
