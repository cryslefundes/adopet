package br.com.cryslefundes.adopet.api.core.enums;

public enum AdoptionStatus {
    WAITING_EVALUATION,
    APPROVED,
    DENIED;

    public AdoptionStatus fromString(String text) {
        for(AdoptionStatus status: AdoptionStatus.values()) {
            if (status.toString().equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid adoption status: " + text);
    }
}
