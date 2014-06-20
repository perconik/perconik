package sk.stuba.fiit.perconik.core.debug;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;

public interface DebugResource<L extends Listener> extends DebugRegistrable, Resource<L>
{
}
