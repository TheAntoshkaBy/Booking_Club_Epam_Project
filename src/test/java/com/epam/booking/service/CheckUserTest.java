package com.epam.booking.service;

import by.epam.booking.exception.ServiceException;
import by.epam.booking.service.user.CheckUser;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class CheckUserTest {

    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String failName;
    private String failSurname;
    private String failLogin;
    private String failPassword;
    private String failEmail;

    @BeforeTest
    public void init()
    {
       name = "Anton";
       surname = "Vedenichev";
       login = "Larra1992";
       password = "aperstn324";
       email = "y.menya.ee.nety@gmail.com";
       failName = "";
       failSurname = surname;
       failLogin = "";
       failPassword = password;
       failEmail = "";

    }

    @Test
    public void TestCheckUserDataIsCorrect() throws ServiceException {
        Map<String, Boolean> userData = CheckUser.checkUserDataIsCorrect(name,surname,login,password,email);
        Map<String, Boolean> badUserData = CheckUser.
                checkUserDataIsCorrect(failName,failSurname,failLogin,failPassword,failEmail);

        boolean result = userData.values().stream().filter(i -> i.equals(true)).findAny().orElse(false);
        boolean badResult = badUserData.values().stream().filter(i -> i.equals(true)).findAny().orElse(false);

        Assert.assertTrue(!result && badResult);
    }
}
