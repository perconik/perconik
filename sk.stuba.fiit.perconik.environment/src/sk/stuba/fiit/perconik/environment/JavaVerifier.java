package sk.stuba.fiit.perconik.environment;

import org.osgi.framework.Version;

public enum JavaVerifier {
  JAVA_5("1.5"),

  JAVA_6("1.6"),

  JAVA_7("1.7"),

  JAVA_8("1.8");

  private final transient Version version;

  private JavaVerifier(final String version) {
    this.version = parseJavaVersion(version);
  }

  public static final Version parseJavaVersion(final String value) {
    return Version.parseVersion(value.replace("_", "."));
  }

  public final boolean isJavaCompatible() {
    return this.version.compareTo(Environment.getJavaVersion()) < 0;
  }

  public final void verify() throws JavaVerificationException {
    if (!this.isJavaCompatible()) {
      throw new JavaVerificationException(this.version, Environment.getJavaVersion());
    }
  }

  public final Version getJavaVersion() {
    return this.version;
  }
}
