package com.gratex.perconik.activity.ide.listeners;

import static com.gratex.perconik.activity.ide.IdeActivityServices.performWatcherServiceOperation;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setEventData;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setProjectData;
import java.util.List;
import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.search.internal.ui.text.FileSearchQuery;
import org.eclipse.search.internal.ui.text.FileSearchResult;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.text.FileTextSearchScope;
import org.eclipse.search.ui.text.Match;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import sk.stuba.fiit.perconik.core.annotations.Dependent;
import sk.stuba.fiit.perconik.core.listeners.SearchQueryListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.Projects;
import sk.stuba.fiit.perconik.eclipse.core.runtime.CoreExceptions;
import sk.stuba.fiit.perconik.eclipse.search.ui.text.MatchUnit;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import com.gratex.perconik.activity.ide.IdeActivityServices.WatcherServiceOperation;
import com.gratex.perconik.activity.ide.IdeApplication;
import com.gratex.perconik.activity.ide.IdeDataTransferObjects;
import com.gratex.perconik.services.IVsActivityWatcherService;
import com.gratex.perconik.services.uaca.vs.ArrayOfIdeFindFileResultDto;
import com.gratex.perconik.services.uaca.vs.ArrayOfIdeFindResultRowDto;
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
 * <p>Note that row and column offsets in documents start from zero
 * instead of one.
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

		data.setFindWhat(query.getSearchString());
		data.setMatchCase(query.isCaseSensitive());
		data.setMatchWholeWord(query.isWholeWord());
		data.setSearchSubfolders(null);
		data.setTotalFilesSearched(null);
		
		FileTextSearchScope scope = query.getSearchScope();
		
		String[] patterns = scope.getFileNamePatterns();

		data.setDerivedResources(scope.includeDerived());
		data.setFileTypes(patterns == null ? "*.*" : Joiner.on(",").join(patterns));
		data.setLookin(query.getSearchScope().getDescription());
		data.setPatternSyntax(query.isRegexSearch() ? "Regular expressions" : "Wildcards");
		
		FileSearchResult result = (FileSearchResult) query.getSearchResult();
		
		data.setResultsPerFiles(buildResults(result));

		setProjectData(data, project);
		setApplicationData(data);
		setEventData(data, time);
		
		if (IdeApplication.getInstance().isDebug()){console.print(dump(data));} // TODO rm
		
		return data;
	}

	private static final ArrayOfIdeFindFileResultDto buildResults(FileSearchResult result)
	{
		ArrayOfIdeFindFileResultDto data = new ArrayOfIdeFindFileResultDto();
		List<IdeFindFileResultDto>  list = data.getIdeFindFileResultDto();
		
		for (Object element: result.getElements())
		{
			IFile   file    = result.getFile(element);
			Match[] matches = result.getMatches(element);

			list.add(buildResult(file, matches));
		}
		
		return data;
	}
	
	private static final IdeFindFileResultDto buildResult(IFile file, Match[] matches)
	{
		IdeFindFileResultDto data = new IdeFindFileResultDto();

		data.setFile(IdeDataTransferObjects.newDocumentData(file));


		// TODO mv
		ITextFileBufferManager manager = FileBuffers.getTextFileBufferManager();
		
		IPath path = file.getFullPath();
		LocationKind kind = LocationKind.IFILE;
		
		try
		{
			manager.connect(path, kind, null);
		}
		catch (CoreException e)
		{
			CoreExceptions.propagate(e);
		}
		
		ITextFileBuffer buffer = manager.getTextFileBuffer(path, kind);
		IDocument document = buffer.getDocument();

		
		data.setRows(buildMatches(document, matches));
		
		return data;
	}
	
	private static final ArrayOfIdeFindResultRowDto buildMatches(IDocument document, Match[] matches)
	{
		ArrayOfIdeFindResultRowDto data = new ArrayOfIdeFindResultRowDto();
		List<IdeFindResultRowDto>  list = data.getIdeFindResultRowDto();
		
		for (Match match: matches)
		{
			list.add(buildMatch(document, match));
		}
		
		return data;
	}
	
	private static final IdeFindResultRowDto buildMatch(IDocument document, Match match)
	{
		IdeFindResultRowDto data = new IdeFindResultRowDto();
		
		int offset = match.getOffset();
		int length = match.getLength();
		
		try
		{
			switch (MatchUnit.valueOf(match.getBaseUnit()))
			{
				case CHARACTER:
					data.setRow(document.getLineOfOffset(offset));
					data.setColumn(offset - document.getLineOffset(data.getRow()));
					data.setText(document.get(offset, length));
					break;
					
				case LINE:
					data.setRow(offset);
					data.setColumn(null);
					data.setText(document.get(document.getLineOffset(offset), length));
					break;
					
				default:
					throw new IllegalStateException();
			}
		}
		catch (BadLocationException e)
		{
			Throwables.propagate(e);
		}
		
		return data;
	}
	
	static final void process(final long time, final IWorkbenchPage page, final ISearchQuery query)
	{
		IProject project = Projects.fromPage(page);
		
		send(build(time, project, (FileSearchQuery) query));
	}
	
	// TODO rm
	private static final String dump(IdeFindOperationDto data)
	{
		SmartStringBuilder builder = new SmartStringBuilder();
		
		builder.append("app-name: ").appendln(data.getApplicationName());
		builder.append("app-version: ").appendln(data.getApplicationVersion());
		builder.append("file-types: ").appendln(data.getFileTypes());
		builder.append("find-what: ").appendln(data.getFindWhat());
		builder.append("lookin: ").appendln(data.getLookin());
		builder.append("pattern-syntax: ").appendln(data.getPatternSyntax());
		builder.append("project-name: ").appendln(data.getProjectName());
		builder.append("solution-name: ").appendln(data.getSolutionName());
		builder.append("files-searched: ").appendln(data.getTotalFilesSearched());
		builder.appendln("results:").tab();
		
		for (IdeFindFileResultDto result: data.getResultsPerFiles().getIdeFindFileResultDto())
		{
			builder.append("file: ").appendln(result.getFile().getPath());
			builder.appendln("rows:").tab();
			
			for (IdeFindResultRowDto row: result.getRows().getIdeFindResultRowDto())
			{
				builder.append("row: ").appendln(row.getRow());
				builder.append("column: ").appendln(row.getColumn());
				builder.append("text: ").appendln(row.getText());
			}
			
			builder.untab();
		}
		
		return builder.toString();
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

		Display.getDefault().asyncExec(new Runnable()
		{
			public final void run()
			{
				process(time, Workbenches.getActivePage(), query);
			}
		});
	}
}
