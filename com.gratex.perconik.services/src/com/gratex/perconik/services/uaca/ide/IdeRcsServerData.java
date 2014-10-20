package com.gratex.perconik.services.uaca.ide;

public class IdeRcsServerData {
  /**
   * Server url
   */
  private String url;

  /**
   * Type of the server.
   * Format should follow: http://perconik.gratex.com/useractivity/enum/rcsserver/type#[value] where value is "git", "tfs", ...
   */
  private String typeUri;

  public IdeRcsServerData() {}

  public IdeRcsServerData(final String url, final String typeUri) {
    super();
    this.url = url;
    this.typeUri = typeUri;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  public String getTypeUri() {
    return this.typeUri;
  }

  public void setTypeUri(final String typeUri) {
    this.typeUri = typeUri;
  }
}
