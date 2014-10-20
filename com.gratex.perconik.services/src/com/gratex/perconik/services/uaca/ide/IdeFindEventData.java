package com.gratex.perconik.services.uaca.ide;

import java.util.ArrayList;
import java.util.List;

public class IdeFindEventData extends IdeEventData {
  /**
   * Search query
   */
  private String queryText;

  /**
   * Whether was search case sensitive. Null if not determined
   */
  private Boolean matchCase;

  /**
   * Whether has search matched only whole words. Null if not determined
   */
  private Boolean matchWholeWord;

  /**
   * Whether was search recursive. Null if not determined
   */
  private Boolean searchSubfolders;

  /**
   * Uri specifing scope of search. It should be in form of:
   * http://perconik.gratex.com/useractivity/enum/idefindevent/lookintype/[idename]#[value]
   * where value is "file", "project", "workspace" ... and ideName is
   * "eclipse", "vs", ...
   */
  private String lookinTypeUri;

  /**
   * Uri specifying syntax of the query string. It should be in form of:
   * http://perconik.gratex.com/useractivity/enum/idefindevent/patternsyntaxtype/[idename]#[value]
   * where value is "regex", "wildcard", ... and ideName is "eclipse", "vs",
   * ...
   */
  private String patternSyntaxTypeUri;

  /**
   * String pattern representing file types that were searched. For instance
   * "*.cs;*.resx;*.resw;"
   */
  private String fileTypes;

  /**
   * number of files that were searched for a given query text. Null if not
   * determined
   */
  private Integer totalFilesSearched;

  /**
   * Whether were derived resources considered in search
   */
  private Boolean derivedResources;

  /**
   * search results per matched file
   */
  private List<IdeFindFileResultData> resultsPerFiles;

  public IdeFindEventData() {}

  public String getQueryText() {
    return this.queryText;
  }

  public void setQueryText(final String queryText) {
    this.queryText = queryText;
  }

  public Boolean getMatchCase() {
    return this.matchCase;
  }

  public void setMatchCase(final Boolean matchCase) {
    this.matchCase = matchCase;
  }

  public Boolean getMatchWholeWord() {
    return this.matchWholeWord;
  }

  public void setMatchWholeWord(final Boolean matchWholeWord) {
    this.matchWholeWord = matchWholeWord;
  }

  public Boolean getSearchSubfolders() {
    return this.searchSubfolders;
  }

  public void setSearchSubfolders(final Boolean searchSubfolders) {
    this.searchSubfolders = searchSubfolders;
  }

  public String getLookinTypeUri() {
    return this.lookinTypeUri;
  }

  public void setLookinTypeUri(final String lookinTypeUri) {
    this.lookinTypeUri = lookinTypeUri;
  }

  public String getPatternSyntaxTypeUri() {
    return this.patternSyntaxTypeUri;
  }

  public void setPatternSyntaxTypeUri(final String patternSyntaxTypeUri) {
    this.patternSyntaxTypeUri = patternSyntaxTypeUri;
  }

  public String getFileTypes() {
    return this.fileTypes;
  }

  public void setFileTypes(final String fileTypes) {
    this.fileTypes = fileTypes;
  }

  public Integer getTotalFilesSearched() {
    return this.totalFilesSearched;
  }

  public void setTotalFilesSearched(final Integer totalFilesSearched) {
    this.totalFilesSearched = totalFilesSearched;
  }

  public Boolean getDerivedResources() {
    return this.derivedResources;
  }

  public void setDerivedResources(final Boolean derivedResources) {
    this.derivedResources = derivedResources;
  }

  public List<IdeFindFileResultData> getResultsPerFiles() {
    if (this.resultsPerFiles == null) {
      this.resultsPerFiles = new ArrayList<>();
    }

    return this.resultsPerFiles;
  }

  public void setResultsPerFiles(final List<IdeFindFileResultData> resultsPerFiles) {
    this.resultsPerFiles = resultsPerFiles;
  }
}
