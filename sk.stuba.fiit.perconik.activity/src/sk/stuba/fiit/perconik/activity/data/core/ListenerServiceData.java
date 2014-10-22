package sk.stuba.fiit.perconik.activity.data.core;

import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.activity.data.NameableData;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;

public class ListenerServiceData extends NameableData {
  protected NameableData provider;

  protected NameableData manager;

  protected Set<Class<? extends Listener>> classes;

  protected SetMultimap<String, ListenerData> registrations;

  public ListenerServiceData() {}

  protected ListenerServiceData(final ListenerService service) {
    super(service);

    if (service == null) {
      return;
    }

    this.setProvider(NameableData.of(service.getListenerProvider()));
    this.setManager(NameableData.of(service.getListenerManager()));
    this.setClasses(service.getListenerProvider().classes());

    SetMultimap<String, ListenerData> registrations = HashMultimap.create();

    for (Entry<Resource<?>, Listener> entry: service.getListenerManager().registrations().entries()) {
      registrations.put(entry.getKey().getName(), ListenerData.of(entry.getValue()));
    }

    this.setRegistrations(registrations);
  }

  public static ListenerServiceData of(final ListenerService service) {
    return new ListenerServiceData(service);
  }

  public void setProvider(final NameableData provider) {
    this.provider = provider;
  }

  public void setManager(final NameableData manager) {
    this.manager = manager;
  }

  public void setClasses(final Set<Class<? extends Listener>> classes) {
    this.classes = classes;
  }

  public void setRegistrations(final SetMultimap<String, ListenerData> registrations) {
    this.registrations = registrations;
  }

  public NameableData getProvider() {
    return this.provider;
  }

  public NameableData getManager() {
    return this.manager;
  }

  public SetMultimap<String, ListenerData> getRegistrations() {
    return this.registrations;
  }

  public Set<Class<? extends Listener>> getClasses() {
    return this.classes;
  }
}
