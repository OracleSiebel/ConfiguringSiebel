
package com.siebel.customui;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.siebel.xml.siebelsearchsearchinall_input.SearchInCategoryInputHeader;


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
 *         &lt;element ref="{http://www.siebel.com/xml/SiebelSearchSearchInAll_Input}SearchInCategory_InputHeader"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "searchInCategoryInputHeader" })
@XmlRootElement(name = "SiebelSearchService_SEARCHINALL_Input")
public class SiebelSearchServiceSEARCHINALLInput {

    @XmlElement(name = "SearchInCategory_InputHeader",
                namespace = "http://www.siebel.com/xml/SiebelSearchSearchInAll_Input", required = true)
    protected SearchInCategoryInputHeader searchInCategoryInputHeader;

    /**
     * Gets the value of the searchInCategoryInputHeader property.
     *
     * @return
     *     possible object is
     *     {@link SearchInCategoryInputHeader }
     *
     */
    public SearchInCategoryInputHeader getSearchInCategoryInputHeader() {
        return searchInCategoryInputHeader;
    }

    /**
     * Sets the value of the searchInCategoryInputHeader property.
     *
     * @param value
     *     allowed object is
     *     {@link SearchInCategoryInputHeader }
     *
     */
    public void setSearchInCategoryInputHeader(SearchInCategoryInputHeader value) {
        this.searchInCategoryInputHeader = value;
    }

}
