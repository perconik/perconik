package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jdt.core.IElementChangedListener;
import sk.stuba.fiit.perconik.eclipse.jdt.core.ElementChangedEventType;

public interface ElementChangedListener extends FilteringListener<ElementChangedEventType>, IElementChangedListener
{
}
