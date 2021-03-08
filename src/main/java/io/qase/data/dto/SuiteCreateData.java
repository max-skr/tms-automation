package io.qase.data.dto;

public class SuiteCreateData {

    private String name;

    private String parentSuite;

    private String description;

    private String preconditions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentSuite() {
        return parentSuite;
    }

    public void setParentSuite(String parentSuite) {
        this.parentSuite = parentSuite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreconditions() {
        return preconditions;
    }

    public void setPreconditions(String preconditions) {
        this.preconditions = preconditions;
    }
}
