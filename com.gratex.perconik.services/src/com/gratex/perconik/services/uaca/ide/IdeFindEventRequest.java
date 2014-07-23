package com.gratex.perconik.services.uaca.ide;

import java.util.ArrayList;
import java.util.List;

public class IdeFindEventRequest extends BaseIdeEventRequest {
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
	private List<IdeFindFileResultDto> resultsPerFiles;
	
	/**
	 * @return the {@link #queryText}
	 */
	public String getQueryText() {
		return queryText;
	}
	/**
	 * @param {@link #queryText}
	 */
	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}
	/**
	 * @return the {@link #matchCase}
	 */
	public Boolean getMatchCase() {
		return matchCase;
	}
	/**
	 * @param {@link #matchCase}
	 */
	public void setMatchCase(Boolean matchCase) {
		this.matchCase = matchCase;
	}
	/**
	 * @return the {@link #matchWholeWord}
	 */
	public Boolean getMatchWholeWord() {
		return matchWholeWord;
	}
	/**
	 * @param {@link #matchWholeWord}
	 */
	public void setMatchWholeWord(Boolean matchWholeWord) {
		this.matchWholeWord = matchWholeWord;
	}
	/**
	 * @return the {@link #searchSubfolders}
	 */
	public Boolean getSearchSubfolders() {
		return searchSubfolders;
	}
	/**
	 * @param {@link #searchSubfolders}
	 */
	public void setSearchSubfolders(Boolean searchSubfolders) {
		this.searchSubfolders = searchSubfolders;
	}
	/**
	 * @return the {@link #lookinTypeUri}
	 */
	public String getLookinTypeUri() {
		return lookinTypeUri;
	}
	/**
	 * @param {@link #lookinTypeUri}
	 */
	public void setLookinTypeUri(String lookinTypeUri) {
		this.lookinTypeUri = lookinTypeUri;
	}
	/**
	 * @return the {@link #patternSyntaxTypeUri}
	 */
	public String getPatternSyntaxTypeUri() {
		return patternSyntaxTypeUri;
	}
	/**
	 * @param {@link #patternSyntaxTypeUri}
	 */
	public void setPatternSyntaxTypeUri(String patternSyntaxTypeUri) {
		this.patternSyntaxTypeUri = patternSyntaxTypeUri;
	}
	/**
	 * @return the {@link #fileTypes}
	 */
	public String getFileTypes() {
		return fileTypes;
	}
	/**
	 * @param {@link #fileTypes}
	 */
	public void setFileTypes(String fileTypes) {
		this.fileTypes = fileTypes;
	}
	/**
	 * @return the {@link #totalFilesSearched}
	 */
	public Integer getTotalFilesSearched() {
		return totalFilesSearched;
	}
	/**
	 * @param {@link #totalFilesSearched}
	 */
	public void setTotalFilesSearched(Integer totalFilesSearched) {
		this.totalFilesSearched = totalFilesSearched;
	}
	/**
	 * @return the {@link #derivedResources}
	 */
	public Boolean getDerivedResources() {
		return derivedResources;
	}
	/**
	 * @param {@link #derivedResources}
	 */
	public void setDerivedResources(Boolean derivedResources) {
		this.derivedResources = derivedResources;
	}
	/**
	 * @return the {@link #resultsPerFiles}
	 */
	public List<IdeFindFileResultDto> getResultsPerFiles() {
		if(resultsPerFiles == null){
			resultsPerFiles = new ArrayList<IdeFindFileResultDto>();
		}
		return resultsPerFiles;
	}
	/**
	 * @param {@link #resultsPerFiles}
	 */
	public void setResultsPerFiles(List<IdeFindFileResultDto> resultsPerFiles) {
		this.resultsPerFiles = resultsPerFiles;
	}
}