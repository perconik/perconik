package sk.stuba.fiit.perconik.activity.data.core;

import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.activity.data.NameableTypeData;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.resources.ResourceService;

public class ResourceServiceData extends NameableTypeData {
  protected NameableTypeData provider;

  protected NameableTypeData manager;

  protected Set<String> names;

  protected SetMultimap<String, ResourceData> registrations;

  public ResourceServiceData() {}

  protected ResourceServiceData(final ResourceService service) {
    super(service);

    if (service == null) {
      return;
    }

    this.setProvider(NameableTypeData.of(service.getResourceProvider()));
    this.setManager(NameableTypeData.of(service.getResourceManager()));
    this.setNames(service.getResourceProvider().names());

    SetMultimap<String, ResourceData> registrations = HashMultimap.create();

    for (Entry<Class<? extends Listener>, Resource<?>> entry: service.getResourceManager().registrations().entries()) {
      registrations.put(entry.getKey().getName(), ResourceData.of(entry.getValue()));
    }

    this.setRegistrations(registrations);
  }

  public static ResourceServiceData of(final ResourceService service) {
    return new ResourceServiceData(service);
  }

  public void setProvider(final NameableTypeData provider) {
    this.provider = provider;
  }

  public void setManager(final NameableTypeData manager) {
    this.manager = manager;
  }

  public void setNames(final Set<String> names) {
    this.names = names;
  }

  public void setRegistrations(final SetMultimap<String, ResourceData> registrations) {
    this.registrations = registrations;
  }

  public NameableTypeData getProvider() {
    return this.provider;
  }

  public NameableTypeData getManager() {
    return this.manager;
  }

  public SetMultimap<String, ResourceData> getRegistrations() {
    return this.registrations;
  }

  public Set<String> getNames() {
    return this.names;
  }
}
