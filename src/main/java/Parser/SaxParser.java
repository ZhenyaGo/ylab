package Parser;
import Comparator.AbstractComparator;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SaxParser {
    public SaxParser(AbstractComparator comparator, String fileName) throws IOException, ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(false);
        SAXParser parser = factory.newSAXParser();
        NodeParser handler = new NodeParser(comparator);
        parser.parse(fileName, handler);
    }
}
