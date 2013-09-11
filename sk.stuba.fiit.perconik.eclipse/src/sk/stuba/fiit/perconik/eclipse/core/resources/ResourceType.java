package sk.stuba.fiit.perconik.eclipse.core.resources;

import java.util.Set;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import sk.stuba.fiit.perconik.utilities.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.IntegralConstantSupport;
import sk.stuba.fiit.perconik.utilities.TypeConstant;
import sk.stuba.fiit.perconik.utilities.TypeConstantSupport;

/**
 * Resource types.
 * 
 * @see IResource
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public enum ResourceType implements IntegralConstant, TypeConstant<IResource>
{
	/**
	 * @see IResource#FILE
	 */
	FILE(IResource.FILE, IFile.class),
	
	/**
	 * @see IResource#FOLDER
	 */
	FOLDER(IResource.FOLDER, IFolder.class),
	
	/**
	 * @see IResource#PROJECT
	 */
	PROJECT(IResource.PROJECT, IProject.class),
	
	/**
	 * @see IResource#ROOT
	 */
	ROOT(IResource.ROOT, IWorkspaceRoot.class);
	
	private static final IntegralConstantSupport<ResourceType> integers = IntegralConstantSupport.of(ResourceType.class);

	private static final TypeConstantSupport<ResourceType, IResource> types = TypeConstantSupport.of(ResourceType.class);
	
	private final int value;
	
	private final Class<? extends IResource> type;
	
	private ResourceType(final int value, final Class<? extends IResource> type)
	{
		assert type != null;
		
		this.value = value;
		this.type  = type;
	}
	
	public static final Set<Integer> valuesAsIntegers()
	{
		return integers.getIntegers();
	}
	
	public static final Set<Class<? extends IResource>> valuesAsTypes()
	{
		return types.getTypes();
	}
	
	public static final ResourceType valueOf(final int value)
	{
		return integers.getConstant(value);
	}
	
	public static final ResourceType valueOf(final Class<? extends IResource> type)
	{
		return types.getConstant(type);
	}

	public static final ResourceType valueOf(final IResource resource)
	{
		return valueOf(resource.getClass());
	}

	public final int getValue()
	{
		return this.value;
	}
	
	public final Class<? extends IResource> getType()
	{
		return this.type;
	}
}
