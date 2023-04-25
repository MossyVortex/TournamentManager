import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class HomeController {

    @FXML
    private Button TButto;

    @FXML
    private Button CButton;

    @FXML
    private ImageView backButton;

    @FXML
    void TButtoAction(ActionEvent event) {
        System.out.println("Thank u ");
    }

}
