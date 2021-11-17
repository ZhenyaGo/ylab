package Parser;

import Comparator.AbstractComparator;
import Constant.XConstant;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class NodeParser extends DefaultHandler {
    private final AbstractComparator comparator;

    public NodeParser(AbstractComparator comparator) {
        this.comparator = comparator;
    }

    private final List<String> dir = new ArrayList<>();
    private String currentTagName;
    private boolean isFile, isDir;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentTagName = qName;

        if(attributes.getLength() != 0 && attributes.getValue(XConstant.IS_FILE).equals(XConstant.TRUE)) {
            isFile = true;
        } else if (attributes.getLength() != 0 && !attributes.getValue(XConstant.IS_FILE).equals(XConstant.TRUE)) {
            isDir = true;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(qName.equals(XConstant.INCLUDE_NODE)){
            dir.remove(dir.size() - 1);
        }
        currentTagName =null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if(currentTagName == null) {
            return;
        }

        if(currentTagName.equals(XConstant.ACTIVE_NODE)) {
            String info = new String(ch, start, length);

            if(isDir) {
                dir.add(info);
                isDir = false;
            }

            if(isFile) {
                dir.add("");
                comparator.start();
                if(comparator.compare(info)) {
                    getFilePath(dir, info);
                }
                isFile = false;
            }
        }
    }

    private void getFilePath(List<String> dir, String fileName) {
        for (String s: dir) {
            if(!s.startsWith(XConstant.SPLIT_DIR) && !s.equals("")) {
                System.out.print(s + XConstant.SPLIT_DIR);
            } else System.out.print(s);
        }
        System.out.println(fileName);


//        return dir.stream()
//                .filter(e -> !equals(""))
//                .collect(Collectors.joining());

    }

//    private void output(String info) {
//        System.out.println(getFilePath() + XConstant.SPLIT_DIR + info);
//    }

}

