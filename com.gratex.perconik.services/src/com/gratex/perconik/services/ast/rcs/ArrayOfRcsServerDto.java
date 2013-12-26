
package com.gratex.perconik.services.ast.rcs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRcsServerDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRcsServerDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RcsServerDto" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}RcsServerDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRcsServerDto", propOrder = {
    "rcsServerDto"
})
public class ArrayOfRcsServerDto {

    @XmlElement(name = "RcsServerDto", nillable = true)
    protected List<RcsServerDto> rcsServerDto;

    /**
     * Gets the value of the rcsServerDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rcsServerDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRcsServerDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RcsServerDto }
     * 
     * 
     */
    public List<RcsServerDto> getRcsServerDto() {
        if (rcsServerDto == null) {
            rcsServerDto = new ArrayList<RcsServerDto>();
        }
        return this.rcsServerDto;
    }

}
