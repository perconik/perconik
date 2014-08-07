package com.gratex.perconik.activity.ide.listeners;

import java.util.List;

import com.google.common.base.Joiner;

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

import com.gratex.perconik.activity.ide.IdeData;
import com.gratex.perconik.activity.uaca.IdeUacaProxy;
import com.gratex.perconik.activity.uaca.IdeUacaUris;
import com.gratex.perconik.services.uaca.ide.IdeFindEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeFindFileResultDto;
import com.gratex.perconik.services.uaca.ide.IdeFindResultRowDto;

import sk.stuba.fiit.perconik.core.annotations.Dependent;
import sk.stuba.fiit.perconik.core.listeners.SearchQueryListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.Projects;
import sk.stuba.fiit.perconik.eclipse.jface.text.Documents;
import sk.stuba.fiit.perconik.eclipse.search.ui.text.MatchUnit;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Lists.newArrayListWithCapacity;

import static com.gratex.perconik.activity.ide.IdeData.setApplicationData;
import static com.gratex.perconik.activity.ide.IdeData.setEventData;
import static com.gratex.perconik.activity.ide.IdeData.setProjectData;
import static com.gratex.perconik.activity.ide.listeners.Utilities.currentTime;

/**
 * A listener of IDE find events. This listener handles desired
 * events and eventually builds corresponding data transfer objects
 * of type {@link IdeFindEventRequest} and passes them to the
 * {@link IdeUacaProxy} to be transferred into the <i>User Activity Central
 * Application</i> for further processing.
 *
 * <p>Find operations are logged when a file search is performed.
 *
 * <p>Data available in an {@code IdeFindEventRequest}:
 *
 * <ul>
 *   <li>{@code derivedResources} - set to {@code true} if search should
 *   consider derived resources, {@code false} otherwise.
 *   <li>{@code fileTypes} - file name patterns separated by {@code ", "}.
 *   Set to {@code "*"} by default, other examples produce strings such as
 *   {@code "Map*.*, String*.class"}.
 *   <li>{@code queryText} - the search query string.
 *   <li>{@code lookinTypeUri} - search scopes separated by {@code ", "}.
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
 *   <li>{@code matchWholeWord} - always {@code null}, can not be determined.
 *   <li>{@code patternSyntaxTypeUri} - set to {@code "Regular expressions"} when
 *   enabled or {@code "Wildcards"} by default.
 *   <li>{@code resultsPerFiles} - a list of matched files,
 *   see {@code IdeFindFileResultDto} below.
 *   <li>{@code searchSubfolders} - always {@code null}, in fact it is always
 *   {@code true} (whole directory tree is always searched) but {@code null}
 *   indicates that it is not a search option nor accessible via search API.
 *   <li>{@code totalFilesSearched} - always {@code null}, not accessible
 *   via search API.
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
@Dependent({FileSearchQuery.class, FileSearchResult.class})
public final class IdeFindListener extends IdeListener implements SearchQueryListener {
  public IdeFindListener() {}

  static final IdeFindEventRequest build(final long time, final IProject project, final FileSearchQuery query) {
    final IdeFindEventRequest data = new IdeFindEventRequest();

    data.setQueryText(query.getSearchString());
    data.setMatchCase(query.isCaseSensitive());
    data.setMatchWholeWord(null);
    data.setSearchSubfolders(null);
    data.setTotalFilesSearched(null);

    FileTextSearchScope scope = query.getSearchScope();

    String[] patterns = scope.getFileNamePatterns();
    IWorkingSet[] sets = scope.getWorkingSets();
    IResource[] roots = scope.getRoots();

    data.setDerivedResources(scope.includeDerived());
    data.setFileTypes(patterns == null ? "*" : Joiner.on(",").join(patterns));
    data.setLookinTypeUri(IdeUacaUris.forLookinType(sets == null ? toString(roots) : toString(sets)));
    data.setPatternSyntaxTypeUri(IdeUacaUris.forPatternSyntaxType(query.isRegexSearch() ? "regex" : "wildcard"));

    FileSearchResult result = (FileSearchResult) query.getSearchResult();

    data.setResultsPerFiles(buildResults(result));

    setProjectData(data, project);
    setApplicationData(data);
    setEventData(data, time);

    return data;
  }

  private static final List<IdeFindFileResultDto> buildResults(FileSearchResult result) {
    Object[] elements = result.getElements();

    List<IdeFindFileResultDto> list = newArrayListWithCapacity(elements.length);

    for (Object element: elements) {
      IFile file = result.getFile(element);
      Match[] matches = result.getMatches(element);

      list.add(buildResult(file, matches));
    }

    return list;
  }

  private static final IdeFindFileResultDto buildResult(IFile file, Match[] matches) {
    IdeFindFileResultDto data = new IdeFindFileResultDto();

    data.setFile(IdeData.newDocumentData(file));
    data.setRows(buildMatches(Documents.fromFile(file), matches));

    return data;
  }

  private static final List<IdeFindResultRowDto> buildMatches(IDocument document, Match[] matches) {
    List<IdeFindResultRowDto> list = newArrayListWithCapacity(matches.length);

    for (Match match: matches) {
      list.add(buildMatch(document, match));
    }

    return list;
  }

  private static final IdeFindResultRowDto buildMatch(IDocument document, Match match) {
    IdeFindResultRowDto data = new IdeFindResultRowDto();

    int offset = match.getOffset();
    int length = match.getLength();

    try {
      switch (MatchUnit.valueOf(match.getBaseUnit())) {
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
    } catch (BadLocationException e) {
      propagate(e);
    }

    return data;
  }

  private static final String toString(IResource[] resources) {
    if (resources.length == 1 && resources[0] instanceof IWorkspaceRoot) {
      return "workspace";
    }

    List<String> parts = newArrayListWithCapacity(resources.length);

    for (IResource resource: resources) {
      parts.add(resource.getFullPath().toString());
    }

    return Joiner.on(",").join(parts);
  }

  private static final String toString(IWorkingSet[] sets) {
    List<String> parts = newArrayListWithCapacity(sets.length);

    for (IWorkingSet set: sets) {
      parts.add(set.getLabel());
    }

    return "working sets " + Joiner.on(",").join(parts);
  }

  static final void process(final long time, final ISearchQuery query) {
    IWorkbenchPage page = execute(DisplayTask.of(Workbenches.activePageSupplier()));

    IProject project = Projects.fromPage(page);

    // TODO project can not be always determined: when IClassFile is in editor, or when nothing is selected
    // TODO handle other query types such as JavaSearchQuery

    if (query instanceof FileSearchQuery) {
      proxy.sendFindEvent(build(time, project, (FileSearchQuery) query));
    }
  }

  public final void queryAdded(final ISearchQuery query) {}

  public final void queryRemoved(final ISearchQuery query) {}

  public final void queryStarting(final ISearchQuery query) {}

  public final void queryFinished(final ISearchQuery query) {
    final long time = currentTime();

    execute(new Runnable() {
      public final void run() {
        process(time, query);
      }
    });
  }
}
