package Parser;

import Comparator.Comparator;
import Constant.XConstant;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


public class NodeParser extends DefaultHandler {
    private final Comparator comparator;

    public NodeParser(Comparator comparator) {
        this.comparator = comparator;
    }

    private boolean active;


    private boolean checkElement(Attributes attributes) {
        return attributes.getLength() != 0 && attributes.getLocalName(0).equals(XConstant.IS_FILE);
    }
    private boolean checkIsFile(Attributes attributes) {
        return attributes.getValue(0).equals(XConstant.TRUE);
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(checkElement(attributes)) {
            comparator.setIsFile(checkIsFile(attributes));
        }
        active = qName.equals(XConstant.ACTIVE_NODE);

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(qName.equals(XConstant.ACTIVE_NODE)){
            active = false;
        }

        if(qName.equals(XConstant.INCLUDE_NODE)){
            comparator.deleteDir();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if(active) {
            String info = new String(ch, start, length);
            comparator.store(info);
        }
    }

}

