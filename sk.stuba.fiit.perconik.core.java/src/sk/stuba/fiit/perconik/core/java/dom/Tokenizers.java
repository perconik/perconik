package sk.stuba.fiit.perconik.core.java.dom;

import uk.ac.open.crc.intt.IdentifierNameTokeniserFactory;

final class Tokenizers {
  static final Tokenizer shared;

  static {
    IdentifierNameTokeniserFactory factory = new IdentifierNameTokeniserFactory();

    factory.setSeparatorCharacters("`~!@#$%^&*()_+-=[];'\\,./{}:\"|<>? \n\r\t\f");

    shared = Tokenizer.create(factory);
  }

  private Tokenizers() {
    throw new AssertionError();
  }
}
