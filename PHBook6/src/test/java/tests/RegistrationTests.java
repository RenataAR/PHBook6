package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{
    public void preCondition(){
        if(app.getUser().isLogged()){
            app.getUser().logOut();
        }
    }

    @Test
    public void registrationPositiveTest(){
        int i = (int)((System.currentTimeMillis()/1000)%3600);
        User user = User.builder()
                .email("galina" + i + "@gmail.com")
                .password("Gg123456$")
                .build();
        logger.info("registrationPositiveTest starts with: " + user.getEmail() + " & " + user.getPassword());

        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isLogged());
    }

    @Test
    public void registrationNegativeTestWrongEmail(){
        int i = (int)((System.currentTimeMillis()/1000)%3600);
        User user = User.builder()
                .email("galina" + i + "gmail.com")
                .password("Gg123456$")
                .build();

        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isErrorFormatMessage());
        Assert.assertTrue(app.getUser().isAlertPresent());
    }
}
