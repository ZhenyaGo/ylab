package Comparator;

import Service.ArgumentProcess;

public class FactoryMethod {
    public static Comparator getComparator(ArgumentProcess arguments) {
        switch (arguments.getType()) {
            case Full -> {return new FullComparator();}
            case Equals -> {return new EqualsComparator();}
            case Mask -> {return new MaskComparator();}
            case Regular -> {return new RegExComparator();}
            default -> throw new IllegalStateException("Unexpected type: " + arguments.getType());
        }
    }

}
