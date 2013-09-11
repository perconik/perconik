package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jdt.core.IElementChangedListener;
import sk.stuba.fiit.perconik.core.FilteringListener;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementEventType;

/**
 * A Java element listener.
 * 
 * @see FilteringListener
 * @see IElementChangedListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface JavaElementListener extends FilteringListener<JavaElementEventType>, IElementChangedListener
{
}
