package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import sk.stuba.fiit.perconik.utilities.Exceptional;
import com.google.common.reflect.TypeToken;

public final class Accessors
{
	private Accessors()
	{
		throw new AssertionError();
	}
	
	public static final <T> Exceptional<Accessor<T>> ofClassConstant(Class<?> implementation, Class<T> type, String name)
	{
		return ofClassConstant(implementation, TypeToken.of(type), name);
	}
	
	public static final <T> Exceptional<Accessor<T>> ofClassConstant(Class<?> implementation, TypeToken<T> type, String name)
	{
		try
		{
			return Exceptional.of(StaticAccessor.ofClassConstant(implementation, type, name));
		}
		catch (Exception e)
		{
			return Exceptional.failure(e);
		}
	}
	
	public static final <T> Exceptional<Accessor<T>> ofClassField(Class<?> implementation, Class<T> type, String name)
	{
		return ofClassField(implementation, TypeToken.of(type), name);
	}

	public static final <T> Exceptional<Accessor<T>> ofClassField(Class<?> implementation, TypeToken<T> type, String name)
	{
		try
		{
			return Exceptional.of(StaticAccessor.ofClassField(implementation, type, name));
		}
		catch (Exception e)
		{
			return Exceptional.failure(e);
		}
	}

	public static final <T> Exceptional<Accessor<T>> ofClassConstructor(Class<T> type, Object ... arguments)
	{
		return ofClassConstructor(TypeToken.of(type), arguments);
	}
	
	public static final <T> Exceptional<Accessor<T>> ofClassConstructor(TypeToken<T> type, Object ... arguments)
	{
		try
		{
			return Exceptional.of(StaticAccessor.ofClassConstructor(type, arguments));
		}
		catch (Exception e)
		{
			return Exceptional.failure(e);
		}
	}

	public static final <T> Exceptional<Accessor<T>> ofClassMethod(Class<?> implementation, Class<T> type, String name, Object ... arguments)
	{
		return ofClassMethod(implementation, TypeToken.of(type), name, arguments);
	}
	
	public static final <T> Exceptional<Accessor<T>> ofClassMethod(Class<?> implementation, TypeToken<T> type, String name, Object ... arguments)
	{
		try
		{
			return Exceptional.of(StaticAccessor.ofClassMethod(implementation, type, name, arguments));
		}
		catch (Exception e)
		{
			return Exceptional.failure(e);
		}
	}
	
	public static final <T> Exceptional<Accessor<T>> ofEnumConstant(Class<T> type, String name)
	{
		return ofEnumConstant(TypeToken.of(type), name);
	}

	public static final <T> Exceptional<Accessor<T>> ofEnumConstant(TypeToken<T> type, String name)
	{
		try
		{
			return Exceptional.of(StaticAccessor.ofEnumConstant(type, name));
		}
		catch (Exception e)
		{
			return Exceptional.failure(e);
		}
	}
	
	public static final <T> Exceptional<Accessor<T>> ofInstanceConstant(Object instance, Class<T> type, String name)
	{
		return ofInstanceConstant(instance, TypeToken.of(type), name);
	}
	
	public static final <T> Exceptional<Accessor<T>> ofInstanceConstant(Object instance, TypeToken<T> type, String name)
	{
		try
		{
			return Exceptional.of(DynamicAccessor.ofInstanceConstant(instance, type, name));
		}
		catch (Exception e)
		{
			return Exceptional.failure(e);
		}
	}
	
	public static final <T> Exceptional<Accessor<T>> ofInstanceField(Object instance, Class<T> type, String name)
	{
		return ofInstanceField(instance, TypeToken.of(type), name);
	}

	public static final <T> Exceptional<Accessor<T>> ofInstanceField(Object instance, TypeToken<T> type, String name)
	{
		try
		{
			return Exceptional.of(DynamicAccessor.ofInstanceField(instance, type, name));
		}
		catch (Exception e)
		{
			return Exceptional.failure(e);
		}
	}

	public static final <T> Exceptional<Accessor<T>> ofInstanceMethod(Object instance, Class<T> type, String name)
	{
		return ofInstanceMethod(instance, TypeToken.of(type), name);
	}
	
	public static final <T> Exceptional<Accessor<T>> ofInstanceMethod(Object instance, TypeToken<T> type, String name, Object ... arguments)
	{
		try
		{
			return Exceptional.of(DynamicAccessor.ofInstanceMethod(instance, type, name, arguments));
		}
		catch (Exception e)
		{
			return Exceptional.failure(e);
		}
	}
}
