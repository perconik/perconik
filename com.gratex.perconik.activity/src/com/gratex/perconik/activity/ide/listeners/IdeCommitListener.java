package com.gratex.perconik.activity.ide.listeners;

import static com.google.common.base.Preconditions.checkArgument;
import static com.gratex.perconik.activity.ide.IdeActivityServices.performWatcherServiceOperation;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setEventData;
import static com.gratex.perconik.activity.ide.listeners.Utilities.currentTime;
import java.io.File;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import org.eclipse.jgit.events.RefsChangedEvent;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import sk.stuba.fiit.perconik.core.listeners.GitReferenceListener;
import sk.stuba.fiit.perconik.eclipse.jgit.lib.GitRepositories;
import com.google.common.collect.Maps;
import com.gratex.perconik.activity.ide.IdeActivityServices.WatcherServiceOperation;
import com.gratex.perconik.activity.ide.IdeDataTransferObjects;
import com.gratex.perconik.services.IVsActivityWatcherService;
import com.gratex.perconik.services.uaca.vs.IdeCheckinDto;

/**
 * A listener of {@code IdeCommit} events. This listener creates
 * {@link IdeCheckinDto} data transfer objects and passes them to
 * the <i>Activity Watcher Service</i> to be transferred into the
 * <i>User Activity Client Application</i> for further processing.
 * 
 * <p>Commit listener listens to Git commit events only.
 * 
 * <p>Data available in an {@code IdeCheckinDto}:
 * 
 * <ul>
 *   <li>{@code idInRcs} - current Git commit
 *   identifier (40 hexadecimal characters),
 *   for example {@code "ffba951d35f710abee873db3f5547043aeb3fde9"}.
 *   <li>{@code rcsServer} - see {@code RcsServerDto} below.
 *   <li>See {@link IdeListener} for documentation of inherited data.
 * </ul>
 * 
 * <p>Data available in an {@code RcsServerDto}:
 * 
 * <ul>
 *   <li>{@code path} - remote origin URL from the nearest Git repository,
 *   for example {@code https://github.com/perconik/perconik.git}. The nearest
 *   Git repository is the first one found on path starting in project root,
 *   going through workspace root down to the file system root.
 *   <li>{@code type} - always {@code "git"}.
 * </ul>
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class IdeCommitListener extends IdeListener implements GitReferenceListener
{
	private final Object lock = new Object();
	
	@GuardedBy("lock")
	private final Map<File, Map<String, String>> cache;
	
	public IdeCommitListener()
	{
		this.cache = Maps.newHashMap();
	}
	
	private final boolean updateLastCommit(final File directory, final String branch, final String id)
	{
		Map<String, String> cache;
		
		synchronized (this.lock)
		{
			cache = this.cache.get(directory);
			
			if (cache == null)
			{
				this.cache.put(directory, cache = Maps.newHashMap());
			}
		}
		
		synchronized (cache)
		{
			String last = cache.get(branch);
			
			if (!id.equals(last))
			{
				cache.put(branch, id);
				
				return last != null;
			}
		}
		
		return false;
	}

	static final void send(final IdeCheckinDto data)
	{
		performWatcherServiceOperation(new WatcherServiceOperation()
		{
			public final void perform(final IVsActivityWatcherService service)
			{
				service.notifyIdeCheckin(data);
			}
		});
	}
	
	static final IdeCheckinDto build(final long time, final String url, final String id)
	{
		final IdeCheckinDto data = new IdeCheckinDto();

		data.setIdInRcs(id);
	//	data.setRcsServer(IdeDataTransferObjects.newGitServerData(url));

		setApplicationData(data);
		setEventData(data, time);
		
		if (Log.enabled()) Log.message().appendln("commit: " + id + " url: " + url).appendTo(console);
		
		return data;
	}
	
	final void process(final long time, final RefsChangedEvent event)
	{
		Repository repository = event.getRepository();
		File       directory  = repository.getDirectory();
		String     url        = GitRepositories.getRemoteOriginUrl(repository);
		
		checkArgument(url != null, "Unable to get remote origin url from %s", directory);
		
		String    branch = GitRepositories.getBranch(repository);
		RevCommit commit = GitRepositories.getMostRecentCommit(repository);
		
		String id = commit.getName();
		
		if (this.updateLastCommit(directory, branch, id))
		{
			send(build(time, url, id));
		}
	}

	public final void onRefsChanged(final RefsChangedEvent event)
	{
		final long time = currentTime();
		
		execute(new Runnable()
		{
			public final void run()
			{
				process(time, event);
			}
		});
	}
}
