package tn.iit.glid3.service;

import jakarta.jws.WebService;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



@WebService(endpointInterface = "tn.iit.glid3.service.Iconversion")
public class Conversion implements Iconversion {

	List<String> currencies = new ArrayList<>();
	List<Double> rates = new ArrayList<>();

	public Conversion() {
		prepare();
	}

	public void prepare() {
		try {
			String filePath = getClass().getClassLoader().getResource("eurofxref-daily.xml").getPath();

			File xmlFile = new File(filePath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);

			document.getDocumentElement().normalize();

			NodeList cubeList = document.getElementsByTagName("Cube");

			for (int i = 0; i < cubeList.getLength(); i++) {
				Node node = cubeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element cube = (Element) node;

					String currency = cube.getAttribute("currency");
					String rate = cube.getAttribute("rate");

					if (!currency.isEmpty()) {
						currencies.add(currency);
						rates.add(Double.parseDouble(rate));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public double Convert(String curr, double montant) {
		if (currencies.contains(curr)) {
			double rate = rates.get(currencies.indexOf(curr));
			return rate * montant;
		} else {
			return 0.0;
		}
	}

	@Override
	public List<String> getcurrency() {
		return currencies;
	}
}

