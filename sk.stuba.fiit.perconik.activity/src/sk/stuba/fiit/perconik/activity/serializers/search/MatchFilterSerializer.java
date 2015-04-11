package sk.stuba.fiit.perconik.activity.serializers.search;

import java.util.Set;

import org.eclipse.search.ui.text.MatchFilter;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class MatchFilterSerializer extends AbstractMatchFilterSerializer<MatchFilter> {
  public MatchFilterSerializer(final Option ... options) {
    super(options);
  }

  public MatchFilterSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putMatchFilter(final StructuredContent content, final MatchFilter filter, @SuppressWarnings("unused") final Set<Option> options) {
    content.put(key("identifier"), filter.getID());

    content.put(key("name"), filter.getName());
    content.put(key("description"), filter.getDescription());

    content.put(key("label"), filter.getActionLabel());
  }
}
