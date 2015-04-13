package sk.stuba.fiit.perconik.activity.serializers.search;

import java.util.List;
import java.util.Set;

import org.eclipse.search.ui.ISearchResult;
import org.eclipse.search.ui.text.AbstractTextSearchResult;
import org.eclipse.search.ui.text.IFileMatchAdapter;
import org.eclipse.search.ui.text.MatchFilter;

import sk.stuba.fiit.perconik.activity.serializers.resource.FileSerializer;
import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static java.util.Arrays.asList;

import static com.google.common.collect.Lists.newArrayListWithExpectedSize;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.newStructuredContent;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class SearchResultSerializer extends AbstractSearchResultSerializer<ISearchResult> {
  public SearchResultSerializer(final Option ... options) {
    super(options);
  }

  public SearchResultSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putSearchResult(final StructuredContent content, final ISearchResult result, final Set<Option> options) {
    content.put(key("label"), result.getLabel());

    content.put(key("tooltip"), result.getTooltip());

    if (options.contains(StandardOption.TREE)) {
      content.put(key("query"), identifyObject(result.getQuery()));
    }

    if (result instanceof AbstractTextSearchResult) {
      AbstractTextSearchResult text = (AbstractTextSearchResult) result;

      MatchFilter[] applicable = text.getAllMatchFilters();

      if (applicable != null) {
        content.put(key("filters", "applicable"), new MatchFilterSerializer(options).serialize(asList(applicable)));
      }

      MatchFilter[] active = text.getActiveMatchFilters();

      if (active != null) {
        content.put(key("filters", "active"), new MatchFilterSerializer(options).serialize(asList(active)));
      }

      IFileMatchAdapter adapter = text.getFileMatchAdapter();

      List<Content> elements = newArrayListWithExpectedSize(128);

      for (Object element: text.getElements()) {
        StructuredContent elementContent = newStructuredContent();

        elementContent.put(key("matches"), new MatchSerializer(options).serialize(asList(text.getMatches(element))));

        if (adapter != null) {
          elementContent.put(key("file"), new FileSerializer(options).serialize(adapter.getFile(element)));
        }

        elements.add(elementContent);
      }

      content.put(key("elements"), elements);
    }
  }
}
