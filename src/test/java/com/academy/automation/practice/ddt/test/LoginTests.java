package com.academy.automation.practice.ddt.test;

import com.academy.automation.practice.ddt.manager.model.LoginData;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import static com.academy.util.MatcherAssertExt.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

// Тестируем логин
public class LoginTests extends BaseTest {


    @Test(groups = "login", dataProvider = "userName")
    public void testSuccessLogin(String userNameExpected) throws Exception {
        manager.goTo().home();
        manager.session().login();

        String userNameActual = manager.account().getUserName();
        try {
            //verifyThat(userNameActual, equalTo(userNameExpected));
            assertThat(userNameActual, equalTo(userNameExpected));

        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        manager.session().logout();
    }

    @Ignore
    @Test(groups = {"login", "provider"}, dataProvider = "excelProvider")
    public void testIncorrectLogin(String login, String password, String expectedMessage) {
        manager.goTo().home();
        manager.session().tryLogin(login, password);
        String actualMsg = manager.session().getErrMessage();
        Assert.assertEquals(actualMsg, expectedMessage);
    }

    @DataProvider(name="userName")
    public Object[][] userNameProvider() {
        return new Object[][] {
                {"Ruslan Skira"}
        };
    }


    // TODO read from excel
    @DataProvider(name="excelProvider")
    public Object[][] excelProvider() {
        return new Object[][] {
                {"oleg.kh81@gmail.com", "123", "Invalid password."},
                {"123", "123qwerty", "Invalid email address."},
                {"", "123", "An email address required."},
        };
    }

    @DataProvider(name="xmlProvider")
    public Object[][] xmlProvider() {
        String xml = readFileAsString(manager.root() + "/data/login.xml");
        XStream xStream = new XStream();
        xStream.processAnnotations(LoginData.class);
        List<LoginData> data = (List<LoginData>)xStream.fromXML(xml);
        return data.stream().map(LoginData::toArray).toArray(Object[][]::new);
    }

    @DataProvider(name="jsonProvider")
    public Object[][] jsonProvider() {
        String json = readFileAsString(manager.root() + "/data/login.json");
        Type listType = new TypeToken<List<LoginData>>() {}.getType();
        List<LoginData> data = new Gson().fromJson(json, listType);
        return data.stream().map(LoginData::toArray).toArray(Object[][]::new);
    }

    // Читаем из файла в строку как есть
    private static String readFileAsString(String path) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(new File(path)))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
        }
        return null;
    }
}

