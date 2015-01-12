package sk.stuba.fiit.perconik.utilities.reflect.resolver;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newLinkedList;

import static sk.stuba.fiit.perconik.utilities.MoreThrowables.initializeSuppressor;

final class CompositeClassResolver implements ClassResolver {
  private final List<ClassResolver> resolvers;

  CompositeClassResolver(final Iterable<ClassResolver> resolvers) {
    this.resolvers = ImmutableList.copyOf(resolvers);

    checkArgument(!this.resolvers.isEmpty());
  }

  public Class<?> forName(final String name) throws ClassNotFoundException {
    List<Throwable> suppressions = newLinkedList();

    for (ClassResolver resolver: this.resolvers) {
      try {
        return resolver.forName(name);
      } catch (Exception e) {
        suppressions.add(e);
      }
    }

    ClassNotFoundException failure = new ClassNotFoundException(name + " not found");

    throw initializeSuppressor(failure, Lists.reverse(suppressions));
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("CompositeClassResolver(");

    Joiner.on(",").appendTo(builder, this.resolvers);

    return builder.append(")").toString();
  }
}
