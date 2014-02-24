
package com.gratex.perconik.services.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LineCommentFormatDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LineCommentFormatDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Regex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SupportedFileExtensions" type="{http://www.gratex.com/PerConIk/TagAdm/ITagProfileWcfSvc}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="LineCommentTemplate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LineCommentFormatDto", propOrder = {
    "regex",
    "supportedFileExtensions",
    "lineCommentTemplate"
})
public class LineCommentFormatDto {

    @XmlElement(name = "Regex")
    protected String regex;
    @XmlElement(name = "SupportedFileExtensions")
    protected ArrayOfString supportedFileExtensions;
    @XmlElement(name = "LineCommentTemplate")
    protected String lineCommentTemplate;

    /**
     * Gets the value of the regex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegex() {
        return regex;
    }

    /**
     * Sets the value of the regex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegex(String value) {
        this.regex = value;
    }

    /**
     * Gets the value of the supportedFileExtensions property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getSupportedFileExtensions() {
        return supportedFileExtensions;
    }

    /**
     * Sets the value of the supportedFileExtensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setSupportedFileExtensions(ArrayOfString value) {
        this.supportedFileExtensions = value;
    }

    /**
     * Gets the value of the lineCommentTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineCommentTemplate() {
        return lineCommentTemplate;
    }

    /**
     * Sets the value of the lineCommentTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineCommentTemplate(String value) {
        this.lineCommentTemplate = value;
    }

}
