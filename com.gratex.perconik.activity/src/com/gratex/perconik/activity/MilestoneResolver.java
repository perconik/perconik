package com.gratex.perconik.activity;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.gratex.perconik.services.vs.EventDto;
import com.gratex.perconik.services.vs.IdeDocumentOperationDto;
import com.gratex.perconik.services.vs.IdeDocumentOperationTypeEnum;

public final class MilestoneResolver implements Predicate<EventDto>
{
	private static final MilestoneResolver instance = new MilestoneResolver();

	private MilestoneResolver()
	{
	}
	
	public static final MilestoneResolver getInstance()
	{
		return instance;
	}

	public final boolean apply(final EventDto data)
	{
		for (Class<?> type: ActivityDefaults.milestoneDataTypes)
		{
			if (type.isInstance(data))
			{
				return true;
			}
		}
		
		if (data instanceof IdeDocumentOperationDto)
		{
			IdeDocumentOperationTypeEnum type = ((IdeDocumentOperationDto) data).getOperationType();
			
			Preconditions.checkState(type != null);
			
			return ActivityDefaults.milestoneDocumentOperationTypes.contains(type);
		}
		
		return false;
	}
}
