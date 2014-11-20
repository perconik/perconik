package sk.stuba.fiit.perconik.eclipse.jdt.ui;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ui.IEditorPart;

import sk.stuba.fiit.perconik.eclipse.jdt.core.ClassFiles;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

public abstract class UnderlyingResource<R> {
  private final String identifier;

  final R raw;

  final IPath path;

  final IFile file;

  UnderlyingResource(final String identifier, final R raw, @Nullable final IPath path, @Nullable final IFile file) {
    assert identifier != null && raw != null;

    this.identifier = identifier;

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
    return File.create(raw);
  }

  private static final class ClassFile extends UnderlyingResource<IClassFile> {
    private ClassFile(final String identifier, final IClassFile raw, @Nullable final IPath path, @Nullable final IFile file) {
      super(identifier, raw, path, file);
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

  private static final class File extends UnderlyingResource<IFile> {
    private File(final IFile raw, final IPath path) {
      super(path.toString(), raw, path, raw);
    }

    static File create(final IFile raw) {
      return new File(raw, raw.getFullPath().makeRelative());
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

    return this.identifier.equals(((UnderlyingResource<?>) o).identifier);
  }

  @Override
  public final int hashCode() {
    return this.identifier.hashCode();
  }

  public abstract R getRaw();

  public abstract Optional<IPath> getPath();

  public abstract Optional<IFile> getFile();
}
