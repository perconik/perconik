package sk.stuba.fiit.perconik.data.providers;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

import sk.stuba.fiit.perconik.data.bind.Mapper;

@Provider
public class MapperResolver implements ContextResolver<ObjectMapper> {
  public MapperResolver() {}

  public ObjectMapper getContext(final Class<?> type) {
    return Mapper.getShared();
  }
}
