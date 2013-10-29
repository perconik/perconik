
package com.gratex.perconik.services.vs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdeDocumentOperationDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdeDocumentOperationDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}IdeSlnPrjEventDto">
 *       &lt;sequence>
 *         &lt;element name="Document" type="{http://www.gratex.com/PerConIk/IActivitySvc}IdeDocumentDto" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="OperationType" use="required" type="{http://www.gratex.com/PerConIk/IActivitySvc}IdeDocumentOperationTypeEnum" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdeDocumentOperationDto", propOrder = {
    "document"
})
public class IdeDocumentOperationDto
    extends IdeSlnPrjEventDto
{

    @XmlElement(name = "Document")
    protected IdeDocumentDto document;
    @XmlAttribute(name = "OperationType", required = true)
    protected IdeDocumentOperationTypeEnum operationType;

    /**
     * Gets the value of the document property.
     * 
     * @return
     *     possible object is
     *     {@link IdeDocumentDto }
     *     
     */
    public IdeDocumentDto getDocument() {
        return document;
    }

    /**
     * Sets the value of the document property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdeDocumentDto }
     *     
     */
    public void setDocument(IdeDocumentDto value) {
        this.document = value;
    }

    /**
     * Gets the value of the operationType property.
     * 
     * @return
     *     possible object is
     *     {@link IdeDocumentOperationTypeEnum }
     *     
     */
    public IdeDocumentOperationTypeEnum getOperationType() {
        return operationType;
    }

    /**
     * Sets the value of the operationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdeDocumentOperationTypeEnum }
     *     
     */
    public void setOperationType(IdeDocumentOperationTypeEnum value) {
        this.operationType = value;
    }

}
