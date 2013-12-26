
package com.gratex.perconik.services.uaca.vs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdeProjectOperationDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdeProjectOperationDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}IdeSlnPrjEventDto">
 *       &lt;attribute name="OperationType" use="required" type="{http://www.gratex.com/PerConIk/IActivitySvc}IdeProjectOperationTypeEnum" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdeProjectOperationDto")
public class IdeProjectOperationDto
    extends IdeSlnPrjEventDto
{

    @XmlAttribute(name = "OperationType", required = true)
    protected IdeProjectOperationTypeEnum operationType;

    /**
     * Gets the value of the operationType property.
     * 
     * @return
     *     possible object is
     *     {@link IdeProjectOperationTypeEnum }
     *     
     */
    public IdeProjectOperationTypeEnum getOperationType() {
        return operationType;
    }

    /**
     * Sets the value of the operationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdeProjectOperationTypeEnum }
     *     
     */
    public void setOperationType(IdeProjectOperationTypeEnum value) {
        this.operationType = value;
    }

}
