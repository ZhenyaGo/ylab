package Comparator;

import Constant.XConstant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExComparator extends Comparator {
    private Pattern pattern;

    @Override
    public void start() {
        String newMask = processingMask(getMask());
        pattern = Pattern.compile(newMask);
    }

    public String processingMask(String mask) {
        int index1 = mask.indexOf(XConstant.APOSTROPHE1);
        int index2 = mask.indexOf(XConstant.APOSTROPHE2);
        return mask.substring(index1 + 1, index2);
    }

    @Override
    public boolean compare(String context) {
        Matcher matcher = pattern.matcher(context);
        return matcher.matches();
    }
}
