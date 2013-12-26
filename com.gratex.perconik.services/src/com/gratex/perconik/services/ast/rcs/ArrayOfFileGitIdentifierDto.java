
package com.gratex.perconik.services.ast.rcs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfFileGitIdentifierDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfFileGitIdentifierDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FileGitIdentifierDto" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}FileGitIdentifierDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfFileGitIdentifierDto", propOrder = {
    "fileGitIdentifierDto"
})
public class ArrayOfFileGitIdentifierDto {

    @XmlElement(name = "FileGitIdentifierDto", nillable = true)
    protected List<FileGitIdentifierDto> fileGitIdentifierDto;

    /**
     * Gets the value of the fileGitIdentifierDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fileGitIdentifierDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFileGitIdentifierDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FileGitIdentifierDto }
     * 
     * 
     */
    public List<FileGitIdentifierDto> getFileGitIdentifierDto() {
        if (fileGitIdentifierDto == null) {
            fileGitIdentifierDto = new ArrayList<FileGitIdentifierDto>();
        }
        return this.fileGitIdentifierDto;
    }

}
