package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.runtime.IPath;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;

/**
 * An abstract adapter class for a {@code FileBufferListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 *
 * <p>Extend this class to create a {@code FileBufferListener}
 * and override the methods for the events of interest.
 *
 * @see Adapter
 * @see FileBufferListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class FileBufferAdapter extends Adapter implements FileBufferListener {
  /**
   * Constructor for use by subclasses.
   */
  protected FileBufferAdapter() {}

  public void bufferCreated(final IFileBuffer buffer) {}

  public void bufferDisposed(final IFileBuffer buffer) {}

  public void bufferContentAboutToBeReplaced(final IFileBuffer buffer) {}

  public void bufferContentReplaced(final IFileBuffer buffer) {}

  public void stateChanging(final IFileBuffer buffer) {}

  public void stateChangeFailed(final IFileBuffer buffer) {}

  public void stateValidationChanged(final IFileBuffer buffer, final boolean stateValidated) {}

  public void dirtyStateChanged(final IFileBuffer buffer, final boolean dirty) {}

  public void underlyingFileMoved(final IFileBuffer buffer, final IPath path) {}

  public void underlyingFileDeleted(final IFileBuffer buffer) {}
}
