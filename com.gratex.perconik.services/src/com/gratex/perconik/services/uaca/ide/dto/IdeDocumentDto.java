package com.gratex.perconik.services.uaca.ide.dto;

public class IdeDocumentDto {
	/**
	 * Specifies the version of the document
	 */
	private String changesetIdInRcs;
	/**
	 *  Full local path of the document
	 */
	private String localPath;
	/**
	 * Path of the document in the RCS. Null if not determined or in case of local file.
	 */
	private String serverPath;
	/**
	 * Rcs server to which this file version belongs. Null if not determined or in case of local file.
	 */
	private RcsServerDto rcsServer;
	/**
	 * Rcs branch name. Null if not determined or in case of local file. 
	 */
	private String branch;

	public IdeDocumentDto(){
	}

	public IdeDocumentDto(String changesetIdInRcs, String localPath,
			String serverPath, RcsServerDto rcsServer, String branch) {
		super();
		this.changesetIdInRcs = changesetIdInRcs;
		this.localPath = localPath;
		this.serverPath = serverPath;
		this.rcsServer = rcsServer;
		this.branch = branch;
	}
	/**
	 * @return the {@link #changesetIdInRcs}
	 */
	public String getChangesetIdInRcs() {
		return changesetIdInRcs;
	}
	/**
	 * @param {@link #changesetIdInRcs}
	 */
	public void setChangesetIdInRcs(String changesetIdInRcs) {
		this.changesetIdInRcs = changesetIdInRcs;
	}
	/**
	 * @return the {@link #localPath}
	 */
	public String getLocalPath() {
		return localPath;
	}
	/**
	 * @param {@link #localPath}
	 */
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	/**
	 * @return the {@link #serverPath}
	 */
	public String getServerPath() {
		return serverPath;
	}
	/**
	 * @param {@link #serverPath}
	 */
	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}
	/**
	 * @return the {@link #rcsServer}
	 */
	public RcsServerDto getRcsServer() {
		return rcsServer;
	}
	/**
	 * @param {@link #rcsServer}
	 */
	public void setRcsServer(RcsServerDto rcsServer) {
		this.rcsServer = rcsServer;
	}
	/**
	 * @return the {@link #branch}
	 */
	public String getBranch() {
		return branch;
	}
	/**
	 * @param {@link #branch}
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}
}
