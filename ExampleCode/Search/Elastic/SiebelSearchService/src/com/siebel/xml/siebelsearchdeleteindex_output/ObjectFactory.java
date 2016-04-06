
package com.siebel.xml.siebelsearchdeleteindex_output;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.siebel.xml.siebelsearchdeleteindex_output package.
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

    private final static QName _DeleteIndexStatus_QNAME =
        new QName("http://www.siebel.com/xml/SiebelSearchDeleteIndex_Output", "DeleteIndex_Status");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.siebel.xml.siebelsearchdeleteindex_output
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteIndexStatus }
     *
     */
    public DeleteIndexStatus createDeleteIndexStatus() {
        return new DeleteIndexStatus();
    }

    /**
     * Create an instance of {@link DeleteIndexStatusTopElmt }
     *
     */
    public DeleteIndexStatusTopElmt createDeleteIndexStatusTopElmt() {
        return new DeleteIndexStatusTopElmt();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteIndexStatus }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.siebel.com/xml/SiebelSearchDeleteIndex_Output", name = "DeleteIndex_Status")
    public JAXBElement<DeleteIndexStatus> createDeleteIndexStatus(DeleteIndexStatus value) {
        return new JAXBElement<DeleteIndexStatus>(_DeleteIndexStatus_QNAME, DeleteIndexStatus.class, null, value);
    }

}
