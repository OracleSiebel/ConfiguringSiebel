
package com.siebel.customui;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.siebel.xml.siebelsearchcreateschema_input.CreateSchemaInputHeader;


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
 *         &lt;element ref="{http://www.siebel.com/xml/SiebelSearchCreateSchema_Input}CreateSchema_InputHeader"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "createSchemaInputHeader" })
@XmlRootElement(name = "SiebelSearchService_CREATESCHEMA_Input")
public class SiebelSearchServiceCREATESCHEMAInput {

    @XmlElement(name = "CreateSchema_InputHeader",
                namespace = "http://www.siebel.com/xml/SiebelSearchCreateSchema_Input", required = true)
    protected CreateSchemaInputHeader createSchemaInputHeader;

    /**
     * Gets the value of the createSchemaInputHeader property.
     *
     * @return
     *     possible object is
     *     {@link CreateSchemaInputHeader }
     *
     */
    public CreateSchemaInputHeader getCreateSchemaInputHeader() {
        return createSchemaInputHeader;
    }

    /**
     * Sets the value of the createSchemaInputHeader property.
     *
     * @param value
     *     allowed object is
     *     {@link CreateSchemaInputHeader }
     *
     */
    public void setCreateSchemaInputHeader(CreateSchemaInputHeader value) {
        this.createSchemaInputHeader = value;
    }

}
