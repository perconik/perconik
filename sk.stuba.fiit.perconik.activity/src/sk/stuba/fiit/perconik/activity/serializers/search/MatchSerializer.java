package sk.stuba.fiit.perconik.activity.serializers.search;

import java.util.Set;

import org.eclipse.search.ui.text.Match;

import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.eclipse.search.ui.text.MatchUnit;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.describeObject;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class MatchSerializer extends AbstractMatchSerializer<Match> {
  public MatchSerializer(final Option ... options) {
    super(options);
  }

  public MatchSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putMatch(final StructuredContent content, final Match match, @SuppressWarnings("unused") final Set<Option> options) {
    content.put(key("offset"), match.getOffset());
    content.put(key("length"), match.getLength());

    content.put(key("unit"), MatchUnit.valueOf(match.getBaseUnit()).toString().toLowerCase());

    content.put(key("element"), describeObject(match.getElement()));

    content.put(key("isFiltered"), match.isFiltered());
  }
}
