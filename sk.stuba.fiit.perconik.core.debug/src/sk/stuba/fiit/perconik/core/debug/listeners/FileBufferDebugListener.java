package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.runtime.IPath;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;

public final class FileBufferDebugListener extends AbstractDebugListener implements FileBufferListener {
  public FileBufferDebugListener() {}

  public FileBufferDebugListener(final DebugConsole console) {
    super(console);
  }

  public void bufferCreated(final IFileBuffer buffer) {
    this.printHeader("File buffer created");
    this.printFileBuffer(buffer);
  }

  public void bufferDisposed(final IFileBuffer buffer) {
    this.printHeader("File buffer disposed");
    this.printFileBuffer(buffer);
  }

  public void bufferContentAboutToBeReplaced(final IFileBuffer buffer) {
    this.printHeader("File buffer content about to be replaced");
    this.printFileBuffer(buffer);
  }

  public void bufferContentReplaced(final IFileBuffer buffer) {
    this.printHeader("File buffer content replaced");
    this.printFileBuffer(buffer);
  }

  public void stateChanging(final IFileBuffer buffer) {
    this.printHeader("State changing");
    this.printFileBuffer(buffer);
  }

  public void stateChangeFailed(final IFileBuffer buffer) {
    this.printHeader("State change failed");
    this.printFileBuffer(buffer);
  }

  public void stateValidationChanged(final IFileBuffer buffer, final boolean stateValidated) {
    this.printHeader("State validation changed");
    this.printFileBuffer(buffer);
  }

  public void dirtyStateChanged(final IFileBuffer buffer, final boolean dirty) {
    this.printHeader("Dirty state changed");
    this.printFileBuffer(buffer);
  }

  public void underlyingFileMoved(final IFileBuffer buffer, final IPath path) {
    this.printHeader("Underlying file moved");
    this.printFileBuffer(buffer);
  }

  public void underlyingFileDeleted(final IFileBuffer buffer) {
    this.printHeader("Underlying file deleted");
    this.printFileBuffer(buffer);
  }

  private void printFileBuffer(final IFileBuffer buffer) {
    this.put(Debug.dumpFileBuffer(buffer));
  }
}
