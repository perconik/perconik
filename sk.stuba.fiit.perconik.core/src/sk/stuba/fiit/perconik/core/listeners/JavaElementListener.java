package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jdt.core.IElementChangedListener;
import sk.stuba.fiit.perconik.core.FilteringListener;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementEventType;

public interface JavaElementListener extends FilteringListener<JavaElementEventType>, IElementChangedListener
{
}
