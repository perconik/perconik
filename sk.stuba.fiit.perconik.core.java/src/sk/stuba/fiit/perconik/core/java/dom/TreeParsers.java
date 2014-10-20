package sk.stuba.fiit.perconik.core.java.dom;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;

import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.TreeApiLevel;

public final class TreeParsers {
  private TreeParsers() {}

  public static ASTParser newParser() {
    return newParser(TreeApiLevel.latest());
  }

  public static ASTParser newParser(final TreeApiLevel level) {
    return ASTParser.newParser(level.getValue());
  }

  public static String read(final Path path) throws IOException {
    return Files.toString(path.toFile(), Charsets.UTF_8);
  }

  public static String read(final Path path, final Charset charset) throws IOException {
    return Files.toString(path.toFile(), charset);
  }

  public static ASTNode parse(final Path path) throws IOException {
    return parse(path, Charsets.UTF_8);
  }

  public static ASTNode parse(final Path path, final Charset charset) throws IOException {
    return parse(path, charset, TreeApiLevel.latest());
  }

  public static ASTNode parse(final Path path, final Charset charset, final TreeApiLevel level) throws IOException {
    return parse(Files.toString(path.toFile(), charset), level);
  }

  public static ASTNode parse(final String source) {
    return parse(source, TreeApiLevel.latest());
  }

  public static ASTNode parse(final String source, final TreeApiLevel level) {
    return Nodes.create(newParser(level), source);
  }

  public static ASTNode parse(final ICompilationUnit source) {
    return parse(source, TreeApiLevel.latest(), true);
  }

  public static ASTNode parse(final ICompilationUnit source, final TreeApiLevel level, final boolean resolveBindings) {
    return Nodes.create(newParser(level), source, resolveBindings);
  }

  //  TODO rm?
  //	public static ASTNode parse(final IClassFile source)
  //	{
  //		return parse(source, TreeApiLevel.latest());
  //	}
  //
  //	public static ASTNode parse(final IClassFile source, final TreeApiLevel level)
  //	{
  //		return Nodes.create(newParser(level), source);
  //	}
}
