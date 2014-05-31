package com.gratex.perconik.services.uaca.ide;

public class RcsServerDto {
	/**
	* Server url
	*/
	private String url;
	
	/**
	 * Type of the server.
	 * Format should follow : http://perconik.gratex.com/useractivity/enum/rcsserver/type#[value] where value is "git", "tfs", ...
	 */
	private String typeUri;

	
	public RcsServerDto(){
	}
	
	public RcsServerDto(String url, String typeUri) {
		super();
		this.url = url;
		this.typeUri = typeUri;
	}

	/**
	 * @return the {@link #url}
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param {@link #url}
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the {@link #typeUri}
	 */
	public String getTypeUri() {
		return typeUri;
	}

	/**
	 * @param {@link #typeUri}
	 */
	public void setTypeUri(String typeUri) {
		this.typeUri = typeUri;
	}
}
