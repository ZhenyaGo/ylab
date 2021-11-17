package Comparator;

public abstract class Comparator extends Item {
    private String mask;

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public void start() {

    }

    public abstract boolean compare(String context);

}
