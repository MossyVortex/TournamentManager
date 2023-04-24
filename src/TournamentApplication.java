//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.Objects;
//
//public class TournamentApplication extends Application {
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//            Parent fxmlLoader = null;
//            try {
//                fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginScene.fxml")));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Scene loginPage = new Scene(fxmlLoader);
//            primaryStage.setScene(loginPage);
//            primaryStage.setTitle("Tournament Manager - Login");
//            primaryStage.show();
//    }
//
//
//}
