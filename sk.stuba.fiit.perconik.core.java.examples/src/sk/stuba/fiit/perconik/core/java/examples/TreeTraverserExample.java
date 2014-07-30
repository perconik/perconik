package sk.stuba.fiit.perconik.core.java.examples;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import com.google.common.collect.TreeTraverser;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import sk.stuba.fiit.perconik.core.java.dom.NodeCollectors;
import sk.stuba.fiit.perconik.core.java.dom.NodePathExtractor;
import sk.stuba.fiit.perconik.core.java.dom.NodePaths;
import sk.stuba.fiit.perconik.core.java.dom.Nodes;
import sk.stuba.fiit.perconik.core.java.dom.TreeParsers;
import sk.stuba.fiit.perconik.core.java.dom.traverse.CachedTraverser;

import static java.lang.System.out;

import static com.google.common.collect.Lists.newArrayList;

public class TreeTraverserExample {
  public static void main(String[] args) throws IOException {
    CompilationUnit u = (CompilationUnit) TreeParsers.parse(Paths.get("fixtures/HashMap.java"));

    TreeTraverser<ASTNode> t = CachedTraverser.create(u);

    out.println((Nodes.isProblematicTree(u) ? "" : "not") + " problematic compilation unit");
    out.println();

    ASTNode n = NodeCollectors.ofClass(MethodDeclaration.class).apply(u).get(10);

    out.println(n);
    out.println();
    out.println(Nodes.children(n).size() + " children");
    out.println(Nodes.descendants(n).size() + " descendants");
    out.println();

    List<ASTNode> pr = newArrayList(t.preOrderTraversal(n));
    List<ASTNode> po = newArrayList(t.postOrderTraversal(n));
    List<ASTNode> br = newArrayList(t.breadthFirstTraversal(n));

    NodePathExtractor<ASTNode> e = NodePaths.typePathExtractor();

    String r = Paths.get(e.apply(pr.get(0)).getName(0).toString(), e.apply(pr.get(0)).getName(1).toString()).toString() + e.apply(pr.get(0)).getFileSystem().getSeparator();

    out.printf("%-120s %-120s %-120s%n%n", "PRE-ORDER", "POST-ORDER", "BREADTH-FIRST");

    for (int i = 0; i < br.size(); i ++) {
      String pre = e.apply(pr.get(i)).toString().replace(r, "");
      String poe = e.apply(po.get(i)).toString().replace(r, "");
      String bre = e.apply(br.get(i)).toString().replace(r, "");

      out.printf("%-120s %-120s %-120s%n", pre, poe, bre);
    }
  }
}
