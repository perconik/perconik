package com.gratex.perconik.activity.ide.listeners;

import javax.annotation.Nullable;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

final class UnderlyingContent<R>
{
	final IDocument document;
	
	final UnderlyingResource<R> resource;
	
	UnderlyingContent(final IDocument document, final UnderlyingResource<R> resource)
	{
		assert document != null && resource != null;
		
		this.document = document;
		this.resource = resource;
	}
	
	public static final UnderlyingContent<?> from(@Nullable final IEditorPart editor)
	{
		IDocument document = Editors.getDocument(editor);
		
		UnderlyingResource<?> resource = UnderlyingResource.from(editor);
		
		if (document == null || resource == null)
		{
			return null;
		}
		
		return new UnderlyingContent<>(document, resource);
	}
}
