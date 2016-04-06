
package com.siebel.customui;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.siebel.xml.siebelsearchsearchinall_output.SearchInAllOutputHeader;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.siebel.com/xml/SiebelSearchSearchInAll_Output}SearchInAll_OutputHeader"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "searchInAllOutputHeader" })
@XmlRootElement(name = "SiebelSearchService_SEARCHINALL_Output")
public class SiebelSearchServiceSEARCHINALLOutput {

    @XmlElement(name = "SearchInAll_OutputHeader",
                namespace = "http://www.siebel.com/xml/SiebelSearchSearchInAll_Output", required = true)
    protected SearchInAllOutputHeader searchInAllOutputHeader;

    /**
     * Gets the value of the searchInAllOutputHeader property.
     *
     * @return
     *     possible object is
     *     {@link SearchInAllOutputHeader }
     *
     */
    public SearchInAllOutputHeader getSearchInAllOutputHeader() {
        return searchInAllOutputHeader;
    }

    /**
     * Sets the value of the searchInAllOutputHeader property.
     *
     * @param value
     *     allowed object is
     *     {@link SearchInAllOutputHeader }
     *
     */
    public void setSearchInAllOutputHeader(SearchInAllOutputHeader value) {
        this.searchInAllOutputHeader = value;
    }

}
