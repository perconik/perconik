package sk.stuba.fiit.perconik.activity.data.events;

import sk.stuba.fiit.perconik.activity.data.base.AnnotableData;
import sk.stuba.fiit.perconik.activity.data.base.AnnotationData;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotations;

public class EventData extends AnnotableData
{
	protected long time;
	
	protected String action;

	public EventData()
	{
	}
	
	protected EventData(String action)
	{
		if (action == null)
		{
			return;
		}
		
		this.setAnnotations(AnnotationData.of(Annotations.ofClass(this.getClass())));
	}
	
	public static EventData of(String action)
	{
		return new EventData(action);
	}

	public void setTime(long time)
	{
		this.time = time;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	public long getTime()
	{
		return this.time;
	}

	public String getAction()
	{
		return this.action;
	}
}
