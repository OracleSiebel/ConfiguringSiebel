
package com.siebel.xml.siebelsearchgetautosuggestions_output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetAutoSuggestions_OutputHeaderTopElmt complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="GetAutoSuggestions_OutputHeaderTopElmt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetAutoSuggestions_OutputHeader" type="{http://www.siebel.com/xml/SiebelSearchGetAutoSuggestions_Output}GetAutoSuggestions_OutputHeader"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAutoSuggestions_OutputHeaderTopElmt", propOrder = { "getAutoSuggestionsOutputHeader" })
public class GetAutoSuggestionsOutputHeaderTopElmt {

    @XmlElement(name = "GetAutoSuggestions_OutputHeader", required = true)
    protected GetAutoSuggestionsOutputHeader getAutoSuggestionsOutputHeader;

    /**
     * Gets the value of the getAutoSuggestionsOutputHeader property.
     *
     * @return
     *     possible object is
     *     {@link GetAutoSuggestionsOutputHeader }
     *
     */
    public GetAutoSuggestionsOutputHeader getGetAutoSuggestionsOutputHeader() {
        return getAutoSuggestionsOutputHeader;
    }

    /**
     * Sets the value of the getAutoSuggestionsOutputHeader property.
     *
     * @param value
     *     allowed object is
     *     {@link GetAutoSuggestionsOutputHeader }
     *
     */
    public void setGetAutoSuggestionsOutputHeader(GetAutoSuggestionsOutputHeader value) {
        this.getAutoSuggestionsOutputHeader = value;
    }

}
