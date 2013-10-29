
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LyncStatusChangeDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LyncStatusChangeDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}EventDto">
 *       &lt;attribute name="Status" use="required" type="{http://www.gratex.com/PerConIk/IActivitySvc}LyncStatusTypeEnum" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LyncStatusChangeDto")
public class LyncStatusChangeDto
    extends EventDto
{

    @XmlAttribute(name = "Status", required = true)
    protected LyncStatusTypeEnum status;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link LyncStatusTypeEnum }
     *     
     */
    public LyncStatusTypeEnum getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link LyncStatusTypeEnum }
     *     
     */
    public void setStatus(LyncStatusTypeEnum value) {
        this.status = value;
    }

}
