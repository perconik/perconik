package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jdt.core.IElementChangedListener;
import sk.stuba.fiit.perconik.core.FilteringListener;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementChangeEventType;

public interface JavaElementListener extends FilteringListener<JavaElementChangeEventType>, IElementChangedListener
{
}
