package ru.profit_group.scorocode_sdk.Responses.user;

import java.util.List;

/**
 * Created by Peter Staranchuk on 10/5/16.
 */

public class UserData {
    private String _id;
    private String createdAt;
    private String email;
    private boolean emailVerified;
    private String password;
    private List<String> readACL;
    private List<String> removeACL;
    private List<String> updateACL;
    private String updatedAt;
    private String username;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getReadACL() {
        return readACL;
    }

    public void setReadACL(List<String> readACL) {
        this.readACL = readACL;
    }

    public List<String> getRemoveACL() {
        return removeACL;
    }

    public void setRemoveACL(List<String> removeACL) {
        this.removeACL = removeACL;
    }

    public List<String> getUpdateACL() {
        return updateACL;
    }

    public void setUpdateACL(List<String> updateACL) {
        this.updateACL = updateACL;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
