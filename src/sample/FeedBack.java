package sample;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class FeedBack {
    protected void displayErrorFeedback(TextField textField) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(15);
        dropShadow.setColor(Color.color(1,0,0));
        textField.setEffect(dropShadow);
    }

    protected void displayErrorFeedback(PasswordField passField) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(15);
        dropShadow.setColor(Color.color(1,0,0));
        passField.setEffect(dropShadow);
    }


}
