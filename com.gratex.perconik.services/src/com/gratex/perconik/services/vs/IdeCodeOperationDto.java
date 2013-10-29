
package com.gratex.perconik.services.vs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdeCodeOperationDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdeCodeOperationDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}IdeSlnPrjEventDto">
 *       &lt;sequence>
 *         &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WebUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Document" type="{http://www.gratex.com/PerConIk/IActivitySvc}IdeDocumentDto" minOccurs="0"/>
 *         &lt;element name="StartColumnIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EndColumnIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *       &lt;attribute name="StartRowIndex" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="EndRowIndex" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="OperationType" use="required" type="{http://www.gratex.com/PerConIk/IActivitySvc}IdeCodeOperationTypeEnum" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdeCodeOperationDto", propOrder = {
    "code",
    "webUrl",
    "document",
    "startColumnIndex",
    "endColumnIndex"
})
public class IdeCodeOperationDto
    extends IdeSlnPrjEventDto
{

    @XmlElement(name = "Code")
    protected String code;
    @XmlElement(name = "WebUrl")
    protected String webUrl;
    @XmlElement(name = "Document")
    protected IdeDocumentDto document;
    @XmlElement(name = "StartColumnIndex", required = true, type = Integer.class, nillable = true)
    protected Integer startColumnIndex;
    @XmlElement(name = "EndColumnIndex", required = true, type = Integer.class, nillable = true)
    protected Integer endColumnIndex;
    @XmlAttribute(name = "StartRowIndex", required = true)
    protected int startRowIndex;
    @XmlAttribute(name = "EndRowIndex", required = true)
    protected int endRowIndex;
    @XmlAttribute(name = "OperationType", required = true)
    protected IdeCodeOperationTypeEnum operationType;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the webUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * Sets the value of the webUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebUrl(String value) {
        this.webUrl = value;
    }

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
     * Gets the value of the startColumnIndex property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStartColumnIndex() {
        return startColumnIndex;
    }

    /**
     * Sets the value of the startColumnIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStartColumnIndex(Integer value) {
        this.startColumnIndex = value;
    }

    /**
     * Gets the value of the endColumnIndex property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEndColumnIndex() {
        return endColumnIndex;
    }

    /**
     * Sets the value of the endColumnIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEndColumnIndex(Integer value) {
        this.endColumnIndex = value;
    }

    /**
     * Gets the value of the startRowIndex property.
     * 
     */
    public int getStartRowIndex() {
        return startRowIndex;
    }

    /**
     * Sets the value of the startRowIndex property.
     * 
     */
    public void setStartRowIndex(int value) {
        this.startRowIndex = value;
    }

    /**
     * Gets the value of the endRowIndex property.
     * 
     */
    public int getEndRowIndex() {
        return endRowIndex;
    }

    /**
     * Sets the value of the endRowIndex property.
     * 
     */
    public void setEndRowIndex(int value) {
        this.endRowIndex = value;
    }

    /**
     * Gets the value of the operationType property.
     * 
     * @return
     *     possible object is
     *     {@link IdeCodeOperationTypeEnum }
     *     
     */
    public IdeCodeOperationTypeEnum getOperationType() {
        return operationType;
    }

    /**
     * Sets the value of the operationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdeCodeOperationTypeEnum }
     *     
     */
    public void setOperationType(IdeCodeOperationTypeEnum value) {
        this.operationType = value;
    }

}
