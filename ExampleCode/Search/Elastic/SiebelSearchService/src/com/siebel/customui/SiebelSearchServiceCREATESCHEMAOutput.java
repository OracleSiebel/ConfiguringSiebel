
package com.siebel.customui;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.siebel.xml.siebelsearchcreateschema_output.CreateSchemaStatus;


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
 *         &lt;element ref="{http://www.siebel.com/xml/SiebelSearchCreateSchema_Output}CreateSchema_Status"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "createSchemaStatus" })
@XmlRootElement(name = "SiebelSearchService_CREATESCHEMA_Output")
public class SiebelSearchServiceCREATESCHEMAOutput {

    @XmlElement(name = "CreateSchema_Status", namespace = "http://www.siebel.com/xml/SiebelSearchCreateSchema_Output",
                required = true)
    protected CreateSchemaStatus createSchemaStatus;

    /**
     * Gets the value of the createSchemaStatus property.
     *
     * @return
     *     possible object is
     *     {@link CreateSchemaStatus }
     *
     */
    public CreateSchemaStatus getCreateSchemaStatus() {
        return createSchemaStatus;
    }

    /**
     * Sets the value of the createSchemaStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link CreateSchemaStatus }
     *
     */
    public void setCreateSchemaStatus(CreateSchemaStatus value) {
        this.createSchemaStatus = value;
    }

}
