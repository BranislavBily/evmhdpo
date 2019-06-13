package sample.resethesla;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.FeedBack;
import sample.email.emailFinder.EmailFinder;
import sample.email.emailSender.EmailSender;
import sample.email.verification.EmailVerificator;
import sample.password.PasswordChangerJava;

public class ResetHeslaController extends FeedBack {

    @FXML
    private TextField textFieldEmail;
    @FXML
    private PasswordField passField;
    @FXML
    private PasswordField passField1;
    @FXML
    private AnchorPane resetHeslaPane;
    @FXML
    private Button zmenitHeslo;
    @FXML
    private Button zaslatEmail;
    @FXML
    private Button buttonZaslatCode;
    @FXML
    private TextField textFieldCode;

    private int code;
    private int pocetPokusov = 0;

    @FXML
    private void onOveritEmail() {
        resetFeedback();
        System.out.println("Overujem kod");
        String email = textFieldEmail.getText();
        if (!EmailVerificator.isEmailCorrect(email)) {
            displayErrorFeedback(textFieldEmail);
            System.out.println("Zly email");

        } else if(!EmailFinder.emailInDatabase(email)) {
            textFieldEmail.setPromptText("Neexistuje pouzivatel s takymto emailom");
            displayErrorFeedback(textFieldEmail);
            System.out.println("Neexistuje pouzivatel s takymto emailom");
        } else {
            code = generateCode();
            EmailSender.sendVerificationEmail(email,"Verification Code", code);
            resizeScene(400);
            resetHeslaPane.autosize();
            textFieldCode.setVisible(true);
            buttonZaslatCode.setVisible(true);
            zaslatEmail.setDisable(true);
            textFieldEmail.setDisable(true);
        }
    }

    @FXML
    private void onOveritKod() {
        if(pocetPokusov < 3) {
            resetFeedback();
            try {
                int userKod = Integer.parseInt(textFieldCode.getText());
                if(userKod == code) {
                    System.out.println("Yey");
                    buttonZaslatCode.setDisable(true);
                    textFieldCode.setDisable(true);
                    passField.setVisible(true);
                    passField1.setVisible(true);
                    zmenitHeslo.setVisible(true);
                    resizeScene(620);
                    resetHeslaPane.autosize();
                } else {
                    displayErrorFeedback(textFieldCode);
                    System.out.println("Zly kod");
                    pocetPokusov++;
                }
            } catch (NumberFormatException e) {
                pocetPokusov++;
                displayErrorFeedback(textFieldCode);
                System.out.println("Sa spamataj");
            }
        } else {
            Stage stage = (Stage) textFieldCode.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void onZmenitHeslo() {
        resetFeedback();
        String password = passField.getText();
        String passwordAgain = passField1.getText();
        if(password.equals(passwordAgain) && password.length() > 8) {
            PasswordChangerJava.changePassword(textFieldEmail.getText(), password);
            Stage stage = (Stage) textFieldCode.getScene().getWindow();
            stage.close();
            EmailSender.sendPasswordChangedEmail(textFieldEmail.getText());
        } else {
            displayErrorFeedback(passField);
            displayErrorFeedback(passField1);
            System.out.println("Zle heslo");
        }
    }

    private int generateCode() {
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            number.append((int) (Math.random() * 10));
        }
        return Integer.parseInt(number.toString());
    }

    private void resizeScene(int height) {
        resetHeslaPane.resize(362, height);
        Stage stage = (Stage) passField.getScene().getWindow();
        stage.setMinHeight(height);
        stage.setMinWidth(362);
    }

    public void resetFeedback(){
        DropShadow dropShadow = new DropShadow(0, Color.color(0, 0, 0));
        textFieldCode.setEffect(dropShadow);
        textFieldEmail.setEffect(dropShadow);
        passField.setEffect(dropShadow);
        passField1.setEffect(dropShadow);
    }


}
