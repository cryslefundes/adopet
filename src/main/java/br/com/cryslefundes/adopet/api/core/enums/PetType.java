package br.com.cryslefundes.adopet.api.core.enums;

public enum PetType {
    DOG,
    CAT;

    public static PetType fromString(String text) {
        for (PetType type: PetType.values()) {
            if (type.toString().equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid pet type: " + text);
    }

}
