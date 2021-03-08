package io.qase.data;

import io.qase.data.dto.ProjectCreateData;
import io.qase.data.dto.SuiteCreateData;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class DataProvider {

    public static ProjectCreateData getProjectCreateData() {
        ProjectCreateData data = new ProjectCreateData();
        data.setName("PROJECT-" + UUID.randomUUID().toString());
        data.setCode(RandomStringUtils.randomAlphabetic(6).toUpperCase());
        data.setPrivate(true);
        data.setMembersAccessOption(ProjectCreateData.AccessOption.NO_MEMBERS);
        data.setDescription(RandomStringUtils.randomAlphabetic(10));
        return data;
    }

    public static SuiteCreateData getSuiteCreateData() {
        SuiteCreateData createData = new SuiteCreateData();
        createData.setName("SUITE-" + UUID.randomUUID().toString());
        createData.setDescription(RandomStringUtils.randomAlphabetic(10));
        createData.setPreconditions(RandomStringUtils.randomAlphabetic(10));
        return createData;
    }
}
