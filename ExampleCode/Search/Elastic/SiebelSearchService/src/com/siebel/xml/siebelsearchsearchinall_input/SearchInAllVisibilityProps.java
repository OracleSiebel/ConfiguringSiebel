
package com.siebel.xml.siebelsearchsearchinall_input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchInAll_VisibilityProps complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SearchInAll_VisibilityProps">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VisibilityField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VisibilityValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchInAll_VisibilityProps", propOrder = { "visibilityField", "visibilityValue" })
public class SearchInAllVisibilityProps {

    @XmlElement(name = "VisibilityField", required = true)
    protected String visibilityField;
    @XmlElement(name = "VisibilityValue", required = true)
    protected String visibilityValue;

    /**
     * Gets the value of the visibilityField property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVisibilityField() {
        return visibilityField;
    }

    /**
     * Sets the value of the visibilityField property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVisibilityField(String value) {
        this.visibilityField = value;
    }

    /**
     * Gets the value of the visibilityValue property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVisibilityValue() {
        return visibilityValue;
    }

    /**
     * Sets the value of the visibilityValue property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVisibilityValue(String value) {
        this.visibilityValue = value;
    }

}
