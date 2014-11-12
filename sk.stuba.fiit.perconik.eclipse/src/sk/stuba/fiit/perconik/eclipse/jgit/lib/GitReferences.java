package sk.stuba.fiit.perconik.eclipse.jgit.lib;

import java.util.List;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Static utility methods pertaining to Git references.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class GitReferences {
  private GitReferences() {}

  public static List<ObjectId> toIdentifiers(final Iterable<Ref> references) {
    List<ObjectId> identifiers = newArrayList();

    for (Ref reference: references) {
      identifiers.add(reference.getObjectId());
    }

    return identifiers;
  }

  public static List<String> toNames(final Iterable<Ref> references) {
    List<String> identifiers = newArrayList();

    for (Ref reference: references) {
      identifiers.add(reference.getName());
    }

    return identifiers;
  }
}
