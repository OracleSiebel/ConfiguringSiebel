
package com.siebel.xml.siebelsearchdoindex_input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListOfDoIndex_Input complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ListOfDoIndex_Input">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DoIndex_InputHeader" type="{http://www.siebel.com/xml/SiebelSearchDoIndex_Input}DoIndex_InputHeader" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOfDoIndex_Input", propOrder = { "doIndexInputHeader" })
public class ListOfDoIndexInput {

    @XmlElement(name = "DoIndex_InputHeader", required = true)
    protected List<DoIndexInputHeader> doIndexInputHeader;

    /**
     * Gets the value of the doIndexInputHeader property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the doIndexInputHeader property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDoIndexInputHeader().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DoIndexInputHeader }
     *
     *
     */
    public List<DoIndexInputHeader> getDoIndexInputHeader() {
        if (doIndexInputHeader == null) {
            doIndexInputHeader = new ArrayList<DoIndexInputHeader>();
        }
        return this.doIndexInputHeader;
    }

}
