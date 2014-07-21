package sk.stuba.fiit.perconik.core.java.examples;

import java.io.File;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

import sk.stuba.fiit.perconik.core.java.dom.NodeCounters;
import sk.stuba.fiit.perconik.core.java.dom.TreeParsers;
import sk.stuba.fiit.perconik.utilities.MoreStrings;

import static java.lang.System.out;

public class StandardCountersExample
{
	public static void main(String[] args) throws Exception
	{
//		IFile file = Workspaces.getWorkspace().getRoot().getFile(new Path("fixtures/HashMap.java"));
//		
//		ICompilationUnit unit = JavaCore.createCompilationUnitFrom(file);
//		
//		CompilationUnit u = (CompilationUnit) TreeParsers.parse(unit);
		
		String source = Files.toString(new File("fixtures/HashMap.java"), Charsets.UTF_8);
		
		CompilationUnit u = (CompilationUnit) TreeParsers.parse(source);
		
		ASTNode n = u;
		
//		n = NodeCollectors.ofClass(MethodDeclaration.class).apply(u).get(10);
//		
//		out.println(m.getName());
		
		out.println(NodeCounters.nodes().apply(n) + " nodes");
		out.println(NodeCounters.lines(source).apply(n) + " lines (" + MoreStrings.lines(source).size() + ")");
		out.println(NodeCounters.characters().apply(n) + " characters (" + source.length() + ")");
		out.println(NodeCounters.memory().apply(n) + " bytes in memory");
	}
}
