package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.egit.core.project.RepositoryChangeListener;

import sk.stuba.fiit.perconik.core.Listener;

/**
 * A Git repository listener.
 *
 * @see Listener
 * @see RepositoryChangeListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@SuppressWarnings("restriction")
public interface GitRepositoryListener extends Listener, RepositoryChangeListener
{
}
