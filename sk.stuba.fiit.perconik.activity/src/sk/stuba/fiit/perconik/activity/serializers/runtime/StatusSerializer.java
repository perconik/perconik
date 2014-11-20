package sk.stuba.fiit.perconik.activity.serializers.runtime;

import java.util.Set;

import org.eclipse.core.runtime.IStatus;

import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.eclipse.core.runtime.StatusSeverity;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class StatusSerializer extends AbstractStatusSerializer<IStatus> {
  public StatusSerializer(final Option ... options) {
    super(options);
  }

  public StatusSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putStatus(final StructuredContent content, final IStatus status, final Set<Option> options) {
    content.put(key("plugin"), status.getPlugin());
    content.put(key("code"), status.getCode());

    content.put(key("severity"), StatusSeverity.valueOf(status.getSeverity()).toString().toLowerCase());
    content.put(key("message"), status.getMessage());

    content.put(key("exception"), identifyObject(status.getException()));

    if (options.contains(StandardOption.TREE)) {
      content.put(key("children"), new StatusSerializer(options).serialize(asList(status.getChildren())));
    }
  }
}
