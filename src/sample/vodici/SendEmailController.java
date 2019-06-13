package sample.vodici;

import javafx.fxml.FXML;

import java.awt.*;

public class SendEmailController {

    @FXML
    private TextField textFieldPredmetSpravy;

    @FXML
    private TextArea textAreaObsahEmailu;

    void onCreate(String userID) {
        System.out.println(userID);
    }

    @FXML
    private void onPoslatEmail() {
        if(textAreaObsahEmailu.getText().isEmpty()) {
            System.out.println("Ci pana daj obsah emailu");
        } else {
            
        }
    }
}
