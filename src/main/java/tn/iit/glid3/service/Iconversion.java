package tn.iit.glid3.service;

import java.util.List;

import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface Iconversion {
	double Convert(String curr, double montant);

	List<String> getcurrency();
}
