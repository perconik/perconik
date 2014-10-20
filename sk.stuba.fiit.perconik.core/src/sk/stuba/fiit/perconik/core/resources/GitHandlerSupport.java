package sk.stuba.fiit.perconik.core.resources;

import java.util.Map;

import org.eclipse.jgit.events.ListenerHandle;
import org.eclipse.jgit.events.RepositoryListener;
import org.eclipse.jgit.lib.Repository;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Maps.newHashMap;

final class GitHandlerSupport<L extends RepositoryListener> {
  private final Class<L> type;

  private final Map<L, ListenerHandle> map;

  GitHandlerSupport(final Class<L> type) {
    this.type = checkNotNull(type);
    this.map = newHashMap();
  }

  public void register(final L listener) {
    if (!this.map.containsKey(listener)) {
      ListenerHandle handle = Repository.getGlobalListenerList().addListener(this.type, listener);

      this.map.put(listener, handle);
    }
  }

  public void unregister(final L listener) {
    ListenerHandle handle = this.map.remove(listener);

    if (handle != null) {
      handle.remove();
    }
  }
}
