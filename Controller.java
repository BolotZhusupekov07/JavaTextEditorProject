package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;

public class Controller {
    @FXML
    public TextArea textArea;
    @FXML
    public VBox root;

    @FXML
    private void handleOpen(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) root.getScene().getWindow();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Java Files", "*.java"));
        File file = fileChooser.showOpenDialog(stage);
        if(file!=null){
            BufferedReader bufferedReader = null;

            String document = "";
            String line = "";

            try {

                bufferedReader = new BufferedReader(new FileReader(file));
                while ((line = bufferedReader.readLine()) != null){
                    document += line + "\n";
                }
                textArea.setText(document);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                handleExceptionAlert();

            } catch (IOException e) {
                e.printStackTrace();
                handleExceptionAlert();
            } catch (Exception e){
                e.printStackTrace();
                handleExceptionAlert();
            } finally {
                if(bufferedReader!=null){
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        handleExceptionAlert();
                    }
                }
            }
        }
    }
    @FXML
    private void handleSave(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) root.getScene().getWindow();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);


        if (file!=null){
            FileWriter writer = null;
            try {
                writer = new FileWriter(file);
                writer.write(textArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
                handleExceptionAlert();
            } catch (Exception e){
                e.printStackTrace();
                handleExceptionAlert();
            } finally {
                if(writer!=null){
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        handleExceptionAlert();
                    }
                }
            }
        }
    }

    @FXML
    public void handleExceptionAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Something went wrong");
        alert.show();
    }
    @FXML
    private void handleClear(ActionEvent event){
        textArea.clear();
    }
    @FXML
    private void handlePaste(ActionEvent event){
        textArea.paste();
    }
    @FXML
    private void handleCopy(ActionEvent event){
        textArea.copy();
    }
    @FXML
    private void handleCut(ActionEvent event){
        textArea.cut();
    }
    @FXML
    private void handleUndo(ActionEvent event){
        textArea.undo();
    }
    @FXML
    private void handleRedo(ActionEvent event){
        textArea.redo();
    }




}
