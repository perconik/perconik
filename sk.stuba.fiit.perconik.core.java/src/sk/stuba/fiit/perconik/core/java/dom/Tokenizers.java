package sk.stuba.fiit.perconik.core.java.dom;

import uk.ac.open.crc.intt.IdentifierNameTokeniserFactory;

final class Tokenizers
{
	static final Tokenizer shared = Tokenizer.create(new IdentifierNameTokeniserFactory());
	
	private Tokenizers()
	{
		throw new AssertionError();
	}
}
