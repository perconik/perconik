
package com.gratex.perconik.services.ast.rcs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfQueuedFileVersionDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfQueuedFileVersionDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QueuedFileVersionDto" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}QueuedFileVersionDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfQueuedFileVersionDto", propOrder = {
    "queuedFileVersionDto"
})
public class ArrayOfQueuedFileVersionDto {

    @XmlElement(name = "QueuedFileVersionDto", nillable = true)
    protected List<QueuedFileVersionDto> queuedFileVersionDto;

    /**
     * Gets the value of the queuedFileVersionDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the queuedFileVersionDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQueuedFileVersionDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QueuedFileVersionDto }
     * 
     * 
     */
    public List<QueuedFileVersionDto> getQueuedFileVersionDto() {
        if (queuedFileVersionDto == null) {
            queuedFileVersionDto = new ArrayList<QueuedFileVersionDto>();
        }
        return this.queuedFileVersionDto;
    }

}
