package com.gratex.perconik.activity.ide.listeners;

import javax.annotation.Nullable;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;

final class Utilities
{
	private Utilities()
	{
		throw new AssertionError();
	}

	/**
	 * Alias for {@code System.currentTimeMillis()}.
	 */
	static final long currentTime()
	{
		return System.currentTimeMillis();
	}

	/**
	 * Returns the editor referenced by this object
	 * or {@code null} if the editor was not instantiated.
	 */
	static final IEditorPart dereferenceEditor(final IEditorReference reference)
	{
		return reference.getEditor(true);
	}

	/**
	 * Helper method to avoid compiler {@code null} warnings.
	 */
	static final boolean isNull(@Nullable Object object)
	{
		return object == null;
	}
}
