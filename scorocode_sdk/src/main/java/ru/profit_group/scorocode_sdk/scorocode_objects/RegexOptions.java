package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.io.Serializable;

/**
 * Created by Peter Staranchuk on 10/13/16
 */
public class RegexOptions implements Serializable {
    private String options;

    public RegexOptions() {
        options = "";
    }

    public void setRegexCaseInsenssitive(){
        addOption("i");
    }

    public void setMatchAnchorsOnEveryStringLine(){
        addOption("m");
    }

    public void setIgnoreWhitespacesInPattern() {
        addOption("x");
    }

    public void setAllowDotCharacterInPattern() {
        addOption("s");
    }

    private void addOption(String option) {
        if(!options.contains(option)) {
            options += option;
        }
    }

    public String getRegexOptions() {
        return options;
    }
}
