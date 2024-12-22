package tn.iit.glid3;

import jakarta.xml.ws.Endpoint;
import tn.iit.glid3.service.*;

public class ConversionPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/CurrencyConverter/?wsdl", new Conversion());
        System.out.println("SOAP Web Service is running at http://localhost:8080/CurrencyConverter/?wsdl");
    }
}
