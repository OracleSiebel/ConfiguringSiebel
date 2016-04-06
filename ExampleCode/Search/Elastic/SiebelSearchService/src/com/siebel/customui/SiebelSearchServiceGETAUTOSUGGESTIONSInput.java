
package com.siebel.customui;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.siebel.xml.siebelsearchgetautosuggestions_input.GetAutoSuggestionsInputHeader;


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
 *         &lt;element ref="{http://www.siebel.com/xml/SiebelSearchGetAutoSuggestions_Input}GetAutoSuggestions_InputHeader"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getAutoSuggestionsInputHeader" })
@XmlRootElement(name = "SiebelSearchService_GETAUTOSUGGESTIONS_Input")
public class SiebelSearchServiceGETAUTOSUGGESTIONSInput {

    @XmlElement(name = "GetAutoSuggestions_InputHeader",
                namespace = "http://www.siebel.com/xml/SiebelSearchGetAutoSuggestions_Input", required = true)
    protected GetAutoSuggestionsInputHeader getAutoSuggestionsInputHeader;

    /**
     * Gets the value of the getAutoSuggestionsInputHeader property.
     *
     * @return
     *     possible object is
     *     {@link GetAutoSuggestionsInputHeader }
     *
     */
    public GetAutoSuggestionsInputHeader getGetAutoSuggestionsInputHeader() {
        return getAutoSuggestionsInputHeader;
    }

    /**
     * Sets the value of the getAutoSuggestionsInputHeader property.
     *
     * @param value
     *     allowed object is
     *     {@link GetAutoSuggestionsInputHeader }
     *
     */
    public void setGetAutoSuggestionsInputHeader(GetAutoSuggestionsInputHeader value) {
        this.getAutoSuggestionsInputHeader = value;
    }

}
