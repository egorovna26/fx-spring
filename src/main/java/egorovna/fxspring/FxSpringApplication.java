package egorovna.fxspring;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class FxSpringApplication extends Application {
    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        try {
            SpringApplication app = new SpringApplication(FxSpringApplication.class);
            app.setHeadless(false);
            context = app.run(getParameters().getRaw().toArray(new String[0]));
        } catch (Exception e) {
            log.error(e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Hello World");
        StackPane stackPane = new StackPane(label);
        Scene scene = new Scene(stackPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        if (context != null) {
            SpringApplication.exit(context);
        }
        System.exit(0);
    }
}
