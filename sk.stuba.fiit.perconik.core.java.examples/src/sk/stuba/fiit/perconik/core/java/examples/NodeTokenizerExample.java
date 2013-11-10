package sk.stuba.fiit.perconik.core.java.examples;

import static java.lang.System.out;
import java.nio.file.Paths;
import java.util.List;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.StringLiteral;
import sk.stuba.fiit.perconik.core.java.dom.NodeCollectors;
import sk.stuba.fiit.perconik.core.java.dom.NodeTokenizer;
import sk.stuba.fiit.perconik.core.java.dom.NodeTokenizers;
import sk.stuba.fiit.perconik.core.java.dom.Tokenizer;
import sk.stuba.fiit.perconik.core.java.dom.TreeParsers;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.NodeType;
import uk.ac.open.crc.intt.IdentifierNameTokeniserFactory;
import com.google.common.base.Function;
import com.google.common.collect.Sets;

public class NodeTokenizerExample
{
	public static void main(String[] args) throws Exception
	{
		CompilationUnit u = (CompilationUnit) TreeParsers.parse(Paths.get("fixtures/HashMap.java"));

		List<String> qn = NodeTokenizers.qualifiedNames().apply(u);
		List<String> sn = NodeTokenizers.simpleNames().apply(u);
		
		IdentifierNameTokeniserFactory cf = new IdentifierNameTokeniserFactory();
		
		cf.setSeparatorCharacters("$._-,;:='\"?!@ |/\\()[]{}<>\n\r\t#%*`");
		
		NodeTokenizer<ASTNode> ct = NodeTokenizer.builder()
		.collector(NodeCollectors.ofType(NodeType.JAVADOC,NodeType.QUALIFIED_NAME, NodeType.SIMPLE_NAME, NodeType.STRING_LITERAL))
		.transformer(new Function<ASTNode, String>()
		{
			public String apply(ASTNode input)
			{
				return input instanceof StringLiteral ? ((StringLiteral) input).getLiteralValue() : input.toString();
			}
			
			@Override
			public String toString()
			{
				return "custom-node-to-string-transformer";
			}
		})
		.tokenizer(Tokenizer.create(cf))
		.build();
		
		List<String> cn = ct.apply(u);
		
		//out.println(NodeTokenizers.qualifiedNames());
		
		out.println(ct);
		out.println();
		
		out.println("lists:");
		out.println("  qualified names: " + qn.size() + " " + qn);
		out.println("  simple names: " + sn.size() + " " + sn);
		out.println("  custom: " + cn.size() + " " + cn);
		out.println();
		
		out.println("sorted sets:");
		out.println("  qualified names: " + Sets.newHashSet(qn).size() + " " + Sets.newTreeSet(qn));
		out.println("  simple names: " + Sets.newHashSet(sn).size() + " "  + Sets.newTreeSet(sn));
		out.println("  custom: " + Sets.newHashSet(cn).size() + " "  + Sets.newTreeSet(cn));
		out.println();
	}
}
