package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Peter Staranchuk on 10/7/16
 */

public class Update {
    UpdateInfo updateInfo;

    public Update() {
        updateInfo = new UpdateInfo();
    }

    public Update set(String field, Object value) {
        addUpdateInfoRule(field, "$set", value);
        return this;
    }

    public Update push(String field, Object value) {
        addUpdateInfoRule(field, "$push", value);
        return this;
    }

    public Update popFirst(String field) {
        addUpdateInfoRule(field, "$pop", ItemToRemovePosition.FIRST.getPosition());
        return this;
    }

    public Update popLast(String field) {
        addUpdateInfoRule(field, "$pop", ItemToRemovePosition.LAST.getPosition());
        return this;
    }

    public Update pull(String field, Object value) {
        addUpdateInfoRule(field, "$pull", value);
        return this;
    }

    public Update pullAll(String field, List<Object> value) {
        addUpdateInfoRule(field, "$pullAll", value);
        return this;
    }

    public Update addToSet(String field, Object value) {
        addUpdateInfoRule(field, "$addToSet", value);
        return this;
    }

    public Update inc(String field, Integer increaseValue) {
        addUpdateInfoRule(field, "$inc", increaseValue);
        return this;
    }

    public Update currentDate(String field) {
        addUpdateInfoRule(field, "$currentDate", "timestamp");
        return this;
    }

    //multiply value by number
    public Update mul(String field, Integer value) {
        addUpdateInfoRule(field, "$mul", value);
        return this;
    }

    public Update min(String field, Integer valueToCompare) {
        addUpdateInfoRule(field, "$min", valueToCompare);
        return this;
    }

    public Update max(String field, Integer valueToCompare) {
        addUpdateInfoRule(field, "$max", valueToCompare);
        return this;
    }

    public HashMap<String, Object> getRecord(String key, Object value) {
        HashMap<String, Object> record = new HashMap<>();
        record.put(key, value);
        return record;
    }

    public UpdateInfo getUpdateInfo() {
        return updateInfo;
    }

    public enum ItemToRemovePosition {
        FIRST(-1), LAST(1);

        int position;

        ItemToRemovePosition(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }

    }

    private void addUpdateInfoRule(String field, String operation, Object value) {
        if(updateInfo.containsKey(operation)) {
            updateInfo.get(operation).put(field, value);
        } else {
            updateInfo.put(operation, getRecord(field, value));
        }
    }
}
