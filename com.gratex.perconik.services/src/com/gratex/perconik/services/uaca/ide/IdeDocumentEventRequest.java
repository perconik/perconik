package com.gratex.perconik.services.uaca.ide;

public class IdeDocumentEventRequest extends BaseIdeEventRequest {
	/**
	 * Document that has been subject of this event
	 */
	private IdeDocumentDto document;

	/**
	 * @return the {@link #document}
	 */
	public IdeDocumentDto getDocument() {
		return document;
	}
	/**
	 * @param {@link #document}
	 */
	public void setDocument(IdeDocumentDto document) {
		this.document = document;
	}
}