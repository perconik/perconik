package com.gratex.perconik.services.uaca.ide;

/**
 * Common request used for events: PasteFromWeb, SelectionChanged, Paste, Copy,
 * Cut
 * 
 */
public class IdeCodeEventRequest extends BaseIdeEventRequest {
	/**
	 * Text that was subject of a given event. For instance text that was pasted from the web.
	 */
	private String text;
	/**
	 * Zero based start row index. Null if not determined
	 */
	private Integer startRowIndex;
	/**
	 * Zero based end row index. Null if not determined
	 */
	private Integer endRowIndex;
	/**
	 * Zero based start column index. Null if not determined
	 */
	private Integer startColumnIndex;
	/**
	 * Zero based end column index. Null if not determined
	 */
	private Integer endColumnIndex;
	/**
	 * Document for which has been this event generated
	 */
	private IdeDocumentDto document;

	/**
	 * @return the {@link #text}
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param {@link #text}
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the {@link #startRowIndex}
	 */
	public Integer getStartRowIndex() {
		return startRowIndex;
	}
	/**
	 * @param {@link #startRowIndex}
	 */
	public void setStartRowIndex(Integer startRowIndex) {
		this.startRowIndex = startRowIndex;
	}
	/**
	 * @return the {@link #endRowIndex}
	 */
	public Integer getEndRowIndex() {
		return endRowIndex;
	}
	/**
	 * @param {@link #endRowIndex}
	 */
	public void setEndRowIndex(Integer endRowIndex) {
		this.endRowIndex = endRowIndex;
	}
	/**
	 * @return the {@link #startColumnIndex}
	 */
	public Integer getStartColumnIndex() {
		return startColumnIndex;
	}
	/**
	 * @param {@link #startColumnIndex}
	 */
	public void setStartColumnIndex(Integer startColumnIndex) {
		this.startColumnIndex = startColumnIndex;
	}
	/**
	 * @return the {@link #endColumnIndex}
	 */
	public Integer getEndColumnIndex() {
		return endColumnIndex;
	}
	/**
	 * @param {@link #endColumnIndex}
	 */
	public void setEndColumnIndex(Integer endColumnIndex) {
		this.endColumnIndex = endColumnIndex;
	}
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