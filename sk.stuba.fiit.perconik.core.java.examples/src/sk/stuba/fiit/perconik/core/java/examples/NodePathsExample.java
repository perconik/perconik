package sk.stuba.fiit.perconik.core.java.examples;

import static java.lang.System.out;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import sk.stuba.fiit.perconik.core.java.dom.NodeCollectors;
import sk.stuba.fiit.perconik.core.java.dom.NodePathExtractor;
import sk.stuba.fiit.perconik.core.java.dom.NodePaths;
import sk.stuba.fiit.perconik.core.java.dom.Nodes;
import sk.stuba.fiit.perconik.core.java.dom.TreeParsers;
import com.google.common.base.Function;
import com.google.common.base.Joiner;

public class NodePathsExample
{
	public static void main(String[] args) throws IOException
	{
		CompilationUnit u = (CompilationUnit) TreeParsers.parse(Paths.get("fixtures/NodeClassFilter.java"));
		
		List<MethodDeclaration>   m = NodeCollectors.ofClass(MethodDeclaration.class).apply(u);
		List<VariableDeclaration> v = NodeCollectors.ofClass(VariableDeclaration.class).apply(u);
		
		final NodePathExtractor<ASTNode> pn = NodePaths.namePathExtractor();
		final NodePathExtractor<ASTNode> pt = NodePaths.typePathExtractor();
		final NodePathExtractor<ASTNode> pc = NodePathExtractor.using(new Function<ASTNode, String>()
		{
			public String apply(ASTNode n)
			{
				return "(" + pt.getStrategy().apply(n) + ") " + pn.getStrategy().apply(n);
			}
		});
		
		out.println((Nodes.isProblematicTree(u) ? "" : "not") + " problematic compilation unit");
		out.println(m.size() + " methods");
		out.println();
		
		for (ASTNode n: m)
		{
			out.println(pn.apply(n));
		}

		out.println();
		
		for (ASTNode n: v)
		{
			out.println(Joiner.on(" -> ").join(pc.apply(n)));
		}
	}
}
