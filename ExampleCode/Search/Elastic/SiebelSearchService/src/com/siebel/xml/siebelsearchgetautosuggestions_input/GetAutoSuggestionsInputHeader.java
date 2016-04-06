
package com.siebel.xml.siebelsearchgetautosuggestions_input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetAutoSuggestions_InputHeader complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="GetAutoSuggestions_InputHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SearchText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CategoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumOfSuggestions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GetAutoSuggestions_CustomProps" type="{http://www.siebel.com/xml/SiebelSearchGetAutoSuggestions_Input}GetAutoSuggestions_CustomProps"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAutoSuggestions_InputHeader", propOrder = {
         "searchText", "categoryName", "numOfSuggestions", "getAutoSuggestionsCustomProps" })
public class GetAutoSuggestionsInputHeader {

    @XmlElement(name = "SearchText")
    protected String searchText;
    @XmlElement(name = "CategoryName")
    protected String categoryName;
    @XmlElement(name = "NumOfSuggestions")
    protected String numOfSuggestions;
    @XmlElement(name = "GetAutoSuggestions_CustomProps", required = true)
    protected GetAutoSuggestionsCustomProps getAutoSuggestionsCustomProps;

    /**
     * Gets the value of the searchText property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * Sets the value of the searchText property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSearchText(String value) {
        this.searchText = value;
    }

    /**
     * Gets the value of the categoryName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the value of the categoryName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
    }

    /**
     * Gets the value of the numOfSuggestions property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNumOfSuggestions() {
        return numOfSuggestions;
    }

    /**
     * Sets the value of the numOfSuggestions property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNumOfSuggestions(String value) {
        this.numOfSuggestions = value;
    }

    /**
     * Gets the value of the getAutoSuggestionsCustomProps property.
     *
     * @return
     *     possible object is
     *     {@link GetAutoSuggestionsCustomProps }
     *
     */
    public GetAutoSuggestionsCustomProps getGetAutoSuggestionsCustomProps() {
        return getAutoSuggestionsCustomProps;
    }

    /**
     * Sets the value of the getAutoSuggestionsCustomProps property.
     *
     * @param value
     *     allowed object is
     *     {@link GetAutoSuggestionsCustomProps }
     *
     */
    public void setGetAutoSuggestionsCustomProps(GetAutoSuggestionsCustomProps value) {
        this.getAutoSuggestionsCustomProps = value;
    }

}
