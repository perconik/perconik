package sk.stuba.fiit.perconik.eclipse.jface.text;

import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import sk.stuba.fiit.perconik.eclipse.core.runtime.CoreExceptions;

import static com.google.common.base.Throwables.propagate;

/**
 * Static utility methods pertaining to Eclipse documents.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Documents {
  private Documents() {}

  public static IDocument fromFile(final IFile file) {
    return fromPath(file.getFullPath(), LocationKind.IFILE);
  }

  public static IDocument fromFileStore(final IFileStore store) {
    ITextFileBufferManager manager = FileBuffers.getTextFileBufferManager();

    try {
      manager.connectFileStore(store, null);
    } catch (CoreException e) {
      CoreExceptions.propagate(e);
    }

    ITextFileBuffer buffer = manager.getFileStoreTextFileBuffer(store);

    try {
      manager.disconnectFileStore(store, null);
    } catch (CoreException e) {
      CoreExceptions.propagate(e);
    }

    return buffer != null ? buffer.getDocument() : null;
  }

  public static IDocument fromFileBuffer(final IFileBuffer buffer) {
    if (buffer instanceof ITextFileBuffer) {
      return ((ITextFileBuffer) buffer).getDocument();
    }

    IFileStore store = buffer.getFileStore();

    if (store != null) {
      return fromFileStore(store);
    }

    IPath location = buffer.getLocation();

    if (location != null) {
      return fromPath(location, LocationKind.LOCATION);
    }

    return null;
  }

  public static IDocument fromPath(final IPath path, final LocationKind kind) {
    ITextFileBufferManager manager = FileBuffers.getTextFileBufferManager();

    try {
      manager.connect(path, kind, null);
    } catch (CoreException e) {
      CoreExceptions.propagate(e);
    }

    ITextFileBuffer buffer = manager.getTextFileBuffer(path, kind);

    try {
      manager.disconnect(path, kind, null);
    } catch (CoreException e) {
      CoreExceptions.propagate(e);
    }

    return buffer != null ? buffer.getDocument() : null;
  }

  public static String get(final IDocument document, final int offset, final int length) {
    try {
      return document.get(offset, length);
    } catch (BadLocationException e) {
      throw propagate(e);
    }
  }
}
