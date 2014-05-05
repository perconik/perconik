package com.gratex.perconik.services.uaca.ide.dto;

import java.util.List;
import java.util.ArrayList;

public class IdeFindFileResultDto {
    public IdeDocumentDto file;
    List<IdeFindResultRowDto> rows;
    
    public IdeFindFileResultDto(){
    }
    
	public IdeFindFileResultDto(IdeDocumentDto file, List<IdeFindResultRowDto> rows) {
		super();
		this.file = file;
		this.rows = rows;
	}
	
	/**
	 * @return the {@link #file}
	 */
	public IdeDocumentDto getFile() {
		return file;
	}
	/**
	 * @param {@link #file}
	 */
	public void setFile(IdeDocumentDto file) {
		this.file = file;
	}
	/**
	 * @return the {@link #rows}
	 */
	public List<IdeFindResultRowDto> getRows() {
		if(rows == null){
			rows = new ArrayList<IdeFindResultRowDto>();
		}
		return rows;
	}
	/**
	 * @param {@link #rows}
	 */
	public void setRows(List<IdeFindResultRowDto> rows) {
		this.rows = rows;
	}
}
