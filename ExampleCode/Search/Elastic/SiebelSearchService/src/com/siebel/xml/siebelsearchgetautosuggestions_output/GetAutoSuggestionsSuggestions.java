
package com.siebel.xml.siebelsearchgetautosuggestions_output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetAutoSuggestions_Suggestions complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="GetAutoSuggestions_Suggestions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SuggestedText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CategoryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAutoSuggestions_Suggestions", propOrder = { "suggestedText", "categoryName" })
public class GetAutoSuggestionsSuggestions {

    @XmlElement(name = "SuggestedText", required = true)
    protected String suggestedText;
    @XmlElement(name = "CategoryName", required = true)
    protected String categoryName;

    /**
     * Gets the value of the suggestedText property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSuggestedText() {
        return suggestedText;
    }

    /**
     * Sets the value of the suggestedText property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSuggestedText(String value) {
        this.suggestedText = value;
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

}
