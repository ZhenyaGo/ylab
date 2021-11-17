package Comparator;

import Constant.XConstant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskComparator extends Comparator {
    private Pattern pattern;

    @Override
    public void start() {
       String newMask = processingMask(getMask());
       pattern = Pattern.compile(newMask);
    }

    public String processingMask(String mask) {
        StringBuilder newMask = new StringBuilder();
        int index1 = mask.indexOf(XConstant.APOSTROPHE1);
        int index2 = mask.indexOf(XConstant.APOSTROPHE2);
        mask = mask.substring(index1 + 1, index2);
        char[] chars = mask.toCharArray();
        for (char ch : chars) {
            switch (ch) {
                case '.' -> newMask.append("\\.");
                case '?' -> newMask.append(".");
                case '*' -> newMask.append(".*");
                default  -> newMask.append(ch);
            }
        }
        return newMask.toString();
    }

    @Override
    public boolean compare(String context) {
        Matcher matcher = pattern.matcher(context);
        return matcher.matches();
    }
}
