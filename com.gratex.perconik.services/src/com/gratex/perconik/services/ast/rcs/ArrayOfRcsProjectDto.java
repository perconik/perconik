
package com.gratex.perconik.services.ast.rcs;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRcsProjectDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRcsProjectDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RcsProjectDto" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}RcsProjectDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRcsProjectDto", propOrder = {
    "rcsProjectDto"
})
public class ArrayOfRcsProjectDto {

    @XmlElement(name = "RcsProjectDto", nillable = true)
    protected List<RcsProjectDto> rcsProjectDto;

    /**
     * Gets the value of the rcsProjectDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rcsProjectDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRcsProjectDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RcsProjectDto }
     * 
     * 
     */
    public List<RcsProjectDto> getRcsProjectDto() {
        if (rcsProjectDto == null) {
            rcsProjectDto = new ArrayList<RcsProjectDto>();
        }
        return this.rcsProjectDto;
    }

}
