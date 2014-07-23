
package com.gratex.perconik.services.ast.rcs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfInheritanceDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfInheritanceDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InheritanceDto" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}InheritanceDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfInheritanceDto", propOrder = {
    "inheritanceDto"
})
public class ArrayOfInheritanceDto {

    @XmlElement(name = "InheritanceDto", nillable = true)
    protected List<InheritanceDto> inheritanceDto;

    /**
     * Gets the value of the inheritanceDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inheritanceDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInheritanceDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InheritanceDto }
     * 
     * 
     */
    public List<InheritanceDto> getInheritanceDto() {
        if (inheritanceDto == null) {
            inheritanceDto = new ArrayList<InheritanceDto>();
        }
        return this.inheritanceDto;
    }

}
