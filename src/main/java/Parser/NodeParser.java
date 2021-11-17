package Parser;

import Comparator.Comparator;
import Constant.XConstant;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class NodeParser extends DefaultHandler {
    private final Comparator comparator;

    public NodeParser(Comparator comparator) {
        this.comparator = comparator;
    }

    private final List<String> dir = new ArrayList<>();
    private boolean active;
    private boolean isFile, isDir;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(attributes.getLength() != 0 && attributes.getValue(XConstant.IS_FILE).equals(XConstant.TRUE)) {
            isFile = true;
        } else if (attributes.getLength() != 0 && !attributes.getValue(XConstant.IS_FILE).equals(XConstant.TRUE)) {
            isDir = true;
        }
        active = qName.equals(XConstant.ACTIVE_NODE);

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(qName.equals(XConstant.INCLUDE_NODE)){
            dir.remove(dir.size() - 1);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if(active) {
            String info = new String(ch, start, length);
            if(isDir) {
                dir.add(dir.size() > 1 ? XConstant.SPLIT_DIR + info : info);
                isDir = false;
            }

            if(isFile) {
                dir.add("");
                comparator.start();
                if(comparator.compare(info)) {
                    output(info);
                }
                isFile = false;
            }
        }
    }

    private String getFilePath() {
        return String.join("", dir);

    }

    private void output(String info) {
        System.out.println(getFilePath().length() == 1
                ? getFilePath() + info : getFilePath() + XConstant.SPLIT_DIR + info);
    }

}

