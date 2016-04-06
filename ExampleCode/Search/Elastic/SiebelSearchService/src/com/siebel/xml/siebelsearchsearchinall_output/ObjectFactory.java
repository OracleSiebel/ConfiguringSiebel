
package com.siebel.xml.siebelsearchsearchinall_output;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.siebel.xml.siebelsearchsearchinall_output package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SearchInAllOutputHeader_QNAME =
        new QName("http://www.siebel.com/xml/SiebelSearchSearchInAll_Output", "SearchInAll_OutputHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.siebel.xml.siebelsearchsearchinall_output
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchInAllOutputHeader }
     *
     */
    public SearchInAllOutputHeader createSearchInAllOutputHeader() {
        return new SearchInAllOutputHeader();
    }

    /**
     * Create an instance of {@link SearchInAllFieldAndValue }
     *
     */
    public SearchInAllFieldAndValue createSearchInAllFieldAndValue() {
        return new SearchInAllFieldAndValue();
    }

    /**
     * Create an instance of {@link SearchInAllOutputHeaderTopElmt }
     *
     */
    public SearchInAllOutputHeaderTopElmt createSearchInAllOutputHeaderTopElmt() {
        return new SearchInAllOutputHeaderTopElmt();
    }

    /**
     * Create an instance of {@link SearchInAllResultRecords }
     *
     */
    public SearchInAllResultRecords createSearchInAllResultRecords() {
        return new SearchInAllResultRecords();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchInAllOutputHeader }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.siebel.com/xml/SiebelSearchSearchInAll_Output",
                    name = "SearchInAll_OutputHeader")
    public JAXBElement<SearchInAllOutputHeader> createSearchInAllOutputHeader(SearchInAllOutputHeader value) {
        return new JAXBElement<SearchInAllOutputHeader>(_SearchInAllOutputHeader_QNAME, SearchInAllOutputHeader.class,
                                                        null, value);
    }

}
