package sk.stuba.fiit.perconik.data.bind;

import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class MapperResolver implements ContextResolver<ObjectMapper> {
  public MapperResolver() {
  }

  public ObjectMapper getContext(Class<?> type) {
    return Mapper.getShared();
  }
}
