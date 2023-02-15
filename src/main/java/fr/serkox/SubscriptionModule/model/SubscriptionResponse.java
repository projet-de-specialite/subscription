package fr.serkox.SubscriptionModule.model;

public enum SubscriptionResponse {

    OK("Ok"),
    ERROR("Error");

    public final String value;
    SubscriptionResponse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
