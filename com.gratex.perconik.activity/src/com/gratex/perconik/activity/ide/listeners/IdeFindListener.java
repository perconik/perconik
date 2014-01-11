package com.gratex.perconik.activity.ide.listeners;

import static com.gratex.perconik.activity.ide.IdeActivityServices.performWatcherServiceOperation;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setEventData;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setProjectData;
import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.search.internal.ui.text.FileSearchQuery;
import org.eclipse.search.internal.ui.text.FileSearchResult;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.text.FileTextSearchScope;
import org.eclipse.search.ui.text.Match;
import sk.stuba.fiit.perconik.core.annotations.Dependent;
import sk.stuba.fiit.perconik.core.listeners.SearchQueryListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.Projects;
import sk.stuba.fiit.perconik.eclipse.search.ui.text.MatchUnit;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;
import com.google.common.base.Joiner;
import com.gratex.perconik.activity.ide.IdeActivityServices.WatcherServiceOperation;
import com.gratex.perconik.services.IVsActivityWatcherService;
import com.gratex.perconik.services.uaca.vs.IdeFindFileResultDto;
import com.gratex.perconik.services.uaca.vs.IdeFindOperationDto;
import com.gratex.perconik.services.uaca.vs.IdeFindResultRowDto;

/**
 * A listener of {@code IdeFindOperation} events. This listener creates
 * {@link IdeFindOperationDto} data transfer objects and passes them to
 * the <i>Activity Watcher Service</i> to be transferred into the
 * <i>User Activity Client Application</i> for further processing.
 * 
 * <p> TODO document how DTOs are build and what data they contain
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
@Dependent({FileSearchQuery.class, FileSearchResult.class, FileTextSearchScope.class})
public final class IdeFindListener extends IdeListener implements SearchQueryListener
{
	// TODO add note about internal API usage: FileSearchQuery, FileTextSearchScope
	// TODO note: lookin -> scope
	
	public IdeFindListener()
	{
	}
	
	static final void send(final IdeFindOperationDto data)
	{
		performWatcherServiceOperation(new WatcherServiceOperation()
		{
			public final void perform(final IVsActivityWatcherService service)
			{
				service.notifyIdeFindOperation(data);
			}
		});
	}
	
	static final IdeFindOperationDto build(final long time, final IProject project, final FileSearchQuery query)
	{
		final IdeFindOperationDto data = new IdeFindOperationDto();

		data.setMatchCase(query.isCaseSensitive());
		data.setMatchWholeWord(query.isWholeWord());
		data.setSearchSubfolders(null);
		data.setTotalFilesSearched(null);
		data.setFindWhat(query.getSearchString());

		FileTextSearchScope scope = query.getSearchScope();
		
		String[] patterns = scope.getFileNamePatterns();
		
		data.setLookin(query.getSearchScope().getDescription());
		data.setFileTypes(patterns == null ? "*.*" : Joiner.on(",").join(patterns));
		data.setPatternSyntax(query.isRegexSearch() ? "Regular expressions" : "Wildcards");
		
		FileSearchResult result = (FileSearchResult) query.getSearchResult();
		
		for (Object element: result.getElements())
		{
			IFile file = result.getFile(element);

			IdeFindFileResultDto x = new IdeFindFileResultDto();
			
			//x.setFile(IdeDataTransferObjects.newDocumentData(file));
			
			
			//x.setRows(value);
			
			for (Match match: result.getMatches(element))
			{
				ITextFileBufferManager manager = FileBuffers.getTextFileBufferManager();
				
				ITextFileBuffer buffer = manager.getTextFileBuffer(file.getFullPath(), LocationKind.IFILE);
				
				IDocument document = buffer.getDocument();
				
				IdeFindResultRowDto y = new IdeFindResultRowDto();
				
				int offset = match.getOffset();
				int length = match.getLength();
				
//				switch (MatchUnit.valueOf(match.getBaseUnit()))
//				{
//					case CHARACTER:
//						y.setRow(document.getLineOfOffset(offset));
//						y.setText(document.get(offset, length));
//						break;
//						
//					case LINE:
//						y.setRow(offset);
//						y.setText(document.get(document.getLineOffset(offset), length));
//						break;
//						
//					default:
//						throw new IllegalStateException();
//				}
			}
		}
		
		//data.setResultsPerFiles(results);

		setProjectData(data, project);
		setApplicationData(data);
		setEventData(data, time);
		
		return data;
	}
	
	// TODO
//	ArrayOfIdeFindFileResultDto
//	ArrayOfIdeFindResultRowDto
//	
//	private static final class X extends ArrayOfIdeFindFileResultDto
//	{
//		X()
//		{
//			this.ideFindFileResultDto = Lists.newArrayList();
//		}
//	}
	
	static final void process(final long time, final ISearchQuery query)
	{
		Object o = IdeFindListener.class.cast(query);// TODO rm
		
		IProject project = Projects.fromPage(Workbenches.getActivePage());
		
		send(build(time, project, (FileSearchQuery) query));
	}
	
	public final void queryAdded(final ISearchQuery query)
	{
	}

	public final void queryRemoved(final ISearchQuery query)
	{
	}

	public final void queryStarting(final ISearchQuery query)
	{
	}

	public final void queryFinished(final ISearchQuery query)
	{
		final long time = currentTime();
		
		executor.execute(new Runnable()
		{
			public final void run()
			{
				process(time, query);
			}
		});
	}
}
