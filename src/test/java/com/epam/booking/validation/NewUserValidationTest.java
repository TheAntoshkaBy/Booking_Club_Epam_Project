package com.epam.booking.validation;

import by.epam.booking.exception.ServiceException;
import by.epam.booking.service.validation.LoginValidation;
import by.epam.booking.service.validation.NewUserValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.validation.constraints.AssertTrue;

public class NewUserValidationTest {

    private String correctName;
    private String failName;
    private String correctSurname;
    private String failSurname;
    private String correctLogin;
    private String failLogin;
    private String correctPassword;
    private String failPassword;
    private String correctEmail;
    private String failEmail;


    @BeforeTest
    public void init()
    {
        correctName = "Anton";
        failName = "ew324";

        correctSurname = "Vedenichev";
        failSurname = "vedenichev";

        correctLogin = "Larra1992";
        failLogin = "";

        correctPassword = "aperstn324";
        failPassword = "a";

        correctEmail = "y.menya.ee.nety@gmail.com";
        failEmail = "NyAChe?";

    }


    @Test
    public void testName() {
        Assert.assertTrue(NewUserValidator.getInstance().isCorrectName(correctName)
                && !NewUserValidator.getInstance().isCorrectName(failName));
    }

    @Test
    public void testSurname() {
        Assert.assertTrue(NewUserValidator.getInstance().isCorrectSurName(correctSurname) &&
               !NewUserValidator.getInstance().isCorrectSurName(failSurname));
    }
    @Test
    public void testLogin() throws ServiceException {
        Assert.assertTrue(NewUserValidator.getInstance().isCorrectLogin(correctLogin) &&
                !NewUserValidator.getInstance().isCorrectLogin(failLogin));
    }
    @Test
    public void testPassword() {
        Assert.assertTrue(NewUserValidator.getInstance().isCorrectPassword(correctPassword) &&
                !NewUserValidator.getInstance().isCorrectPassword(failPassword));
    }
    @Test
    public void testEmail() {
        Assert.assertTrue(NewUserValidator.getInstance().isCorrectEmail(correctEmail) &&
                !NewUserValidator.getInstance().isCorrectEmail(failEmail));
    }
}
