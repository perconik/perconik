package sk.stuba.fiit.perconik.activity.serializers.search;

import org.eclipse.search.ui.ISearchQuery;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.activity.serializers.search.SearchQuerySerializer.putSearchQuery;

abstract class AbstractSearchQuerySerializer<T extends ISearchQuery> extends AbstractConfigurableMultiSerializer<T> {
  AbstractSearchQuerySerializer(final Option ... options) {
    super(options);
  }

  AbstractSearchQuerySerializer(final Iterable<Option> options) {
    super(options);
  }

  @Override
  protected final void put(final StructuredContent content, final T query) {
    putObjectIdentity(content, query);
    putSearchQuery(content, query, this.options);
  }
}
