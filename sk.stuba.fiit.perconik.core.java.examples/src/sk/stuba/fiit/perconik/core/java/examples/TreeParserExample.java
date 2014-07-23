package sk.stuba.fiit.perconik.core.java.examples;

import java.io.IOException;
import java.nio.file.Paths;

import org.eclipse.jdt.core.dom.CompilationUnit;

import sk.stuba.fiit.perconik.core.java.dom.TreeParsers;

import static java.lang.System.out;

public class TreeParserExample {
  public static void main(String[] args) throws IOException {
    //		String source = Files.toString(new File("fixtures/NodeClassFilter.java"), Charsets.UTF_8);
    //		
    //		ASTParser parser = TreeParsers.newParser();
    //		
    //		CompilationUnit u = (CompilationUnit) Nodes.create(parser, source);

    CompilationUnit u = (CompilationUnit) TreeParsers.parse(Paths.get("fixtures/NodeClassFilter.java"));

    out.println(u);
  }
}
