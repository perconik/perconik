package sk.stuba.fiit.perconik.activity.serializers.git;

import java.util.TimeZone;

import org.eclipse.jgit.lib.PersonIdent;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class IdentitySerializer extends AbstractConfigurableMultiSerializer<PersonIdent> {
  public IdentitySerializer(final Option ... options) {
    super(options);
  }

  public IdentitySerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putIdentity(final StructuredContent content, final PersonIdent identity) {
    content.put(key("email"), identity.getEmailAddress());
    content.put(key("name"), identity.getName());
    content.put(key("timestamp"), identity.getWhen().getTime());

    content.put(key("timezone", "offset"), identity.getTimeZoneOffset());

    TimeZone zone = identity.getTimeZone();

    if (zone != null) {
      content.put(key("timezone", "identifier"), zone.getID());
    }
  }

  @Override
  protected void put(final StructuredContent content, final PersonIdent identity) {
    putIdentity(content, identity);
  }
}
