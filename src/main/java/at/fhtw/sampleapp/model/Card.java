package at.fhtw.sampleapp.model;

public abstract class Card {
    protected String name;
    protected double damage;
    protected String elementType;

    public Card(String name, double damage, String elementType) {
        this.name = name;
        this.damage = damage;
        this.elementType = elementType;
    }

    public String getName() {
        return name;
    }

    public double getDamage() {
        return damage;
    }

    public String getElementType() {
        return elementType;
    }


    @Override
    public String toString() {
        return String.format("%s [Damage: %.2f, Element: %s]", name, damage, elementType);
    }
}