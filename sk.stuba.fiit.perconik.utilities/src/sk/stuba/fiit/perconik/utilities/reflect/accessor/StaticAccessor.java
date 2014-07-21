package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

import static sk.stuba.fiit.perconik.utilities.reflect.accessor.Utilities.checkArgument;
import static sk.stuba.fiit.perconik.utilities.reflect.accessor.Utilities.createArgument;
import static sk.stuba.fiit.perconik.utilities.reflect.accessor.Utilities.specialize;

public abstract class StaticAccessor<T> extends AbstractAccessor<T>
{
	StaticAccessor(final TypeToken<T> token)
	{
		super(token);
	}
	
	public static final <T> Accessor<T> ofClassConstant(Class<?> implementation, Class<T> type, String name) throws IllegalAccessException, NoSuchFieldException
	{
		return ofClassConstant(implementation, TypeToken.of(type), name);
	}
	
	public static final <T> Accessor<T> ofClassConstant(Class<?> implementation, TypeToken<T> type, String name) throws IllegalAccessException, NoSuchFieldException
	{
		Field field = implementation.getField(name);
		
		int modifiers = field.getModifiers();
		
		checkArgument(Modifier.isStatic(modifiers), "Field %s of %s is not static", name, implementation);
		checkArgument(Modifier.isFinal(modifiers), "Field %s of %s is not final", name, implementation);
		checkArgument(type.equals(TypeToken.of(field.getGenericType())), "Field %s of %s has incorrect type", name, implementation);
		
		return new ClassConstant<>(type, (T) type.getRawType().cast(field.get(null)));
	}
	
	public static final <T> Accessor<T> ofClassField(Class<?> implementation, Class<T> type, String name) throws NoSuchFieldException
	{
		return ofClassField(implementation, TypeToken.of(type), name);
	}

	public static final <T> Accessor<T> ofClassField(Class<?> implementation, TypeToken<T> type, String name) throws NoSuchFieldException
	{
		Field field = implementation.getField(name);
		
		int modifiers = field.getModifiers();
		
		checkArgument(Modifier.isStatic(modifiers), "Field %s of %s is not static", name, implementation);
		checkArgument(type.equals(TypeToken.of(field.getGenericType())), "Field %s of %s has incorrect type", name, implementation);
		
		return new ClassField<>(type, field);
	}

	public static final <T> Accessor<T> ofClassConstructor(Class<T> type, Object ... arguments) throws NoSuchMethodException
	{
		return ofClassConstructor(TypeToken.of(type), arguments);
	}
	
	public static final <T> Accessor<T> ofClassConstructor(TypeToken<T> type, Object ... arguments) throws NoSuchMethodException
	{
		Constructor<T> constructor = (Constructor<T>) type.getRawType().getConstructor();
		
		return new ClassConstructor<>(type, (Invokable<Object, T>) Invokable.from(constructor), arguments);
	}

	public static final <T> Accessor<T> ofClassMethod(Class<?> implementation, Class<T> type, String name, Object ... arguments) throws NoSuchMethodException
	{
		return ofClassMethod(implementation, TypeToken.of(type), name, arguments);
	}
	
	public static final <T> Accessor<T> ofClassMethod(Class<?> implementation, TypeToken<T> type, String name, Object ... arguments) throws NoSuchMethodException
	{
		Invokable<Object, Object> method = (Invokable<Object, Object>) Invokable.from(implementation.getMethod(name));
		
		checkArgument(method.isStatic(), "Method %s of %s is not static", name, implementation);
		
		return new ClassMethod<>(type, specialize(method, type), arguments);
	}
	
	public static final <T> Accessor<T> ofEnumConstant(Class<T> type, String name)
	{
		return ofEnumConstant(TypeToken.of(type), name);
	}

	public static final <T> Accessor<T> ofEnumConstant(TypeToken<T> type, String name)
	{
		Class<T> raw = (Class<T>) type.getRawType();
		
		checkArgument(Enum.class.isAssignableFrom(raw), "Class %s is not an enum", type);
		
		T[] constants = raw.getEnumConstants();
		
		for (T constant: constants)
		{
			if (name.equals(Enum.class.cast(constant).name()))
			{
				return new EnumConstant<>(type, constant);
			}
		}
		
		throw createArgument("Constant %s not found in enum %s", name, type);
	}
	
	private static final class ClassConstant<T> extends ConstantAccessor<T>
	{
		ClassConstant(TypeToken<T> type, T constant)
		{
			super(type, constant);
		}
	}
	
	private static final class ClassField<T> extends FieldAccessor<T>
	{
		ClassField(TypeToken<T> type, Field field)
		{
			super(type, field, null);
		}
	}

	private static final class ClassConstructor<T> extends InvokableAccessor<T>
	{
		ClassConstructor(TypeToken<T> type, Invokable<Object, T> constructor, Object ... arguments)
		{
			super(type, constructor, null, arguments);
		}
	}
	
	private static final class ClassMethod<T> extends InvokableAccessor<T>
	{
		ClassMethod(TypeToken<T> type, Invokable<Object, T> method, Object ... arguments)
		{
			super(type, method, arguments);
		}
	}
	
	private static final class EnumConstant<T> extends ConstantAccessor<T>
	{
		EnumConstant(TypeToken<T> type, T constant)
		{
			super(type, constant);
		}
	}
}
