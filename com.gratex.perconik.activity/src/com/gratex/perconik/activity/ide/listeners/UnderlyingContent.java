package com.gratex.perconik.activity.ide.listeners;

import javax.annotation.Nullable;

import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;

import sk.stuba.fiit.perconik.eclipse.ui.Editors;

import static java.util.Objects.requireNonNull;

/**
 * @deprecated Use {@link sk.stuba.fiit.perconik.eclipse.jdt.ui.UnderlyingView} instead.
 */
@Deprecated
final class UnderlyingContent<R> {
  final IDocument document;

  final UnderlyingResource<R> resource;

  private UnderlyingContent(final IDocument document, final UnderlyingResource<R> resource) {
    this.document = requireNonNull(document);
    this.resource = requireNonNull(resource);
  }

  public static UnderlyingContent<?> from(@Nullable final IEditorPart editor) {
    IDocument document = Editors.getDocument(editor);

    UnderlyingResource<?> resource = UnderlyingResource.from(editor);

    if (document == null || resource == null) {
      return null;
    }

    return new UnderlyingContent<>(document, resource);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof UnderlyingContent)) {
      return false;
    }

    UnderlyingContent<?> other = (UnderlyingContent<?>) o;

    return this.document.equals(other.document) && this.resource.equals(other.resource);
  }

  @Override
  public int hashCode() {
    return this.document.hashCode() ^ this.resource.hashCode();
  }

  public IDocument getDocument() {
    return this.document;
  }

  public UnderlyingResource<R> getResource() {
    return this.resource;
  }
}
