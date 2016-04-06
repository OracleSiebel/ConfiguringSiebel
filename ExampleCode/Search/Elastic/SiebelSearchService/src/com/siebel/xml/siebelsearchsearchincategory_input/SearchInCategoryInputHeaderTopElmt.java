
package com.siebel.xml.siebelsearchsearchincategory_input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchInCategory_InputHeaderTopElmt complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SearchInCategory_InputHeaderTopElmt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SearchInCategory_InputHeader" type="{http://www.siebel.com/xml/SiebelSearchSearchInCategory_Input}SearchInCategory_InputHeader"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchInCategory_InputHeaderTopElmt", propOrder = { "searchInCategoryInputHeader" })
public class SearchInCategoryInputHeaderTopElmt {

    @XmlElement(name = "SearchInCategory_InputHeader", required = true)
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
