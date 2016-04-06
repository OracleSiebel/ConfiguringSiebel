
package com.siebel.xml.siebelsearchsearchinall_output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchInAll_OutputHeaderTopElmt complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SearchInAll_OutputHeaderTopElmt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SearchInAll_OutputHeader" type="{http://www.siebel.com/xml/SiebelSearchSearchInAll_Output}SearchInAll_OutputHeader" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchInAll_OutputHeaderTopElmt", propOrder = { "searchInAllOutputHeader" })
public class SearchInAllOutputHeaderTopElmt {

    @XmlElement(name = "SearchInAll_OutputHeader")
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
