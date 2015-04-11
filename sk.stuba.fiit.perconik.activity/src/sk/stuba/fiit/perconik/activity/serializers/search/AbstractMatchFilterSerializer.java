package sk.stuba.fiit.perconik.activity.serializers.search;

import org.eclipse.search.ui.text.MatchFilter;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.activity.serializers.search.MatchFilterSerializer.putMatchFilter;

abstract class AbstractMatchFilterSerializer<T extends MatchFilter> extends AbstractConfigurableMultiSerializer<T> {
  AbstractMatchFilterSerializer(final Option ... options) {
    super(options);
  }

  AbstractMatchFilterSerializer(final Iterable<Option> options) {
    super(options);
  }

  @Override
  protected final void put(final StructuredContent content, final T filter) {
    putObjectIdentity(content, filter);
    putMatchFilter(content, filter, this.options);
  }
}
