package gov.weather;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.StringReader;

import static org.junit.Assert.*;

public class NdfdXMLBindingStubTest {
    @Test
    public void NDFDgenLatLonList() throws Exception {
        NdfdXMLBindingStub binding = (NdfdXMLBindingStub)
                new NdfdXMLLocator().getndfdXMLPort();

        String response = binding.latLonListZipCode("53711");
        //assertEquals("result did not match what was expected","43.0798,-89.3875",response);
        String latLong = unmarshallResult(response);
        assertEquals("43.0798,-89.3875",latLong);
    }

    private String unmarshallResult(String response) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(DwmlType.class);
        try{
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            DwmlType dwml = (DwmlType) jaxbUnmarshaller.unmarshal(new StringReader(response));
            return dwml.getLatLonList();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}