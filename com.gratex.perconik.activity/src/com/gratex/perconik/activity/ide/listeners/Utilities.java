package com.gratex.perconik.activity.ide.listeners;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;

final class Utilities
{
	private Utilities()
	{
		throw new AssertionError();
	}

	static final long currentTime()
	{
		return System.currentTimeMillis();
	}

	static final IEditorPart dereferenceEditor(final IEditorReference reference)
	{
		return reference.getEditor(false);
	}
}
