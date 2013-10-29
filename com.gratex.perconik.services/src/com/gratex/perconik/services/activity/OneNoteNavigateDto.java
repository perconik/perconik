
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OneNoteNavigateDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OneNoteNavigateDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}OneNoteEventDto">
 *       &lt;sequence>
 *         &lt;element name="Notebook" type="{http://www.gratex.com/PerConIk/IActivitySvc}OneNoteNotebookDto" minOccurs="0"/>
 *         &lt;element name="SectionGroup" type="{http://www.gratex.com/PerConIk/IActivitySvc}OneNoteSectionGroupDto" minOccurs="0"/>
 *         &lt;element name="Section" type="{http://www.gratex.com/PerConIk/IActivitySvc}OneNoteSectionDto" minOccurs="0"/>
 *         &lt;element name="Page" type="{http://www.gratex.com/PerConIk/IActivitySvc}OneNotePageDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OneNoteNavigateDto", propOrder = {
    "notebook",
    "sectionGroup",
    "section",
    "page"
})
public class OneNoteNavigateDto
    extends OneNoteEventDto
{

    @XmlElement(name = "Notebook")
    protected OneNoteNotebookDto notebook;
    @XmlElement(name = "SectionGroup")
    protected OneNoteSectionGroupDto sectionGroup;
    @XmlElement(name = "Section")
    protected OneNoteSectionDto section;
    @XmlElement(name = "Page")
    protected OneNotePageDto page;

    /**
     * Gets the value of the notebook property.
     * 
     * @return
     *     possible object is
     *     {@link OneNoteNotebookDto }
     *     
     */
    public OneNoteNotebookDto getNotebook() {
        return notebook;
    }

    /**
     * Sets the value of the notebook property.
     * 
     * @param value
     *     allowed object is
     *     {@link OneNoteNotebookDto }
     *     
     */
    public void setNotebook(OneNoteNotebookDto value) {
        this.notebook = value;
    }

    /**
     * Gets the value of the sectionGroup property.
     * 
     * @return
     *     possible object is
     *     {@link OneNoteSectionGroupDto }
     *     
     */
    public OneNoteSectionGroupDto getSectionGroup() {
        return sectionGroup;
    }

    /**
     * Sets the value of the sectionGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link OneNoteSectionGroupDto }
     *     
     */
    public void setSectionGroup(OneNoteSectionGroupDto value) {
        this.sectionGroup = value;
    }

    /**
     * Gets the value of the section property.
     * 
     * @return
     *     possible object is
     *     {@link OneNoteSectionDto }
     *     
     */
    public OneNoteSectionDto getSection() {
        return section;
    }

    /**
     * Sets the value of the section property.
     * 
     * @param value
     *     allowed object is
     *     {@link OneNoteSectionDto }
     *     
     */
    public void setSection(OneNoteSectionDto value) {
        this.section = value;
    }

    /**
     * Gets the value of the page property.
     * 
     * @return
     *     possible object is
     *     {@link OneNotePageDto }
     *     
     */
    public OneNotePageDto getPage() {
        return page;
    }

    /**
     * Sets the value of the page property.
     * 
     * @param value
     *     allowed object is
     *     {@link OneNotePageDto }
     *     
     */
    public void setPage(OneNotePageDto value) {
        this.page = value;
    }

}
