package com.gratex.perconik.activity.ide.listeners;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ui.IEditorPart;

import com.gratex.perconik.activity.ide.IdeData;
import com.gratex.perconik.services.uaca.ide.IdeCodeEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeDocumentEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeEventData;

import sk.stuba.fiit.perconik.core.java.ClassFiles;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

abstract class UnderlyingResource<R> {
  private final String id;

  final R raw;

  final IPath path;

  final IFile file;

  UnderlyingResource(final String id, final R raw, @Nullable final IPath path, @Nullable final IFile file) {
    assert id != null && raw != null;

    this.id = id;
    this.raw = raw;
    this.path = path;
    this.file = file;
  }

  public static UnderlyingResource<?> from(@Nullable final IEditorPart editor) {
    if (editor == null) {
      return null;
    }

    IFile file = Editors.getFile(editor);

    if (file != null) {
      return of(file);
    }

    IClassFile classFile = (IClassFile) editor.getEditorInput().getAdapter(IClassFile.class);

    if (classFile != null) {
      return of(classFile);
    }

    return null;
  }

  public static UnderlyingResource<?> resolve(@Nullable final Object raw) {
    if (raw instanceof IFile) {
      return of((IFile) raw);
    }

    if (raw instanceof IClassFile) {
      return of((IClassFile) raw);
    }

    return null;
  }

  public static UnderlyingResource<IClassFile> of(final IClassFile raw) {
    return ClassFile.create(raw);
  }

  public static UnderlyingResource<IFile> of(final IFile raw) {
    return DataFile.create(raw);
  }

  private static final class ClassFile extends UnderlyingResource<IClassFile> {
    private ClassFile(final String id, final IClassFile raw, @Nullable final IPath path, @Nullable final IFile file) {
      super(id, raw, path, file);
    }

    static ClassFile create(final IClassFile raw) {
      IFile file;
      IPath path;

      try {
        file = (IFile) raw.getUnderlyingResource();
      } catch (JavaModelException | RuntimeException e) {
        file = null;
      }

      path = ClassFiles.path(raw).makeRelative();

      return new ClassFile(path.toString(), raw, path, file);
    }

    @Override
    void setDocumentData(final IdeCodeEventRequest data) {
      data.setDocument(IdeData.newDocumentData(this.raw));
    }

    @Override
    void setDocumentData(final IdeDocumentEventRequest data) {
      data.setDocument(IdeData.newDocumentData(this.raw));
    }

    @Override
    void setProjectData(final IdeEventData data) {
      IdeData.setProjectData(data, this.raw);
    }

    @Override
    public IClassFile getRaw() {
      return this.raw;
    }

    @Override
    public Optional<IFile> getFile() {
      return Optional.fromNullable(this.file);
    }

    @Override
    public Optional<IPath> getPath() {
      return Optional.fromNullable(this.path);
    }
  }

  private static final class DataFile extends UnderlyingResource<IFile> {
    private DataFile(final IFile raw, final IPath path) {
      super(path.toString(), raw, path, raw);
    }

    static DataFile create(final IFile raw) {
      return new DataFile(raw, raw.getFullPath().makeRelative());
    }

    @Override
    void setDocumentData(final IdeCodeEventRequest data) {
      data.setDocument(IdeData.newDocumentData(this.raw));
    }

    @Override
    void setDocumentData(final IdeDocumentEventRequest data) {
      data.setDocument(IdeData.newDocumentData(this.raw));
    }

    @Override
    void setProjectData(final IdeEventData data) {
      IdeData.setProjectData(data, this.raw);
    }

    @Override
    public IFile getRaw() {
      return this.raw;
    }

    @Override
    public Optional<IFile> getFile() {
      return Optional.of(this.file);
    }

    @Override
    public Optional<IPath> getPath() {
      return Optional.of(this.path);
    }
  }

  @Override
  public final boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof UnderlyingResource)) {
      return false;
    }

    return this.id.equals(((UnderlyingResource<?>) o).id);
  }

  @Override
  public final int hashCode() {
    return this.id.hashCode();
  }

  abstract void setDocumentData(IdeCodeEventRequest data);

  abstract void setDocumentData(IdeDocumentEventRequest data);

  abstract void setProjectData(IdeEventData data);

  public abstract R getRaw();

  public abstract Optional<IPath> getPath();

  public abstract Optional<IFile> getFile();
}
