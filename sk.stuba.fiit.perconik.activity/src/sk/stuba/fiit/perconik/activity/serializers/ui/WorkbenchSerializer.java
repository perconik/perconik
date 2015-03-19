package sk.stuba.fiit.perconik.activity.serializers.ui;

import java.util.Set;

import org.eclipse.ui.IWorkbench;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class WorkbenchSerializer extends AbstractConfigurableMultiSerializer<IWorkbench> {
  public WorkbenchSerializer(final Option ... options) {
    super(options);
  }

  public WorkbenchSerializer(final Iterable<Option> options) {
    super(options);
  }

  private static void putWorkbench(final StructuredContent content, final IWorkbench workbench, final Set<Option> options) {
    content.put(key("isStarting"), workbench.isStarting());
    content.put(key("isClosing"), workbench.isClosing());

    if (options.contains(StandardOption.TREE)) {
      content.put(key("activeWindow"), identifyObject(workbench.getActiveWorkbenchWindow()));
      content.put(key("windows"), new WindowSerializer(options).serialize(asList(workbench.getWorkbenchWindows())));
    }
  }

  @Override
  protected void put(final StructuredContent content, final IWorkbench workbench) {
    putObjectIdentity(content, workbench);
    putWorkbench(content, workbench, this.options);
  }
}
