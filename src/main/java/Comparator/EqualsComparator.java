package Comparator;

public class EqualsComparator extends Comparator {
    @Override
    public boolean compare(String context) {
        return context.equals(getMask());
    }
}
