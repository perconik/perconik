package sk.stuba.fiit.perconik.activity.serializers.ui.text;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension4;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class DocumentSerializer extends AbstractConfigurableSerializer<IDocument> {
  public DocumentSerializer(final Option ... options) {
    super(options);
  }

  public DocumentSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putDocument(final StructuredContent content, final IDocument document) {
    content.put(key("length"), document.getLength());
    content.put(key("linesCount"), document.getNumberOfLines());

    if (document instanceof IDocumentExtension4) {
      IDocumentExtension4 extension4 = (IDocumentExtension4) document;

      content.put(key("modificationTimestamp"), extension4.getModificationStamp());
    }
  }

  @Override
  protected void put(final StructuredContent content, final IDocument document) {
    putObjectIdentity(content, document);
    putDocument(content, document);
  }
}
