
package com.siebel.customui;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.siebel.xml.siebelsearchdoindex_input.ListOfDoIndexInput;


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
 *         &lt;element ref="{http://www.siebel.com/xml/SiebelSearchDoIndex_Input}ListOfDoIndex_Input"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "listOfDoIndexInput" })
@XmlRootElement(name = "SiebelSearchService_DOINDEX_Input")
public class SiebelSearchServiceDOINDEXInput {

    @XmlElement(name = "ListOfDoIndex_Input", namespace = "http://www.siebel.com/xml/SiebelSearchDoIndex_Input",
                required = true)
    protected ListOfDoIndexInput listOfDoIndexInput;

    /**
     * Gets the value of the listOfDoIndexInput property.
     *
     * @return
     *     possible object is
     *     {@link ListOfDoIndexInput }
     *
     */
    public ListOfDoIndexInput getListOfDoIndexInput() {
        return listOfDoIndexInput;
    }

    /**
     * Sets the value of the listOfDoIndexInput property.
     *
     * @param value
     *     allowed object is
     *     {@link ListOfDoIndexInput }
     *
     */
    public void setListOfDoIndexInput(ListOfDoIndexInput value) {
        this.listOfDoIndexInput = value;
    }

}
