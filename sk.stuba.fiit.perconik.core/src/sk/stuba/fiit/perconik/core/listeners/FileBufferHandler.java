package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.core.filebuffers.FileBuffers;

enum FileBufferHandler implements Handler<FileBufferListener>
{
	INSTANCE;
	
	public final void add(final FileBufferListener listener)
	{
		FileBuffers.getTextFileBufferManager().addFileBufferListener(listener);
	}

	public final void remove(final FileBufferListener listener)
	{
		FileBuffers.getTextFileBufferManager().removeFileBufferListener(listener);
	}
}
