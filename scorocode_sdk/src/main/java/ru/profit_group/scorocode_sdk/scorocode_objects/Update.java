package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Peter Staranchuk on 10/7/16
 */

public class Update {
    HashMap<String, HashMap<String, Object>> updateInfo;

    public Update() {
        updateInfo = new HashMap<>();
    }

    public Update set(String field, String value) {
        updateInfo.put("$set", getRecord(field, value));
        return this;
    }

    public Update push(String field, String value) {
        updateInfo.put("$push", getRecord(field, value));
        return this;
    }

    public Update pop(String field, ItemToRemovePosition itemToRemovePosition) {
        updateInfo.put("$pop", getRecord(field, itemToRemovePosition.getPosition()));
        return this;
    }

    public Update pullAll(String field, List<String> values) {
        updateInfo.put("$pullAll", getRecord(field, values));
        return this;
    }

    public Update addToSet(String field, String value) {
        updateInfo.put("addToSet", getRecord(field, value));
        return this;
    }

    public Update inc(String field, Integer increaseValue) {
        updateInfo.put("$inc", getRecord(field, increaseValue));
        return this;
    }

    public Update currentDate(String field, String date) {
        updateInfo.put("$inc", getRecord(field, date));
        return this;
    }

    public Update currentDate(String field, Date date) {
        updateInfo.put("$inc", getRecord(field, date));
        return this;
    } //TODO check

    //multiply value by number
    public Update mul(String field, Integer value) {
        updateInfo.put("$mul", getRecord(field, value));
        return this;
    }

    public Update min(String field, Integer valueToCompare) {
        updateInfo.put("$min", getRecord(field, valueToCompare));
        return this;
    }

    public Update max(String field, Integer valueToCompare) {
        updateInfo.put("$max", getRecord(field, valueToCompare));
        return this;
    }

    public HashMap<String, Object> getRecord(String key, Object value) {
        HashMap<String, Object> record = new HashMap<>();
        record.put(key, value);
        return record;
    }

    public HashMap<String, HashMap<String, Object>> getUpdateInfo() {
        return updateInfo;
    }

    public enum ItemToRemovePosition {
        FIRST("1"), LAST("-1");

        String position;

        ItemToRemovePosition(String position) {
            this.position = position;
        }

        public String getPosition() {
            return position;
        }
    }
}
