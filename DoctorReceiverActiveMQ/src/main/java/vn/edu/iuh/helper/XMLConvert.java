package vn.edu.iuh.helper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;

public class XMLConvert<T> {
    private T type;

    public XMLConvert(T type) {
        this.type = type;
    }

    public T xmlToObject(String xml) throws JAXBException {
        T obj = null;
        JAXBContext ctx = JAXBContext.newInstance(type.getClass());
        Unmarshaller ms = ctx.createUnmarshaller();
        obj = (T) ms.unmarshal(new StringReader(xml));
        return obj;
    }

}
