package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.debug.core.IDebugEventSetListener;

import sk.stuba.fiit.perconik.core.Listener;

/**
 * A debug events listener.
 * 
 * @see Listener
 * @see IDebugEventSetListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface DebugEventsListener extends Listener, IDebugEventSetListener {
}
