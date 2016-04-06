
package com.siebel.customui;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.siebel.xml.siebelsearchdoindex_output.DoIndexStatus;


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
 *         &lt;element ref="{http://www.siebel.com/xml/SiebelSearchDoIndex_Output}DoIndex_Status"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "doIndexStatus" })
@XmlRootElement(name = "SiebelSearchService_DOINDEX_Output")
public class SiebelSearchServiceDOINDEXOutput {

    @XmlElement(name = "DoIndex_Status", namespace = "http://www.siebel.com/xml/SiebelSearchDoIndex_Output",
                required = true)
    protected DoIndexStatus doIndexStatus;

    /**
     * Gets the value of the doIndexStatus property.
     *
     * @return
     *     possible object is
     *     {@link DoIndexStatus }
     *
     */
    public DoIndexStatus getDoIndexStatus() {
        return doIndexStatus;
    }

    /**
     * Sets the value of the doIndexStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link DoIndexStatus }
     *
     */
    public void setDoIndexStatus(DoIndexStatus value) {
        this.doIndexStatus = value;
    }

}
