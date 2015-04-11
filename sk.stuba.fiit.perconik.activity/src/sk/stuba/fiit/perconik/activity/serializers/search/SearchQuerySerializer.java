package sk.stuba.fiit.perconik.activity.serializers.search;

import java.util.Set;

import org.eclipse.search.ui.ISearchQuery;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class SearchQuerySerializer extends AbstractSearchQuerySerializer<ISearchQuery> {
  public SearchQuerySerializer(final Option ... options) {
    super(options);
  }

  public SearchQuerySerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putSearchQuery(final StructuredContent content, final ISearchQuery query, final Set<Option> options) {
    content.put(key("label"), query.getLabel());

    if (options.contains(StandardOption.TREE)) {
      content.put(key("result"), new SearchResultSerializer(options).serialize(query.getSearchResult()));
    }
  }
}
