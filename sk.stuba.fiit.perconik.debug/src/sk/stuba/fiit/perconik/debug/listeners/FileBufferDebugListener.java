package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.runtime.IPath;
import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;
import sk.stuba.fiit.perconik.core.utilities.SmartStringBuilder;

public final class FileBufferDebugListener extends AbstractDebugListener implements FileBufferListener
{
	public FileBufferDebugListener()
	{
	}
	
	public FileBufferDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void bufferCreated(final IFileBuffer buffer)
	{
		this.print("File buffer created:");
		this.printFileBuffer(buffer);
	}

	public final void bufferDisposed(final IFileBuffer buffer)
	{
		this.print("File buffer disposed:");
		this.printFileBuffer(buffer);
	}

	public final void bufferContentAboutToBeReplaced(final IFileBuffer buffer)
	{
		this.print("File buffer content about to be replaced:");
		this.printFileBuffer(buffer);
	}

	public final void bufferContentReplaced(final IFileBuffer buffer)
	{
		this.print("File buffer content replaced:");
		this.printFileBuffer(buffer);
	}

	public final void stateChanging(final IFileBuffer buffer)
	{
		this.print("State changing:");
		this.printFileBuffer(buffer);
	}

	public final void stateChangeFailed(final IFileBuffer buffer)
	{
		this.print("State change failed:");
		this.printFileBuffer(buffer);
	}

	public final void stateValidationChanged(final IFileBuffer buffer, final boolean isStateValidated)
	{
		this.print("State validation changed:");
		this.printFileBuffer(buffer);
	}

	public final void dirtyStateChanged(final IFileBuffer buffer, final boolean isDirty)
	{
		this.print("Dirty state changed:");
		this.printFileBuffer(buffer);
	}

	public final void underlyingFileMoved(final IFileBuffer buffer, final IPath path)
	{
		this.print("Underlying file moved:");
		this.printFileBuffer(buffer);
	}

	public final void underlyingFileDeleted(final IFileBuffer buffer)
	{
		this.print("Underlying file deleted:");
		this.printFileBuffer(buffer);
	}
	
	private final void printFileBuffer(final IFileBuffer buffer)
	{
		this.put(dumpFileBuffer(buffer));
	}
	
	static final String dumpFileBuffer(final IFileBuffer buffer)
	{
		// TODO refactor dump* methods into separate helper class
		
		SmartStringBuilder builder = new SmartStringBuilder().tab();
		
		IPath location = buffer.getLocation();
		
		long modificationStamp = buffer.getModificationStamp();
		
		boolean commitable     = buffer.isCommitable();
		boolean dirty          = buffer.isDirty();
		boolean shared         = buffer.isShared();
		boolean stateValidated = buffer.isStateValidated();
		
		boolean synchronizationContextRequested = buffer.isSynchronizationContextRequested();
		boolean synchronizedWithFileSystem      = buffer.isSynchronized();

		builder.append("location: ").appendln(location);
		
		builder.append("modification stamp: ").appendln(modificationStamp < 0 ? "unknown" : modificationStamp);
		
		builder.append("commitable: ").appendln(commitable);
		builder.append("dirty: ").appendln(dirty);
		builder.append("shared: ").appendln(shared);
		builder.append("state validated: ").appendln(stateValidated);
		
		builder.append("synchronization context requested: ").appendln(synchronizationContextRequested);
		builder.append("synchronized with file system: ").appendln(synchronizedWithFileSystem);
		
		return builder.toString();
	}
}
