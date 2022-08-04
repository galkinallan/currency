package ru.GalkinAllan.Currency.parser;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import ru.GalkinAllan.Currency.models.Currency;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyParserXml implements CurrencyParser {
    
    @Override
    public List<Currency> parse(String ratesAsString) {
        var rates = new ArrayList<Currency>();

        var dbf = DocumentBuilderFactory.newInstance();
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            var db = dbf.newDocumentBuilder();

            try (var reader = new StringReader(ratesAsString)) {
                Document doc = db.parse(new InputSource(reader));
                doc.getDocumentElement().normalize();

                NodeList list = doc.getElementsByTagName("Valute");

                for (var valuteIdx = 0; valuteIdx < list.getLength(); valuteIdx++) {
                    var node = list.item(valuteIdx);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        var element = (Element) node;
                        Currency rate = new Currency(node.getTextContent().substring(0,7),
                                element.getElementsByTagName("NumCode").item(0).getTextContent(),
                                element.getElementsByTagName("CharCode").item(0).getTextContent(),
                                element.getElementsByTagName("Nominal").item(0).getTextContent(),
                                element.getElementsByTagName("Name").item(0).getTextContent(),
                                element.getElementsByTagName("Value").item(0).getTextContent());
                        rates.add(rate);
                    }
                }
            }
        } catch (Exception ex) {
            throw new CurrencyParsingException(ex);
        }
        return rates;
    }
}
