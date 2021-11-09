package Comparator;

public class EqualsComparator extends AbstractComparator {
    @Override
    public boolean compare(String context) {
        return context.equals(getMask());
    }
}
