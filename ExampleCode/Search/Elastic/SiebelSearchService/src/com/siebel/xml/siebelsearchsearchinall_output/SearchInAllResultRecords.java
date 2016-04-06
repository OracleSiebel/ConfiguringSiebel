
package com.siebel.xml.siebelsearchsearchinall_output;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchInAll_ResultRecords complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SearchInAll_ResultRecords">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CategoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocumentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Summary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchInAll_FieldAndValue" type="{http://www.siebel.com/xml/SiebelSearchSearchInAll_Output}SearchInAll_FieldAndValue" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchInAll_ResultRecords", propOrder = {
         "rowID", "categoryName", "documentType", "title", "summary", "url", "searchInAllFieldAndValue"
    })
public class SearchInAllResultRecords {

    @XmlElement(name = "RowID")
    protected String rowID;
    @XmlElement(name = "CategoryName")
    protected String categoryName;
    @XmlElement(name = "DocumentType")
    protected String documentType;
    @XmlElement(name = "Title")
    protected String title;
    @XmlElement(name = "Summary")
    protected String summary;
    @XmlElement(name = "URL")
    protected String url;
    @XmlElement(name = "SearchInAll_FieldAndValue")
    protected List<SearchInAllFieldAndValue> searchInAllFieldAndValue;

    /**
     * Gets the value of the rowID property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRowID() {
        return rowID;
    }

    /**
     * Sets the value of the rowID property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRowID(String value) {
        this.rowID = value;
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

    /**
     * Gets the value of the documentType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * Sets the value of the documentType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDocumentType(String value) {
        this.documentType = value;
    }

    /**
     * Gets the value of the title property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the summary property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the value of the summary property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSummary(String value) {
        this.summary = value;
    }

    /**
     * Gets the value of the url property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getURL() {
        return url;
    }

    /**
     * Sets the value of the url property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setURL(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the searchInAllFieldAndValue property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchInAllFieldAndValue property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchInAllFieldAndValue().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchInAllFieldAndValue }
     *
     *
     */
    public List<SearchInAllFieldAndValue> getSearchInAllFieldAndValue() {
        if (searchInAllFieldAndValue == null) {
            searchInAllFieldAndValue = new ArrayList<SearchInAllFieldAndValue>();
        }
        return this.searchInAllFieldAndValue;
    }

}
