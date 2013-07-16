package sk.stuba.fiit.perconik.listeners;


public final class Listeners
{
	private Listeners()
	{
		throw new AssertionError();
	}
	
	public static final void register(final LaunchListener listener)
	{
		Resources.getLaunchResource().register(listener);
	}
	
	public static final void unregister(final LaunchListener listener)
	{
		Resources.getLaunchResource().unregister(listener);
	}
	
//	public static final unregisterAll()
//	{
//		
//	}
	
	
	
	
	
	
	
	
	
	
	// TODO rm
	
//	private static final class Pool
//	{
//		private final Object lock;
//		
//		private final List<Logger> loggers;
//		
//		Pool()
//		{
//			this.lock    = new Object();
//			this.loggers = Lists.newLinkedList();
//		}
//		
//		private final void internalRegister(final Logger logger, final Set<ElementChangedEventType> types)
//		{
//			logger.preRegister();
//			
//			boolean present = false;
//			
//			for (Logger other: this.loggers)
//			{
//				if (logger == other)
//				{
//					present = true;
//					
//					break;
//				}
//			}
//			
//			if (!present)
//			{
//				this.loggers.add(logger);
//			}
//			
//			JavaCore.addElementChangedListener(logger.listener, ElementChangedEventType.valuesAsInteger(types));
//			
//			logger.postRegister();
//		}
//		
//		private final void internalUnregister(final Logger logger)
//		{
//			logger.preUnregister();
//			
//			JavaCore.removeElementChangedListener(logger.listener);
//			
//			this.loggers.remove(logger);
//			
//			logger.postUnregister();
//		}
//		
//		final void register(final Logger logger, final Set<ElementChangedEventType> types)
//		{
//			synchronized (this.lock)
//			{
//				this.internalRegister(logger, types);
//			}
//		}
//		
//		final void unregister(final Logger logger)
//		{
//			synchronized (this.lock)
//			{
//				this.internalUnregister(logger);
//			}
//		}
//		
//		final void unregisterAll()
//		{
//			synchronized (this.lock)
//			{
//				for (Logger logger: Lists.newArrayList(this.loggers))
//				{
//					this.internalUnregister(logger);
//				}
//			}
//		}
//	}
//	
//	public static final void register(final Logger logger)
//	{
//		register(logger, EnumSet.allOf(ElementChangedEventType.class));
//	}
//
//	public static final void register(final Logger logger, final Set<ElementChangedEventType> types)
//	{
//		pool.register(logger, types);
//	}
//
//	public static final void unregister(final Logger logger)
//	{
//		pool.remove(logger);
//	}
//	
//	public static final void unregisterAll()
//	{
//		pool.unregisterAll();
//	}
}
