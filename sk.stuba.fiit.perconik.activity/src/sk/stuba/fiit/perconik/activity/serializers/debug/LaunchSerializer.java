package sk.stuba.fiit.perconik.activity.serializers.debug;

import java.util.Set;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.Launch;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class LaunchSerializer extends AbstractConfigurableSerializer<ILaunch> {
  public LaunchSerializer(final Option ... options) {
    super(options);
  }

  public LaunchSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putLaunch(final StructuredContent content, final ILaunch launch, final Set<Option> options) {
    content.put(key("mode"), launch.getLaunchMode());

    content.put(key("debug", "targets"), new DebugTargetSerializer(options).serialize(asList(launch.getDebugTargets())));
    content.put(key("processes"), new ProcessSerializer(options).serialize(asList(launch.getProcesses())));

    content.put(key("canTerminate"), launch.canTerminate());

    content.put(key("isTerminated"), launch.isTerminated());

    if (launch instanceof Launch) {
      Launch other = (Launch) launch;

      content.put(key("canDisconnect"), other.canDisconnect());

      content.put(key("isDisconnected"), other.isDisconnected());
    }
  }

  @Override
  protected void put(final StructuredContent content, final ILaunch launch) {
    putObjectIdentity(content, launch);
    putLaunch(content, launch, this.options);
  }
}
