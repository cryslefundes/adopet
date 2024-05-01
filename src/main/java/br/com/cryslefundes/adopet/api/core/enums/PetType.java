package br.com.cryslefundes.adopet.api.core.enums;

public enum PetType {
    DOG("dog"),
    CAT("cat");

    private final String animal;

    PetType(String animal) {
        this.animal = animal;
    }

    public static PetType fromString(String text) {
        for (PetType type: PetType.values()) {
            if (type.animal.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid pet type: " + text);
    }

}
