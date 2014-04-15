package sk.stuba.fiit.perconik.activity.data.type.base;

import java.nio.file.Path;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public final class BaseModule extends SimpleModule
{
    private static final long serialVersionUID = 1L;

    public BaseModule()
    {
        super(PackageVersion.VERSION);

        this.addSerializer(Path.class, ToStringSerializer.instance);
        this.addDeserializer(Path.class, new PathDeserializer());
    }

    @Override
    public final boolean equals(Object o)
    {
        return this == o;
    }

	@Override
	public final int hashCode()
	{
	    return BaseModule.class.hashCode();
	}
}
