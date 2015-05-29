package sk.stuba.fiit.perconik.data.providers;

import com.fasterxml.jackson.jaxrs.cfg.Annotations;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import sk.stuba.fiit.perconik.data.bind.Mapper;

public class MapperProvider extends JacksonJsonProvider {
  public MapperProvider() {
    super(Mapper.getShared(), new Annotations[] { Annotations.JACKSON });
  }
}
