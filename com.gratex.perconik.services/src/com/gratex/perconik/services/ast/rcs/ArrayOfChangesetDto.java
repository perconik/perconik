
package com.gratex.perconik.services.ast.rcs;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfChangesetDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfChangesetDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ChangesetDto" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}ChangesetDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfChangesetDto", propOrder = {
    "changesetDto"
})
public class ArrayOfChangesetDto {

    @XmlElement(name = "ChangesetDto", nillable = true)
    protected List<ChangesetDto> changesetDto;

    /**
     * Gets the value of the changesetDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the changesetDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChangesetDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChangesetDto }
     * 
     * 
     */
    public List<ChangesetDto> getChangesetDto() {
        if (changesetDto == null) {
            changesetDto = new ArrayList<ChangesetDto>();
        }
        return this.changesetDto;
    }

}
