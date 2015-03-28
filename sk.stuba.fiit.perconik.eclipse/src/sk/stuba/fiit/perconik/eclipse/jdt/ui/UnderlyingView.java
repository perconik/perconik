package sk.stuba.fiit.perconik.eclipse.jdt.ui;

import javax.annotation.Nullable;

import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;

import sk.stuba.fiit.perconik.eclipse.ui.Editors;

import static com.google.common.base.Preconditions.checkNotNull;

public final class UnderlyingView<R> {
  private final IDocument document;

  private final UnderlyingResource<R> resource;

  private UnderlyingView(final IDocument document, final UnderlyingResource<R> resource) {
    this.document = checkNotNull(document);
    this.resource = checkNotNull(resource);
  }

  public static UnderlyingView<?> from(@Nullable final IEditorPart editor) {
    IDocument document = Editors.getDocument(editor);

    if (document == null) {
      return null;
    }

    return resolve(document, editor);
  }

  public static UnderlyingView<?> resolve(final IDocument document) {
    return resolve(document, Editors.forDocument(document));
  }

  public static UnderlyingView<?> resolve(final IDocument document, final IEditorPart editor) {
    UnderlyingResource<?> resource = UnderlyingResource.from(editor);

    if (resource == null) {
      return null;
    }

    return of(document, resource);
  }

  public static UnderlyingView<?> resolve(final IDocument document, @Nullable final Object raw) {
    if (raw == null) {
      return null;
    }

    return of(document, UnderlyingResource.resolve(raw));
  }

  public static <R> UnderlyingView<R> of(final IDocument document, final UnderlyingResource<R> resource) {
    return new UnderlyingView<>(document, resource);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof UnderlyingView)) {
      return false;
    }

    UnderlyingView<?> other = (UnderlyingView<?>) o;

    return this.document.equals(other.document) && this.resource.equals(other.resource);
  }

  @Override
  public int hashCode() {
    return 31 * (31 * this.document.hashCode()) + this.resource.hashCode();
  }

  public IDocument getDocument() {
    return this.document;
  }

  public UnderlyingResource<R> getResource() {
    return this.resource;
  }
}
