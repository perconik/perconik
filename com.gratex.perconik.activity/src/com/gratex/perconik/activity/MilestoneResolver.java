package com.gratex.perconik.activity;

import com.google.common.base.Preconditions;
import com.gratex.perconik.services.activity.EventDto;
import com.gratex.perconik.services.activity.IdeDocumentOperationDto;
import com.gratex.perconik.services.activity.IdeDocumentOperationTypeEnum;

public final class MilestoneResolver
{
	private static final MilestoneResolver instance = new MilestoneResolver();

	private MilestoneResolver()
	{
	}
	
	public static final MilestoneResolver getInstance()
	{
		return instance;
	}

	@SuppressWarnings("static-method")
	public final boolean isMilestone(final EventDto data)
	{
		return true; // TODO REMOVE AFTER TESTS
//		
//		for (Class<?> type: ActivityDefaults.milestoneDataTypes)
//		{
//			if (type.isInstance(data))
//			{
//				return true;
//			}
//		}
//		
//		if (data instanceof IdeDocumentOperationDto)
//		{
//			IdeDocumentOperationTypeEnum type = ((IdeDocumentOperationDto) data).getOperationType();
//			
//			Preconditions.checkState(type != null);
//			
//			return ActivityDefaults.milestoneDocumentOperationTypes.contains(type);
//		}
//		
//		return false;
	}
}
