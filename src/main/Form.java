package main;

/*
 * Copyright Milan Škarić 2016.
 * Any use and change of this file in comercial and public use is strictly prohibited.
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Milan
 */
public class Form extends Stage implements Initializable {

    private static String changeExtension(String originalName, String newExtension) {
        if (newExtension != null && !newExtension.isEmpty() && !newExtension.startsWith(".")) {
            newExtension = "." + newExtension;
        }
        int lastDot = originalName.lastIndexOf(".");
        if (lastDot != -1) {
            return originalName.substring(0, lastDot) + newExtension;
        } else {
            return originalName + newExtension;
        }
    }

    public Form() {
        this(null, null);
    }

    public Form(String title, String fxml) {
        this(title, fxml, false);
    }

    public Form(String title, String fxml, boolean modal) {
        try {
            if(fxml==null)
                fxml=changeExtension(getClass().getSimpleName(), ".fxml");
            URL url = getClass().getResource(fxml);
            FXMLLoader fxmlLoader = new FXMLLoader(url, null, null, (param) -> {
                return Form.this; 
            });            
            Parent root = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root);
            setScene(scene);
            if(title!=null && getTitle()==null)
                setTitle(title);
            setOnCloseRequest((WindowEvent we) -> {
                onClose();
            });
            if (modal) {
                initModality(Modality.APPLICATION_MODAL);
            }
            show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    public void init() {

    }

    protected void onClose() {

    }

    @Override
    public final void close() {
        getOnCloseRequest().handle(null);
        super.close();
    }

}
