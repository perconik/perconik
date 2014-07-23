package com.gratex.perconik.services.uaca.ide;

public class IdeFindResultRowDto {
	/**
	 * Zero based index of a row where the match has been found or null if it has not been determined
	 */
	private Integer row;
	/**
	 * Zero based index of a column on a row where the match has been found or null if it has not been determined
	 */
	private Integer column;
	/**
	 * Text that has been matched against search query on this row
	 */
	private String text;

	public IdeFindResultRowDto(){
	}
	
	public IdeFindResultRowDto(Integer row, Integer column, String text) {
		this.row = row;
		this.column = column;
		this.text = text;
	}
	
	/**
	 * @return the {@link #row}
	 */
	public Integer getRow() {
		return row;
	}
	/**
	 * @param {@link #row}
	 */
	public void setRow(Integer row) {
		this.row = row;
	}
	/**
	 * @return the {@link #column}
	 */
	public Integer getColumn() {
		return column;
	}
	/**
	 * @param {@link #column}
	 */
	public void setColumn(Integer column) {
		this.column = column;
	}
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
}
