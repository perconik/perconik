package com.gratex.perconik.activity.ide.listeners;

import static com.gratex.perconik.activity.ide.IdeActivityServices.performWatcherServiceOperation;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setEventData;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setProjectData;
import static com.gratex.perconik.activity.ide.listeners.Utilities.currentTime;
import java.util.List;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.search.internal.ui.text.FileSearchQuery;
import org.eclipse.search.internal.ui.text.FileSearchResult;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.text.FileTextSearchScope;
import org.eclipse.search.ui.text.Match;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkingSet;
import sk.stuba.fiit.perconik.core.annotations.Dependent;
import sk.stuba.fiit.perconik.core.listeners.SearchQueryListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.Projects;
import sk.stuba.fiit.perconik.eclipse.jface.text.Documents;
import sk.stuba.fiit.perconik.eclipse.search.ui.text.MatchUnit;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.gratex.perconik.activity.ide.IdeActivityServices.WatcherServiceOperation;
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
 * <p>Find operations are logged when a file search is performed.
 * 
 * <p>Data available in an {@code IdeFindOperationDto}:
 * 
 * <ul>
 *   <li>{@code derivedResources} - set to {@code true} if search should
 *   consider derived resources, {@code false} otherwise.
 *   <li>{@code fileTypes} - file name patterns separated by {@code ", "}.
 *   Set to {@code "*"} by default, other examples produce strings such as
 *   {@code "Map*.*, String*.class"}.
 *   <li>{@code findWhat} - the search query string.
 *   <li>{@code lookin} - search scopes separated by {@code ", "}.
 *   In case of enclosed projects or selected resources the string
 *   consists of a list of resource (project) paths relative to workspace
 *   root (but starting with {@code "/"}), and separated by {@code ", "}.
 *   In case of working sets the string starts with {@code "working sets "}
 *   concatenated to a list of working set names separated by {@code ", "}.
 *   Set to {@code "workspace"} by default, other examples produce strings
 *   such as {@code "/com.gratex.perconik.activity"}, {@code "com.gratex.perconik.activity/src/com/gratex/perconik/activity/ide/listeners/IdeCommitListener.java"},
 *   (for enclosed projects or selected resources) or {@code "working sets PerConIK Core, PerConIK Gratex, PerConIK Site"} (for working sets).
 *   <li>{@code matchCase} - set to {@code true} if search is case sensitive,
 *   {@code false} otherwise.
 *   <li>{@code matchWholeWord} - set to {@code true} if search should match
 *   only whole words, {@code false} otherwise.
 *   <li>{@code patternSyntax} - set to {@code "Regular expressions"} when
 *   enabled or {@code "Wildcards"} by default.
 *   <li>{@code resultsPerFiles} - a list of matched files,
 *   see {@code IdeFindFileResultDto} below.
 *   <li>{@code searchSubfolders} - always {@code null}.
 *   <li>{@code totalFilesSearched} - always {@code null}.
 *   <li>See {@link IdeListener} for documentation of inherited data.
 * </ul>
 * 
 * <p>Data available in an {@code IdeFindFileResultDto}:
 * 
 * <ul>
 *   <li>{@code file} - matched file, see documentation of
 *   {@code IdeDocumentDto} in {@link IdeDocumentListener} for more details.
 *   <li>{@code rows} - a list of file matches,
 *   see {@code IdeFindResultRowDto} below.
 * </ul>
 * 
 * <p>Data available in an {@code IdeFindResultRowDto}:
 * 
 * <ul>
 *   <li>{@code column} - zero based match position on line,
 *   or {@code null} if can not be determined.
 *   <li>{@code row} - zero based match line number.
 *   <li>{@code text} - matched text.
 * </ul>
 * 
 * <p>Note that row and column offsets in documents start from zero
 * instead of one.
 * 
 * <p><b>Warning:</b> this listener depends on some Eclipse search API
 * internals, therefore correct functionality in next versions of Eclipse
 * IDE is not guaranteed.
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
		
		String[]      patterns = scope.getFileNamePatterns();
		IWorkingSet[] sets     = scope.getWorkingSets();
		IResource[]   roots    = scope.getRoots();

		data.setDerivedResources(scope.includeDerived());
		data.setFileTypes(patterns == null ? "*" : Joiner.on(", ").join(patterns));
		data.setLookin(sets == null ? toString(roots) : toString(sets));
		data.setPatternSyntax(query.isRegexSearch() ? "Regular expressions" : "Wildcards");

		FileSearchResult result = (FileSearchResult) query.getSearchResult();
		
		data.setResultsPerFiles(buildResults(result));

		setProjectData(data, project);
		setApplicationData(data);
		setEventData(data, time);
		
		if (Debug.enabled()) Debug.message().appendln(dump(data)).appendTo(console);
		
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
		data.setRows(buildMatches(Documents.fromFile(file), matches));
		
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

	private static final String toString(IResource[] resources)
	{
		if (resources.length == 1 && resources[0] instanceof IWorkspaceRoot)
		{
			return "workspace";
		}
		
		List<String> parts = Lists.newArrayListWithCapacity(resources.length);
		
		for (IResource resource: resources)
		{
			parts.add(resource.getFullPath().toString());
		}
		
		return Joiner.on(", ").join(parts);
	}

	private static final String toString(IWorkingSet[] sets)
	{
		List<String> parts = Lists.newArrayListWithCapacity(sets.length);
		
		for (IWorkingSet set: sets)
		{
			parts.add(set.getLabel());
		}
		
		return "working sets " + Joiner.on(", ").join(parts);
	}

	static final void process(final long time, final IWorkbenchPage page, final ISearchQuery query)
	{
		IProject project = Projects.fromPage(page);

		// TODO project can not be always determined: when IClassFile is in editor, or when nothing is selected
		
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

		executeSafely(new Runnable()
		{
			public final void run()
			{
				process(time, Workbenches.getActivePage(), query);
			}
		});
	}

	//TODO rm
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
}
