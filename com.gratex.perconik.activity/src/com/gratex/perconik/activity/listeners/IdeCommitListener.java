package com.gratex.perconik.activity.listeners;

import static com.gratex.perconik.activity.DataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.DataTransferObjects.setEventData;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.events.RefsChangedEvent;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import sk.stuba.fiit.perconik.core.listeners.GitReferenceListener;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.gratex.perconik.activity.ActivityServices;
import com.gratex.perconik.activity.ActivityServices.WatcherServiceOperation;
import com.gratex.perconik.services.activity.IVsActivityWatcherService;
import com.gratex.perconik.services.activity.IdeCheckinDto;
import com.gratex.perconik.services.activity.RcsServerDto;

/**
 * A listener of {@code IdeCommit} events. This listener creates
 * {@link IdeCheckinDto} data transfer objects and passes them to
 * the <i>Activity Watcher Service</i> to be transferred into the
 * <i>User Activity Client Application</i> for further processing.
 * 
 * <p> TODO document how DTOs are build and what data they contain
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class IdeCommitListener extends IdeListener implements GitReferenceListener
{
	private final Map<File, Map<String, String>> cache;
	
	public IdeCommitListener()
	{
		this.cache = Maps.newHashMap();
	}
	
	private static final void process(final String path, final String id)
	{
		final RcsServerDto server = new RcsServerDto();
		
		server.setPath(path);
		server.setType("git");
		
		final IdeCheckinDto data = new IdeCheckinDto();

		data.setIdInRcs(id);
		data.setRcsServer(server);

		setApplicationData(data);
		setEventData(data);
		
		ActivityServices.performWatcherServiceOperation(new WatcherServiceOperation()
		{
			public final void perform(final IVsActivityWatcherService service)
			{
				service.notifyIdeCheckinAsync(data);
			}
		});
	}

	public final void onRefsChanged(final RefsChangedEvent event)
	{
		Repository repository = event.getRepository();

		String branch;
		
		Iterator<RevCommit> commits;
		
		try
		{
			branch  = repository.getBranch();
			commits = new Git(repository).log().setMaxCount(1).call().iterator();
		}
		catch (Exception e)
		{
			throw Throwables.propagate(e);
		}

		File directory = repository.getDirectory();
		
		Map<String, String> cache = this.cache.get(directory);
		
		if (cache == null)
		{
			this.cache.put(directory, cache = Maps.newHashMap());
		}

		// TODO rm
//		for (String s:repository.getConfig().getSections())
//		{
//			System.out.println("SECTION : "+s+" -- "+repository.getConfig().getNames(s));
//			
//			for (String ss:	repository.getConfig().getSubsections(s))
//			{
//				System.out.println("SUB-SECTION : "+ss + " -- "+repository.getConfig().getNames(s, ss));
//			}
//		}
		
		String path = repository.getConfig().getString("remote", "origin", "url");
		
		if (path == null)
		{
			throw new IllegalStateException("Unable to get remote origin url from " + directory);
		}
		
		String id   = commits.next().getName();
		String last = cache.get(branch);
		
		System.out.println("PATH: "+path + " ID: "+id);//TODO
		System.out.println("CMP: "+last + " cache <-> "+id+" id");//TODO
		
		if (last != null && !last.equals(id))
		{
			cache.put(branch, id);
			
			System.out.println("PROCESSED");// TODO rm
			
			process(path, id);
		}
	}
}
