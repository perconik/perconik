package com.gratex.perconik.activity.ide;

import java.util.GregorianCalendar;
import java.util.Set;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.environment.Environment;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableSet;
import com.gratex.perconik.activity.MilestoneResolver;
import com.gratex.perconik.activity.TimeSupplier;
import com.gratex.perconik.activity.ide.plugin.Activator;
import com.gratex.perconik.services.uaca.vs.IdeCheckinDto;
import com.gratex.perconik.services.uaca.vs.IdeCodeOperationDto;
import com.gratex.perconik.services.uaca.vs.IdeDocumentOperationDto;
import com.gratex.perconik.services.uaca.vs.IdeDocumentOperationTypeEnum;
import com.gratex.perconik.services.uaca.vs.IdeEventDto;
import com.gratex.perconik.services.uaca.vs.IdeProjectOperationDto;
import com.gratex.perconik.services.uaca.vs.IdeStateChangeDto;

final class Internals
{
	static final PluginConsole console = Activator.getDefault().getConsole();
	
	static final boolean debug = Environment.debug;
	
	static final int unknonwnPid = -1;
		
	static final DatatypeFactory datatypeFactory;
	
	static
	{
		try
		{
			datatypeFactory = DatatypeFactory.newInstance();
		}
		catch (DatatypeConfigurationException e)
		{
			throw Throwables.propagate(e);
		}
	}
	
	static final Set<Class<? extends IdeEventDto>> milestoneDataTypes;

	static final Set<IdeDocumentOperationTypeEnum> milestoneDocumentOperationTypes;

	static final MilestoneResolver<IdeEventDto> milestoneResolver;
	
	static
	{
		milestoneDataTypes = ImmutableSet.of
		(
			IdeStateChangeDto.class,
			IdeProjectOperationDto.class,
			IdeCodeOperationDto.class,
			IdeCheckinDto.class
		);
	
		milestoneDocumentOperationTypes = ImmutableSet.of
		(
			IdeDocumentOperationTypeEnum.ADD,
			IdeDocumentOperationTypeEnum.REMOVE,
			IdeDocumentOperationTypeEnum.SAVE
		);
		
		milestoneResolver = new MilestoneResolver<IdeEventDto>()
		{
			public final boolean isMilestone(IdeEventDto data)
			{
				for (Class<?> type: milestoneDataTypes)
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
					
					return milestoneDocumentOperationTypes.contains(type);
				}
				
				return false;
			}
		};
	}
	
	static final TimeSupplier timeSupplier;
	
	static
	{
		timeSupplier = new TimeSupplier()
		{
			public final XMLGregorianCalendar from(final long time)
			{
				GregorianCalendar calendar = new GregorianCalendar();
				
				calendar.setTimeInMillis(time);
				
				return datatypeFactory.newXMLGregorianCalendar(calendar);
			}
		};
	}
	
	private Internals()
	{
		throw new AssertionError();
	}
	
	static final int pid()
	{
		try
		{
			return Environment.pid();
		}
		catch (RuntimeException e)
		{
			return unknonwnPid;
		}
	}
}
