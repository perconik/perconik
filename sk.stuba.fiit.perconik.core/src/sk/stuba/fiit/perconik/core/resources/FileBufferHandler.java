package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.core.filebuffers.FileBuffers;

import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;

enum FileBufferHandler implements Handler<FileBufferListener> {
  INSTANCE;

  public void register(final FileBufferListener listener) {
    FileBuffers.getTextFileBufferManager().addFileBufferListener(listener);
  }

  public void unregister(final FileBufferListener listener) {
    FileBuffers.getTextFileBufferManager().removeFileBufferListener(listener);
  }
}
