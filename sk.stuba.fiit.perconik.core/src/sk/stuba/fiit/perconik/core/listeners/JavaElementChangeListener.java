package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jdt.core.IElementChangedListener;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementChangeEventType;

public interface JavaElementChangeListener extends FilteringListener<JavaElementChangeEventType>, IElementChangedListener
{
}
