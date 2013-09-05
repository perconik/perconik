package sk.stuba.fiit.perconik.utilities.reflection;

import static sk.stuba.fiit.perconik.utilities.reflection.Utilities.checkArgument;
import static sk.stuba.fiit.perconik.utilities.reflection.Utilities.createArgument;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import com.google.common.annotations.Beta;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

@Beta
public abstract class StaticAccessor<T> extends AbstractAccessor<T>
{
	StaticAccessor(final TypeToken<T> token)
	{
		super(token);
	}
	
	public static final <T> Accessor<T> ofClassConstant(Class<?> implementation, TypeToken<T> type, String name) throws IllegalAccessException, NoSuchFieldException
	{
		Field field = implementation.getField(name);
		
		int modifiers = field.getModifiers();
		
		checkArgument(Modifier.isStatic(modifiers), "Field %s of %s is not static", name, implementation);
		checkArgument(Modifier.isFinal(modifiers), "Field %s of %s is not final", name, implementation);
		checkArgument(type.equals(TypeToken.of(field.getGenericType())), "Field %s of %s is not static", name, implementation);
		
		return new ClassConstant<>(type, (T) type.getRawType().cast(field.get(null)));
	}

	public static final <T> Accessor<T> ofClassField(Class<?> implementation, TypeToken<T> type, String name) throws NoSuchFieldException
	{
		Field field = implementation.getField(name);
		
		int modifiers = field.getModifiers();
		
		checkArgument(field.isAccessible(), "Field %s of %s is not accessible", name, implementation);
		checkArgument(Modifier.isStatic(modifiers), "Field %s of %s is not static", name, implementation);
		checkArgument(type.equals(TypeToken.of(field.getGenericType())), "Field %s of %s is not static", name, implementation);
		
		return new ClassField<>(type, field);
	}

	public static final <T> Accessor<T> ofClassConstructor(Class<T> type) throws NoSuchMethodException
	{
		Constructor<T> constructor = type.getConstructor();
		
		return new ClassConstructor<>(TypeToken.of(type), Invokable.from(constructor));
	}

	public static final <T> Accessor<T> ofClassMethod(Class<?> implementation, TypeToken<T> type, String name) throws NoSuchMethodException
	{
		Invokable<?, Object> method = Invokable.from(implementation.getMethod(name));
		
		checkArgument(method.isAccessible(), "Method %s of %s is not accessible", name, implementation);
		checkArgument(method.isStatic(), "Method %s of %s is not static", name, implementation);
		
		return new ClassMethod<>(type, method.returning(type));
	}

	public static final <T> Accessor<T> ofEnumConstant(Class<T> type, String name)
	{
		checkArgument(Enum.class.isAssignableFrom(type), "Class %s is not an enum", type);
		
		T[] constants = type.getEnumConstants();
		
		for (T constant: constants)
		{
			if (name.equals(Enum.class.cast(constant).name()))
			{
				return new EnumConstant<>(TypeToken.of(type), constant);
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
			super(type, field);
		}
	}

	private static final class ClassConstructor<T> extends InvokableAccessor<T>
	{
		ClassConstructor(TypeToken<T> type, Invokable<T, T> constructor)
		{
			super(type, constructor);
		}
	}
	
	private static final class ClassMethod<T> extends InvokableAccessor<T>
	{
		ClassMethod(TypeToken<T> type, Invokable<?, T> method)
		{
			super(type, method);
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
