
package com.siebel.customui;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.siebel.xml.siebelsearchgetautosuggestions_output.GetAutoSuggestionsOutputHeader;


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
 *         &lt;element ref="{http://www.siebel.com/xml/SiebelSearchGetAutoSuggestions_Output}GetAutoSuggestions_OutputHeader"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getAutoSuggestionsOutputHeader" })
@XmlRootElement(name = "SiebelSearchService_GETAUTOSUGGESTIONS_Output")
public class SiebelSearchServiceGETAUTOSUGGESTIONSOutput {

    @XmlElement(name = "GetAutoSuggestions_OutputHeader",
                namespace = "http://www.siebel.com/xml/SiebelSearchGetAutoSuggestions_Output", required = true)
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
