package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    public void preCondition(){
        if(app.getUser().isLogged()){
            app.getUser().logOut();
        }
    }

    @Test
    public void loginPositiveTest(){
        User user = User.builder()
                .email("galina@gmail.com")
                .password("Gg123456$")
                .build();

        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLogged());
    }

    @Test
    public void loginNegativeTestWrongEmail(){
        User user = User.builder()
                .email("galinagmail.com")
                .password("Gg123456$")
                .build();

        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isErrorFormatMessage());
        Assert.assertTrue(app.getUser().isAlertPresent());
    }
}
