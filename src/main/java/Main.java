import Comparator.Comparator;
import Comparator.FactoryMethod;
import Parser.SaxParser;
import Service.ArgumentProcess;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            ArgumentProcess arguments = new ArgumentProcess(args);
            FactoryMethod factoryMethod = new FactoryMethod();
            Comparator comparator = factoryMethod.getComparator(arguments);
            comparator.setMask(arguments.getMask());

            new SaxParser(comparator, arguments.getInputFileName());

        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println(e.getMessage());
        }
    }
}
