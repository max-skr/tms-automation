package io.qase.data.dto.json;

import io.qase.data.dto.UserData;

import java.util.List;

public class Accounts {

    private List<UserData> admins;

    public List<UserData> getAdmins() {
        return admins;
    }

    public void setAdmins(List<UserData> admins) {
        this.admins = admins;
    }
}
