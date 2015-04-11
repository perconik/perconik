package sk.stuba.fiit.perconik.activity.serializers.search;

import org.eclipse.search.ui.text.Match;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.activity.serializers.search.MatchSerializer.putMatch;

abstract class AbstractMatchSerializer<T extends Match> extends AbstractConfigurableMultiSerializer<T> {
  AbstractMatchSerializer(final Option ... options) {
    super(options);
  }

  AbstractMatchSerializer(final Iterable<Option> options) {
    super(options);
  }

  @Override
  protected final void put(final StructuredContent content, final T match) {
    putObjectIdentity(content, match);
    putMatch(content, match, this.options);
  }
}
