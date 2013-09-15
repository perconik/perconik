package sk.stuba.fiit.perconik.utilities.net;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLStreamHandler;

public final class UniformResources
{
	private UniformResources()
	{
		throw new AssertionError();
	}
	
	public static final URI newUri(String str)
	{
		try
		{
			return new URI(str);
		}
		catch (URISyntaxException e)
		{
			throw new IllegalArgumentException(e);
		}
	}
	
	public static final URI newUri(String scheme, String login, String host, int port, String path, String query, String fragment)
	{
		try
		{
			return new URI(scheme, login, host, port, path, query, fragment);
		}
		catch (URISyntaxException e)
		{
			throw new IllegalArgumentException(e);
		}
	}
	
	public static final URI newUri(String scheme, String authority, String path, String query, String fragment)
	{
		try
		{
			return new URI(scheme, authority, path, query, fragment);
		}
		catch (URISyntaxException e)
		{
			throw new IllegalArgumentException(e);
		}
	}
	
	public static final URI newUri(String scheme, String host, String path, String fragment)
	{
		try
		{
			return new URI(scheme, host, path, fragment);
		}
		catch (URISyntaxException e)
		{
			throw new IllegalArgumentException(e);
		}
	}
	
	public static final URI newUri(String scheme, String part, String fragment)
	{
		try
		{
			return new URI(scheme, part, fragment);
		}
		catch (URISyntaxException e)
		{
			throw new IllegalArgumentException(e);
		}
	}
	
	public static final URL newUrl(String content)
	{
		try
		{
			return new URL(content);
		}
		catch (MalformedURLException e)
		{
			throw new IllegalArgumentException(e);
		}
	}

	public static final URL newUrl(String protocol, String host, String file)
	{
		try
		{
			return new URL(protocol, host, file);
		}
		catch (MalformedURLException e)
		{
			throw new IllegalArgumentException(e);
		}
	}

	public static final URL newUrl(String protocol, String host, int port, String file)
	{
		try
		{
			return new URL(protocol, host, port, file);
		}
		catch (MalformedURLException e)
		{
			throw new IllegalArgumentException(e);
		}
	}
	
	public static final URL newUrl(String protocol, String host, int port, String file, URLStreamHandler handler)
	{
		try
		{
			return new URL(protocol, host, port, file, handler);
		}
		catch (MalformedURLException e)
		{
			throw new IllegalArgumentException(e);
		}
	}
	
	public static final URL newUrl(URL context, String content)
	{
		try
		{
			return new URL(context, content);
		}
		catch (MalformedURLException e)
		{
			throw new IllegalArgumentException(e);
		}
	}
	
	public static final URL newUrl(URL context, String content, URLStreamHandler handler)
	{
		try
		{
			return new URL(context, content, handler);
		}
		catch (MalformedURLException e)
		{
			throw new IllegalArgumentException(e);
		}
	}
}
