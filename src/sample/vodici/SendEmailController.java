package sample.vodici;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.FeedBack;
import sample.email.emailSender.EmailSender;


public class SendEmailController extends FeedBack {

    @FXML
    private TextField textFieldPredmetSpravy;

    @FXML
    private TextArea textAreaObsahEmailu;

    private String userEmail;

    void onCreate(String userEmail) {
        this.userEmail = userEmail;
        System.out.println(userEmail);
    }

    @FXML
    private void onPoslatEmail() {
        resetFeedback();
        if(textFieldPredmetSpravy.getText().isEmpty()) {
            System.out.println("Ci pana daj obsah emailu");
            displayErrorFeedback(textFieldPredmetSpravy);
        } else {
            EmailSender.sendEmail(userEmail, textFieldPredmetSpravy.getText(), textAreaObsahEmailu.getText());
            Stage stage = (Stage) textFieldPredmetSpravy.getScene().getWindow();
            stage.close();
            System.out.println("Email poslany");
        }
    }

    private void resetFeedback() {
        DropShadow dropShadow = new DropShadow(0, Color.color(0, 0, 0));
        textFieldPredmetSpravy.setEffect(dropShadow);
    }
}
