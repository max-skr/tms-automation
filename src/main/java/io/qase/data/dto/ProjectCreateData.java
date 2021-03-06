package io.qase.data.dto;

public class ProjectCreateData {

    private String name;

    private String code;

    private String description;

    private AccessOption membersAccessOption;

    private boolean isPrivate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccessOption getMembersAccessOption() {
        return membersAccessOption;
    }

    public void setMembersAccessOption(AccessOption membersAccessOption) {
        this.membersAccessOption = membersAccessOption;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean value) {
        isPrivate = value;
    }

    public enum AccessOption {
        ALL_MEMBERS,
        SELECT_MEMBERS,
        NO_MEMBERS
    }

}
