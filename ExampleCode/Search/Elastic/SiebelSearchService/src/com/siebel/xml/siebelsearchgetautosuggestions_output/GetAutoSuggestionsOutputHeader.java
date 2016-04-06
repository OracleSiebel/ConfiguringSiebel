
package com.siebel.xml.siebelsearchgetautosuggestions_output;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetAutoSuggestions_OutputHeader complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="GetAutoSuggestions_OutputHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumOfSuggestions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GetAutoSuggestions_Suggestions" type="{http://www.siebel.com/xml/SiebelSearchGetAutoSuggestions_Output}GetAutoSuggestions_Suggestions" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAutoSuggestions_OutputHeader", propOrder = { "numOfSuggestions", "getAutoSuggestionsSuggestions" })
public class GetAutoSuggestionsOutputHeader {

    @XmlElement(name = "NumOfSuggestions")
    protected String numOfSuggestions;
    @XmlElement(name = "GetAutoSuggestions_Suggestions")
    protected List<GetAutoSuggestionsSuggestions> getAutoSuggestionsSuggestions;

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
     * Gets the value of the getAutoSuggestionsSuggestions property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the getAutoSuggestionsSuggestions property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGetAutoSuggestionsSuggestions().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetAutoSuggestionsSuggestions }
     *
     *
     */
    public List<GetAutoSuggestionsSuggestions> getGetAutoSuggestionsSuggestions() {
        if (getAutoSuggestionsSuggestions == null) {
            getAutoSuggestionsSuggestions = new ArrayList<GetAutoSuggestionsSuggestions>();
        }
        return this.getAutoSuggestionsSuggestions;
    }

}
