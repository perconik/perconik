package sk.stuba.fiit.perconik.core.java.examples;

import java.util.Arrays;

import uk.ac.open.crc.intt.IdentifierNameTokeniser;
import uk.ac.open.crc.intt.IdentifierNameTokeniserFactory;

import static java.lang.System.out;

public final class IdentifierNameTokeniserExample {
  public static void main(String[] args) {
    IdentifierNameTokeniserFactory factory = new IdentifierNameTokeniserFactory();

    factory.setSeparatorCharacters("._$");

    out.print(factory);

    IdentifierNameTokeniser tokeniser = factory.create();

    out.println("----");

    out.println(Arrays.toString(tokeniser.tokenise("hello_world")));
    out.println(Arrays.toString(tokeniser.tokenise("helloWorld")));
    out.println(Arrays.toString(tokeniser.tokenise("HELLOworld")));
    out.println(Arrays.toString(tokeniser.tokenise("helloworld")));
    out.println(Arrays.toString(tokeniser.tokenise("ASTNode")));
    out.println(Arrays.toString(tokeniser.tokenise("ASTVisitor")));
    out.println(Arrays.toString(tokeniser.tokenise("ASTMATCHER")));
    out.println(Arrays.toString(tokeniser.tokenise("bin2hex")));
    out.println(Arrays.toString(tokeniser.tokenise("pop32Html")));
    out.println(Arrays.toString(tokeniser.tokenise("sk.fiit.stuba.perconik.core.java.dom.traverse.NodeVisitor")));
  }
}
