
package com.gratex.perconik.services.vs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfIdeFindResultRowDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfIdeFindResultRowDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdeFindResultRowDto" type="{http://www.gratex.com/PerConIk/IActivitySvc}IdeFindResultRowDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfIdeFindResultRowDto", propOrder = {
    "ideFindResultRowDto"
})
public class ArrayOfIdeFindResultRowDto {

    @XmlElement(name = "IdeFindResultRowDto", nillable = true)
    protected List<IdeFindResultRowDto> ideFindResultRowDto;

    /**
     * Gets the value of the ideFindResultRowDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ideFindResultRowDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdeFindResultRowDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdeFindResultRowDto }
     * 
     * 
     */
    public List<IdeFindResultRowDto> getIdeFindResultRowDto() {
        if (ideFindResultRowDto == null) {
            ideFindResultRowDto = new ArrayList<IdeFindResultRowDto>();
        }
        return this.ideFindResultRowDto;
    }

}
