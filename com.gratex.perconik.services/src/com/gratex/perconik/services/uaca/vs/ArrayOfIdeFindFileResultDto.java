
package com.gratex.perconik.services.uaca.vs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfIdeFindFileResultDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfIdeFindFileResultDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdeFindFileResultDto" type="{http://www.gratex.com/PerConIk/IActivitySvc}IdeFindFileResultDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfIdeFindFileResultDto", propOrder = {
    "ideFindFileResultDto"
})
public class ArrayOfIdeFindFileResultDto {

    @XmlElement(name = "IdeFindFileResultDto", nillable = true)
    protected List<IdeFindFileResultDto> ideFindFileResultDto;

    /**
     * Gets the value of the ideFindFileResultDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ideFindFileResultDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdeFindFileResultDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdeFindFileResultDto }
     * 
     * 
     */
    public List<IdeFindFileResultDto> getIdeFindFileResultDto() {
        if (ideFindFileResultDto == null) {
            ideFindFileResultDto = new ArrayList<IdeFindFileResultDto>();
        }
        return this.ideFindFileResultDto;
    }

}
