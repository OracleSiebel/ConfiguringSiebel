
package com.siebel.xml.siebelsearchdoindex_input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListOfDoIndex_FieldAndValues complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ListOfDoIndex_FieldAndValues">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DoIndex_FieldAndValues" type="{http://www.siebel.com/xml/SiebelSearchDoIndex_Input}DoIndex_FieldAndValues" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOfDoIndex_FieldAndValues", propOrder = { "doIndexFieldAndValues" })
public class ListOfDoIndexFieldAndValues {

    @XmlElement(name = "DoIndex_FieldAndValues", required = true)
    protected List<DoIndexFieldAndValues> doIndexFieldAndValues;

    /**
     * Gets the value of the doIndexFieldAndValues property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the doIndexFieldAndValues property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDoIndexFieldAndValues().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DoIndexFieldAndValues }
     *
     *
     */
    public List<DoIndexFieldAndValues> getDoIndexFieldAndValues() {
        if (doIndexFieldAndValues == null) {
            doIndexFieldAndValues = new ArrayList<DoIndexFieldAndValues>();
        }
        return this.doIndexFieldAndValues;
    }

}
