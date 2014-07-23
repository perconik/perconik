
package com.gratex.perconik.services.ast.rcs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfFileTfsIdentifierDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfFileTfsIdentifierDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FileTfsIdentifierDto" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}FileTfsIdentifierDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfFileTfsIdentifierDto", propOrder = {
    "fileTfsIdentifierDto"
})
public class ArrayOfFileTfsIdentifierDto {

    @XmlElement(name = "FileTfsIdentifierDto", nillable = true)
    protected List<FileTfsIdentifierDto> fileTfsIdentifierDto;

    /**
     * Gets the value of the fileTfsIdentifierDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fileTfsIdentifierDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFileTfsIdentifierDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FileTfsIdentifierDto }
     * 
     * 
     */
    public List<FileTfsIdentifierDto> getFileTfsIdentifierDto() {
        if (fileTfsIdentifierDto == null) {
            fileTfsIdentifierDto = new ArrayList<FileTfsIdentifierDto>();
        }
        return this.fileTfsIdentifierDto;
    }

}
