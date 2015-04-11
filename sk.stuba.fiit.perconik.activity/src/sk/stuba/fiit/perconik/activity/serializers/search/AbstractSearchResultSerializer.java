package sk.stuba.fiit.perconik.activity.serializers.search;

import org.eclipse.search.ui.ISearchResult;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.activity.serializers.search.SearchResultSerializer.putSearchResult;

abstract class AbstractSearchResultSerializer<T extends ISearchResult> extends AbstractConfigurableMultiSerializer<T> {
  AbstractSearchResultSerializer(final Option ... options) {
    super(options);
  }

  AbstractSearchResultSerializer(final Iterable<Option> options) {
    super(options);
  }

  @Override
  protected final void put(final StructuredContent content, final T result) {
    putObjectIdentity(content, result);
    putSearchResult(content, result, this.options);
  }
}
