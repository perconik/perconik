
package com.gratex.perconik.services.vs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdeFindOperationDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdeFindOperationDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}IdeSlnPrjEventDto">
 *       &lt;sequence>
 *         &lt;element name="FindWhat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MatchCase" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="MatchWholeWord" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SearchSubfolders" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Lookin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatternSyntax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FileTypes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalFilesSearched" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ResultsPerFiles" type="{http://www.gratex.com/PerConIk/IActivitySvc}ArrayOfIdeFindFileResultDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdeFindOperationDto", propOrder = {
    "findWhat",
    "matchCase",
    "matchWholeWord",
    "searchSubfolders",
    "lookin",
    "patternSyntax",
    "fileTypes",
    "totalFilesSearched",
    "resultsPerFiles"
})
public class IdeFindOperationDto
    extends IdeSlnPrjEventDto
{

    @XmlElement(name = "FindWhat")
    protected String findWhat;
    @XmlElement(name = "MatchCase", required = true, type = Boolean.class, nillable = true)
    protected Boolean matchCase;
    @XmlElement(name = "MatchWholeWord", required = true, type = Boolean.class, nillable = true)
    protected Boolean matchWholeWord;
    @XmlElement(name = "SearchSubfolders", required = true, type = Boolean.class, nillable = true)
    protected Boolean searchSubfolders;
    @XmlElement(name = "Lookin")
    protected String lookin;
    @XmlElement(name = "PatternSyntax")
    protected String patternSyntax;
    @XmlElement(name = "FileTypes")
    protected String fileTypes;
    @XmlElement(name = "TotalFilesSearched", required = true, type = Integer.class, nillable = true)
    protected Integer totalFilesSearched;
    @XmlElement(name = "ResultsPerFiles")
    protected ArrayOfIdeFindFileResultDto resultsPerFiles;

    /**
     * Gets the value of the findWhat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFindWhat() {
        return findWhat;
    }

    /**
     * Sets the value of the findWhat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFindWhat(String value) {
        this.findWhat = value;
    }

    /**
     * Gets the value of the matchCase property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMatchCase() {
        return matchCase;
    }

    /**
     * Sets the value of the matchCase property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMatchCase(Boolean value) {
        this.matchCase = value;
    }

    /**
     * Gets the value of the matchWholeWord property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMatchWholeWord() {
        return matchWholeWord;
    }

    /**
     * Sets the value of the matchWholeWord property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMatchWholeWord(Boolean value) {
        this.matchWholeWord = value;
    }

    /**
     * Gets the value of the searchSubfolders property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSearchSubfolders() {
        return searchSubfolders;
    }

    /**
     * Sets the value of the searchSubfolders property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSearchSubfolders(Boolean value) {
        this.searchSubfolders = value;
    }

    /**
     * Gets the value of the lookin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLookin() {
        return lookin;
    }

    /**
     * Sets the value of the lookin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLookin(String value) {
        this.lookin = value;
    }

    /**
     * Gets the value of the patternSyntax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatternSyntax() {
        return patternSyntax;
    }

    /**
     * Sets the value of the patternSyntax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatternSyntax(String value) {
        this.patternSyntax = value;
    }

    /**
     * Gets the value of the fileTypes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileTypes() {
        return fileTypes;
    }

    /**
     * Sets the value of the fileTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileTypes(String value) {
        this.fileTypes = value;
    }

    /**
     * Gets the value of the totalFilesSearched property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalFilesSearched() {
        return totalFilesSearched;
    }

    /**
     * Sets the value of the totalFilesSearched property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalFilesSearched(Integer value) {
        this.totalFilesSearched = value;
    }

    /**
     * Gets the value of the resultsPerFiles property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfIdeFindFileResultDto }
     *     
     */
    public ArrayOfIdeFindFileResultDto getResultsPerFiles() {
        return resultsPerFiles;
    }

    /**
     * Sets the value of the resultsPerFiles property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfIdeFindFileResultDto }
     *     
     */
    public void setResultsPerFiles(ArrayOfIdeFindFileResultDto value) {
        this.resultsPerFiles = value;
    }

}
