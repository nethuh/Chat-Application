package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Objects;

public class ClientFormController extends Thread {
    public Label userName;
    public VBox vbox_messages;
    public TextField txtTyping;
    public Button btn_send;
    public AnchorPane EmojiPane;


    BufferedReader reader;
    PrintWriter writer;
    Socket socket;

    private FileChooser fileChooser;
    private File filePath;

    public void initialize() throws IOException {
        String userName=LoginFormController.name;
        this.userName.setText(userName);
        try {
            socket = new Socket("localhost", 6000);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {


                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];


                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMsg.append(tokens[i]+" ");
                }


                String[] msgToAr = msg.split(" ");
                String st = "";
                for (int i = 0; i < msgToAr.length - 1; i++) {
                    st += msgToAr[i + 1] + " ";
                }


                Text text = new Text(st);
                String firstChars = "";
                if (st.length() > 3) {
                    firstChars = st.substring(0, 3);

                }


                if (firstChars.equalsIgnoreCase("img")) {
                    //for the Images

                    st = st.substring(3, st.length() - 1);


                    File file = new File(st);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(150);
                    imageView.setFitWidth(200);


                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);


                    if (!cmd.equalsIgnoreCase(userName.getText())) {

                        vbox_messages.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);


                        Text text1 = new Text("  " + cmd + " :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);

                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text(": Me ");
                        hBox.getChildren().add(text1);


                    }

                    Platform.runLater(() -> vbox_messages.getChildren().addAll(hBox));


                } else {

                    TextFlow tempFlow = new TextFlow();


                    if (!cmd.equalsIgnoreCase(userName.getText() + ":")) {
                        Text txtName = new Text(cmd + " ");
                        txtName.getStyleClass().add("txtName");
                        tempFlow.getChildren().add(txtName);

                        tempFlow.setStyle("-fx-color: rgb(239,242,255);" +
                                "-fx-background-color: rgb(15,125,242);" +
                                " -fx-background-radius: 10px");
                        tempFlow.setPadding(new Insets(3,10,3,10));
                    }

                    tempFlow.getChildren().add(text);
                    tempFlow.setMaxWidth(200);

                    TextFlow flow = new TextFlow(tempFlow);

                    HBox hBox = new HBox(12);




                    if (!cmd.equalsIgnoreCase(userName.getText() + ":")) {


                        vbox_messages.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(flow);
                        hBox.setPadding(new Insets(2,5,2,10));

                    } else {

                        Text text2 = new Text(fullMsg + ": Me");
                        TextFlow flow2 = new TextFlow(text2);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(flow2);
                        hBox.setPadding(new Insets(2,5,2,10));

                       flow2.setStyle("-fx-color: rgb(239,242,255);" +
                                "-fx-background-color: rgb(191,241,9);" +
                                "-fx-background-radius: 10px");
                        flow2.setPadding(new Insets(3,10,3,10));

                    }

                    Platform.runLater(() -> vbox_messages.getChildren().addAll(hBox));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendmsgOnAction(ActionEvent actionEvent) {
        String msg = txtTyping.getText();
        writer.println(userName.getText() + ": " + msg);
        txtTyping.clear();

        if(msg.equalsIgnoreCase("!Bye") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);

        }
    }

    public void ImageMouseClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        writer.println(userName.getText() + " " + "img" + filePath.getPath());
    }

    public void entersend(ActionEvent actionEvent) {
        btn_send.fire();
    }

    public void EmojiOnAction(MouseEvent mouseEvent) throws IOException {
            EmojiPane.setVisible(true);
//        byte[] emojiByteCode = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x81};
//        String emoji = new String(emojiByteCode,Charset.forName("UTF-8"));
//        txtTyping.appendText("\uD83D\uDE00");
////      txtTyping.appendText("\uD83A");
//        txtTyping.appendText("\uD83D\uDE34");
//        txtTyping.setText(txtTyping.getText()+" "+emoji);

    }

    public void Heart(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128525));
        txtTyping.setText(emoji);
        EmojiPane.setVisible(false);
    }

    public void sadMood(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128546));
        txtTyping.setText(emoji);
        EmojiPane.setVisible(false);
    }

    public void normalMood(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars( 128522));
        txtTyping.setText(emoji);
        EmojiPane.setVisible(false);
    }

    public void Hehe(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128513));
        txtTyping.setText(emoji);
        EmojiPane.setVisible(false);
    }

    public void ToungOut(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128539));
        txtTyping.setText(emoji);
        EmojiPane.setVisible(false);
    }

    public void sick(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128560));
        txtTyping.setText(emoji);
        EmojiPane.setVisible(false);
    }

    public void Hiks(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128540));
        txtTyping.setText(emoji);
        EmojiPane.setVisible(false);
    }

    public void soSad(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128554));
        txtTyping.setText(emoji);
        EmojiPane.setVisible(false);
    }

    public void haha(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128514));
        txtTyping.setText(emoji);
        EmojiPane.setVisible(false);
    }

    public void Emotional(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128578));
        txtTyping.setText(emoji);
        EmojiPane.setVisible(false);
    }

    public void bad(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128543));
        txtTyping.setText(emoji);
        EmojiPane.setVisible(false);
    }

    public void money(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(129297));
        txtTyping.setText(emoji);
        EmojiPane.setVisible(false);
    }

    public void satisfied(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128519));
        txtTyping.setText(emoji);
        EmojiPane.setVisible(false);
    }

    public void ohh(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128550));
        txtTyping.setText(emoji);
        EmojiPane.setVisible(false);
    }

    public void wow(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128559));
        txtTyping.setText(emoji);
        EmojiPane.setVisible(false);
    }
}


