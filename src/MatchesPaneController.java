import classes.Match;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MatchesPaneController {

    @FXML
    private Label dateLable;

    @FXML
    private Label team1Lable;

    @FXML
    private Label team2Lable;

    public void setData(Match match){

        team1Lable.setText(match.getTeamOneName());
        team2Lable.setText(match.getTeamTwoName());

        dateLable.setText(match.getDate());
    }

}
