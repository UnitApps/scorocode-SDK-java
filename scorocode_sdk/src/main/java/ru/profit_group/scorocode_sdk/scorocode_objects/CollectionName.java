package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 10/7/16
 */

public enum CollectionName {
    USERS("users"), ROLES("roles");

    private String collectionName;

    CollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionName() {
        return collectionName;
    }
}
