
package com.gratex.perconik.services.itm;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfITarget complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfITarget">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ITarget" type="{http://perconik.fiit.stuba.sk/ITM}ITarget" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfITarget", propOrder = {
    "iTarget"
})
public class ArrayOfITarget {

    @XmlElement(name = "ITarget", nillable = true)
    protected List<ITarget> iTarget;

    /**
     * Gets the value of the iTarget property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the iTarget property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getITarget().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ITarget }
     * 
     * 
     */
    public List<ITarget> getITarget() {
        if (iTarget == null) {
            iTarget = new ArrayList<ITarget>();
        }
        return this.iTarget;
    }

}
