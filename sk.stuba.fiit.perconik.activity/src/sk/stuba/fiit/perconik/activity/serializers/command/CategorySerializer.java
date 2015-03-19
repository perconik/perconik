package sk.stuba.fiit.perconik.activity.serializers.command;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.common.NotDefinedException;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class CategorySerializer extends AbstractConfigurableMultiSerializer<Category> {
  public CategorySerializer(final Option ... options) {
    super(options);
  }

  public CategorySerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putCategory(final StructuredContent content, final Category category) {
    content.put(key("identifier"), category.getId());

    try {
      content.put(key("name"), category.getName());
      content.put(key("description"), category.getDescription());
    } catch (NotDefinedException ignore) {}

    content.put(key("isDefined"), category.isDefined());
  }

  @Override
  protected void put(final StructuredContent content, final Category category) {
    putObjectIdentity(content, category);
    putCategory(content, category);
  }
}
