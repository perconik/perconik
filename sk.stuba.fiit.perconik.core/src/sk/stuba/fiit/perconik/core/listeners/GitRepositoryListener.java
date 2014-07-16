package sk.stuba.fiit.perconik.core.listeners;

import sk.stuba.fiit.perconik.core.Listener;

import org.eclipse.egit.core.project.RepositoryChangeListener;

/**
 * A Git repository listener.
 *
 * @see Listener
 * @see RepositoryChangeListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface GitRepositoryListener extends Listener, RepositoryChangeListener
{
}
