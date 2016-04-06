
package com.siebel.xml.siebelsearchdoindex_output;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.siebel.xml.siebelsearchdoindex_output package.
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

    private final static QName _DoIndexStatus_QNAME =
        new QName("http://www.siebel.com/xml/SiebelSearchDoIndex_Output", "DoIndex_Status");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.siebel.xml.siebelsearchdoindex_output
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DoIndexStatus }
     *
     */
    public DoIndexStatus createDoIndexStatus() {
        return new DoIndexStatus();
    }

    /**
     * Create an instance of {@link DoIndexStatusTopElmt }
     *
     */
    public DoIndexStatusTopElmt createDoIndexStatusTopElmt() {
        return new DoIndexStatusTopElmt();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoIndexStatus }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.siebel.com/xml/SiebelSearchDoIndex_Output", name = "DoIndex_Status")
    public JAXBElement<DoIndexStatus> createDoIndexStatus(DoIndexStatus value) {
        return new JAXBElement<DoIndexStatus>(_DoIndexStatus_QNAME, DoIndexStatus.class, null, value);
    }

}
