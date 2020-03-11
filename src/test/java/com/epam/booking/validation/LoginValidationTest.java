package com.epam.booking.validation;
import by.epam.booking.service.validation.LoginValidation;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class LoginValidationTest {

    private String testLogAttack;
    private String testLogNormal;

    @BeforeTest
    public void init()
    {
       testLogAttack = "<script> function setColor() {document.getElementById(\"demo\").style.backgroundColor}</script>";
       testLogNormal = "TheAntonioBestEpamEmployeeForever!";
    }

    @Test
    public void test() {
        Assert.assertTrue(LoginValidation.getInstance().isXssAttack(testLogAttack)
                && !LoginValidation.getInstance().isXssAttack(testLogNormal));
    }

}
