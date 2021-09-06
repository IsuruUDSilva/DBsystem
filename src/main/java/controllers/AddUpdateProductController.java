package controllers;

import javafx.event.ActionEvent;

public class AddUpdateProductController extends BaseController {

    public void loadProduct(ActionEvent event) {
        try {
            hide(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadExit(ActionEvent event) {
        try {
            hide(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
