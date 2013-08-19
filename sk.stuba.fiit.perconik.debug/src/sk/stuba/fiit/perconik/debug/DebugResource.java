package sk.stuba.fiit.perconik.debug;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;

public interface DebugResource<L extends Listener> extends DebugObject, Resource<L>
{
}
