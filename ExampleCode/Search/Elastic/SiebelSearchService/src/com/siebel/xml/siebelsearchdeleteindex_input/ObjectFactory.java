
package com.siebel.xml.siebelsearchdeleteindex_input;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.siebel.xml.siebelsearchdeleteindex_input package.
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

    private final static QName _DeleteIndexInputParam_QNAME =
        new QName("http://www.siebel.com/xml/SiebelSearchDeleteIndex_Input", "DeleteIndex_InputParam");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.siebel.xml.siebelsearchdeleteindex_input
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteIndexInputParam }
     *
     */
    public DeleteIndexInputParam createDeleteIndexInputParam() {
        return new DeleteIndexInputParam();
    }

    /**
     * Create an instance of {@link DeleteIndexCustomProps }
     *
     */
    public DeleteIndexCustomProps createDeleteIndexCustomProps() {
        return new DeleteIndexCustomProps();
    }

    /**
     * Create an instance of {@link DeleteIndexInputParamTopElmt }
     *
     */
    public DeleteIndexInputParamTopElmt createDeleteIndexInputParamTopElmt() {
        return new DeleteIndexInputParamTopElmt();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteIndexInputParam }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.siebel.com/xml/SiebelSearchDeleteIndex_Input",
                    name = "DeleteIndex_InputParam")
    public JAXBElement<DeleteIndexInputParam> createDeleteIndexInputParam(DeleteIndexInputParam value) {
        return new JAXBElement<DeleteIndexInputParam>(_DeleteIndexInputParam_QNAME, DeleteIndexInputParam.class, null,
                                                      value);
    }

}
