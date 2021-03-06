package io.qase.data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import io.qase.data.dto.UserData;
import io.qase.data.dto.json.Accounts;
import org.apache.commons.collections4.CollectionUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

public enum AccountProvider {

    INSTANCE;

    public static AccountProvider getInstance() {
        return INSTANCE;
    }

    public UserData getAdmin() {
        try {
            Gson gson = new Gson();
            String filePath = Paths.get("src", "main", "resources", "accounts", "accounts.json")
                    .toAbsolutePath().toString();
            JsonReader reader = new JsonReader(new FileReader(filePath));
            Accounts accounts = gson.fromJson(reader, Accounts.class);
            if (CollectionUtils.isEmpty(accounts.getAdmins())) {
                throw new NoSuchElementException("No admins found in json: " + filePath);
            }
            return accounts.getAdmins().get(0); // todo random?
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to get admins", e);
        }
    }

}
