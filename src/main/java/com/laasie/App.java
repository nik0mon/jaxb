package com.laasie;

import java.io.File;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import com.google.gson.Gson;

import org.xml.sax.SAXException;

import altres.wsdl.OTAHotelResNotifRQ;
/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws JAXBException, SAXException{
        JAXBContext context = JAXBContext.newInstance(OTAHotelResNotifRQ.class);        
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        // Schema schema = schemaFactory.newSchema(new File("/Users/nik0mon/workspace/staywanderful/java/new-alt-reservation/new-alt-reservation/src/main/resources/altres.wsdl"));
        // // unmarshaller.setSchema(schema);
        File request = new File("/Users/nikomon/workspace/staywanderful/java/wips/jaxb/src/main/resources/request.xml");
        JAXBElement<OTAHotelResNotifRQ> root = unmarshaller.unmarshal(new StreamSource(request), OTAHotelResNotifRQ.class);
        OTAHotelResNotifRQ test = root.getValue();
        System.out.println(test);
        Gson gson = new Gson();
        String val = gson.toJson(test);
        System.out.println(val.toString());
     }
}
