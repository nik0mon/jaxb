package com.laasie;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPMessage;

import com.google.gson.Gson;
import altres.wsdl.OTAHotelResNotifRQ;
/**
 * Hello world!
 */
public final class App {
    public static void main(String[] args) throws IOException{
        // SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        // Schema schema = schemaFactory.newSchema(new File("/Users/nik0mon/workspace/staywanderful/java/new-alt-reservation/new-alt-reservation/src/main/resources/altres.wsdl"));
        // // unmarshaller.setSchema(schema);
        byte[] bytes = Files.readAllBytes(
                Paths.get("/Users/nikomon/workspace/staywanderful/java/wips/jaxb/src/main/resources/request.xml")
        );
        String jsonPayload = SoapBodytoJson(bytes);
        System.out.println(jsonPayload);
     }

     public static String SoapBodytoJson(byte[] payloadBytes){
        String jsonPayload = "";
        try {
            SOAPMessage message = MessageFactory
                .newInstance(SOAPConstants.SOAP_1_2_PROTOCOL)
                .createMessage(null, new ByteArrayInputStream(payloadBytes));
            SOAPBody body = message.getSOAPBody();
            JAXBContext context = JAXBContext.newInstance(OTAHotelResNotifRQ.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<OTAHotelResNotifRQ> node = unmarshaller
                .unmarshal(body.getElementsByTagName("OTA_HotelResNotifRQ").item(0), OTAHotelResNotifRQ.class);
            OTAHotelResNotifRQ payload = node.getValue();
            Gson gson = new Gson();
            jsonPayload = gson.toJson(payload);
        }
        catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return jsonPayload;
     }
}
