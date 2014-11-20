package sk.stuba.fiit.perconik.activity.serializers.ui;

import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPart2;
import org.eclipse.ui.IWorkbenchPart3;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class PartSerializer extends AbstractPartSerializer<IWorkbenchPart> {
  public PartSerializer(final Option ... options) {
    super(options);
  }

  public PartSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putPart(final StructuredContent content, final IWorkbenchPart part) {
    content.put(key("title"), part.getTitle());
    content.put(key("titleTooltip"), part.getTitleToolTip());

    if (part instanceof IWorkbenchPart2) {
      IWorkbenchPart2 part2 = (IWorkbenchPart2) part;

      content.put(key("name"), part2.getPartName());
      content.put(key("contentDescription"), part2.getContentDescription());

      if (part2 instanceof IWorkbenchPart3) {
        IWorkbenchPart3 part3 = (IWorkbenchPart3) part2;

        content.put(key("properties"), part3.getPartProperties());
      }
    }
  }
}
