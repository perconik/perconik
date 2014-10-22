package sk.stuba.fiit.perconik.environment.java;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Joiner;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newArrayListWithCapacity;

public abstract class JavaProcessBuilder {
  final Path java;

  final String target;

  final List<String> options;

  final List<String> arguments;

  JavaProcessBuilder(final Path java, final String target) {
    this.java = requireNonNull(java);
    this.target = requireNonNull(target);
    this.options = newArrayList();
    this.arguments = newArrayList();
  }

  public static final class Defaults {
    public static final Path java = Paths.get("java");

    private Defaults() {
    }
  }

  public static JavaProcessBuilder forClass(final String name) {
    return new ClassTarget(Defaults.java, requireNonNullOrEmpty(name));
  }

  public static JavaProcessBuilder forClass(final String name, final Path java) {
    return new ClassTarget(java, requireNonNullOrEmpty(name));
  }

  public static JavaProcessBuilder forJar(final Path jar) {
    return new JarTarget(Defaults.java, jar.toString());
  }

  public static JavaProcessBuilder forJar(final Path jar, final Path java) {
    return new JarTarget(java, jar.toString());
  }

  private static final class ClassTarget extends JavaProcessBuilder {
    ClassTarget(final Path java, final String target) {
      super(java, target);
    }

    @Override
    void addTarget(final List<String> command) {
      command.add(this.target);
    }
  }

  private static final class JarTarget extends JavaProcessBuilder {
    JarTarget(final Path java, final String target) {
      super(java, target);
    }

    @Override
    void addTarget(final List<String> command) {
      command.add("-jar");
      command.add(this.target);
    }
  }

  abstract void addTarget(List<String> command);

  void addOption(final String value) {
    this.options.add("-" + requireNonNullOrEmpty(value));
  }

  void addOptionWithArgument(final String name, final Object argument) {
    this.options.add("-" + requireNonNullOrEmpty(name));

    String value = argument.toString();

    if (!value.isEmpty()) {
      this.options.add(value);
    }
  }

  void addNonStandardOption(final String value) {
    this.options.add("-X" + requireNonNullOrEmpty(value));
  }

  void addSystemProperty(final String name, final Object value) {
    this.options.add("-D" + requireNonNullOrEmpty(name) + "=" + value.toString());
  }

  void addArgument(final Object value) {
    this.arguments.add(requireNonNullOrEmpty(value.toString()));
  }

  public JavaProcessBuilder option(final String value) {
    this.addOption(value);

    return this;
  }

  public JavaProcessBuilder option(final String name, final Object argument) {
    this.addOptionWithArgument(name, argument);

    return this;
  }

  public JavaProcessBuilder options(final String ... values) {
    return this.options(asList(values));
  }

  public JavaProcessBuilder options(final Iterable<String> values) {
    for (String value: values) {
      this.addOption(value);
    }

    return this;
  }

  public JavaProcessBuilder nonStandardOption(final String value) {
    this.addNonStandardOption(value);

    return this;
  }

  public JavaProcessBuilder nonStandardOptions(final String ... values) {
    return this.nonStandardOptions(Arrays.asList(values));
  }

  public JavaProcessBuilder nonStandardOptions(final Iterable<String> values) {
    for (String value: values) {
      this.addNonStandardOption(value);
    }

    return this;
  }

  public JavaProcessBuilder systemProperty(final String name, final Object value) {
    this.addSystemProperty(name, value);

    return this;
  }

  public JavaProcessBuilder systemProperty(final Entry<String, ?> property) {
    return this.systemProperty(property.getKey(), property.getValue());
  }

  public JavaProcessBuilder systemProperties(final Map<String, ?> properties) {
    return this.systemProperties(properties.entrySet());
  }

  public <E extends Entry<String, ?>> JavaProcessBuilder systemProperties(final Iterable<E> properties) {
    for (Entry<String, ?> entry: properties) {
      this.addSystemProperty(entry.getKey(), entry.getValue());
    }

    return this;
  }

  public JavaProcessBuilder argument(final Object value) {
    this.addArgument(value);

    return this;
  }

  public JavaProcessBuilder arguments(final Object ... values) {
    return this.arguments(Arrays.asList(values));
  }

  public JavaProcessBuilder arguments(final Iterable<?> values) {
    for (Object value: values) {
      this.addArgument(value);
    }

    return this;
  }

  private static String requireNonNullOrEmpty(final String value) {
    checkArgument(!isNullOrEmpty(value));

    return value;
  }

  public Path java() {
    return this.java;
  }

  public String target() {
    return this.target;
  }

  public List<String> options() {
    return newArrayList(this.options);
  }

  public List<String> arguments() {
    return newArrayList(this.arguments);
  }

  public List<String> toCommand() {
    int size = 4 + this.options.size() + this.arguments.size();

    List<String> command = newArrayListWithCapacity(size);

    command.add(this.java.toString());
    command.addAll(this.options);
    this.addTarget(command);
    command.addAll(this.arguments);

    return command;
  }

  public ProcessBuilder toProcessBuilder() {
    return new ProcessBuilder(this.toCommand());
  }

  @Override
  public String toString() {
    return Joiner.on(' ').join(this.toCommand());
  }

  public Process start() throws IOException {
    return this.toProcessBuilder().start();
  }
}
