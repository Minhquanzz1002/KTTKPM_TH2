package vn.edu.iuh.receptionistsendermq.helper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import vn.edu.iuh.receptionistsendermq.data.Person;

import java.io.StringWriter;

public class XMLConvert<T> {
    private T type;

    public XMLConvert(T type) {
        this.type = type;
    }

    public String objectToXML(T obj) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(type.getClass());
        Marshaller ms = ctx.createMarshaller();
        StringWriter sw = new StringWriter();
        ms.marshal(obj, sw);
        return sw.toString();
    }

}
