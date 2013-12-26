
package com.gratex.perconik.services.uaca.vs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdeFindFileResultDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdeFindFileResultDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="File" type="{http://www.gratex.com/PerConIk/IActivitySvc}IdeDocumentDto" minOccurs="0"/>
 *         &lt;element name="Rows" type="{http://www.gratex.com/PerConIk/IActivitySvc}ArrayOfIdeFindResultRowDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdeFindFileResultDto", propOrder = {
    "file",
    "rows"
})
public class IdeFindFileResultDto {

    @XmlElement(name = "File")
    protected IdeDocumentDto file;
    @XmlElement(name = "Rows")
    protected ArrayOfIdeFindResultRowDto rows;

    /**
     * Gets the value of the file property.
     * 
     * @return
     *     possible object is
     *     {@link IdeDocumentDto }
     *     
     */
    public IdeDocumentDto getFile() {
        return file;
    }

    /**
     * Sets the value of the file property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdeDocumentDto }
     *     
     */
    public void setFile(IdeDocumentDto value) {
        this.file = value;
    }

    /**
     * Gets the value of the rows property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfIdeFindResultRowDto }
     *     
     */
    public ArrayOfIdeFindResultRowDto getRows() {
        return rows;
    }

    /**
     * Sets the value of the rows property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfIdeFindResultRowDto }
     *     
     */
    public void setRows(ArrayOfIdeFindResultRowDto value) {
        this.rows = value;
    }

}
