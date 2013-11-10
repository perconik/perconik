package sk.stuba.fiit.perconik.core.java.examples;

import static java.lang.System.out;
import java.io.File;
import org.eclipse.jdt.core.dom.CompilationUnit;
import sk.stuba.fiit.perconik.core.java.dom.NodeCounters;
import sk.stuba.fiit.perconik.core.java.dom.TreeParsers;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class StandardCountersExample
{
	public static void main(String[] args) throws Exception
	{
		// TODO
		
		String source = Files.toString(new File("fixtures/HashMap.java"), Charsets.UTF_8);
		
		CompilationUnit u = (CompilationUnit) TreeParsers.parse(source);
		
//		IFile file = Workspaces.getWorkspace().getRoot().getFile(new Path("fixtures/HashMap.java"));
//		
//		ICompilationUnit unit = JavaCore.createCompilationUnitFrom(file);
//		
//		CompilationUnit u = (CompilationUnit) TreeParsers.parse(unit);
		
		out.println(NodeCounters.nodes().apply(u) + " nodes");
		out.println(NodeCounters.lines().apply(u) + " lines (" + source.split("\r?\n|\r").length + ")");
		out.println(NodeCounters.characters().apply(u) + " characters (" + source.length() + ")");
		out.println(NodeCounters.memory().apply(u) + " bytes in memory");
	}
}
