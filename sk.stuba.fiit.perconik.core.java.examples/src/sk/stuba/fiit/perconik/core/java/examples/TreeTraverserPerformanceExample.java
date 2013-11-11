package sk.stuba.fiit.perconik.core.java.examples;

import static java.lang.System.out;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import sk.stuba.fiit.perconik.core.java.dom.TreeParsers;
import sk.stuba.fiit.perconik.core.java.dom.traverse.CachedTraverser;
import sk.stuba.fiit.perconik.core.java.dom.traverse.NaiveTraverser;
import com.google.common.base.Stopwatch;
import com.google.common.collect.TreeTraverser;

public class TreeTraverserPerformanceExample
{
	public static void main(String[] args) throws IOException
	{
		Path p = Paths.get("fixtures/HashMap.java");
		
		CompilationUnit ntu = (CompilationUnit) TreeParsers.parse(p);
		CompilationUnit ctu = (CompilationUnit) TreeParsers.parse(p);

		TreeTraverser<ASTNode> nt = NaiveTraverser.create();
		TreeTraverser<ASTNode> ct = CachedTraverser.create(ctu);

		test("cached", ct, ctu);
		test("naive", nt, ntu);
	}
	
	static void test(String s, TreeTraverser<ASTNode> t, ASTNode n)
	{
		Stopwatch w = Stopwatch.createStarted();

		for (ASTNode o: t.preOrderTraversal(n))     { o.getAST(); }
		//for (ASTNode o: t.postOrderTraversal(n))    { o.getAST(); }
		//for (ASTNode o: t.breadthFirstTraversal(n)) { o.getAST(); }
		
		w.stop();
		
		out.println(s + ": " + w.elapsed(TimeUnit.MILLISECONDS) + " millis");
	}
}
