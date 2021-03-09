package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.util.Locale;
import java.util.Random;

public class Controller {
    @FXML AnchorPane ap_principal;
    @FXML HBox hb_cuadro;
    int perdedor=0;
    TextField panda[];

    @FXML public void initialize(){
        String pInfinito[]={"TELEFONO","NOVIOS","GATO","AHORCADO","MARIO","COMPUTADORA","SIMULACION","PANDA"};
        Random a=new Random();
        String p=pInfinito[a.nextInt(pInfinito.length-1)];
        System.out.println(p);
        panda=new TextField[p.length()];


        for(int i=0;i<p.length();i++){
            panda[i]=new TextField();
            panda[i].setPrefWidth(50);
            panda[i].setPrefHeight(50);
            panda[i].setId("txt-"+ i + "-" + p.toUpperCase().charAt(i));
            panda[i].setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {

                    TextField o=(TextField)event.getTarget();
                    String u[]=o.getId().split("-");
                    if(o.getText().equalsIgnoreCase(u[2])){
                        o.setDisable(true);
                        boolean ganador= true;
                        for(int a=0;a<panda.length;a++){
                            if(panda[a].isDisabled()==false){
                                ganador=false;
                            }
                        }
                        if(ganador==true){
                            Alert al=new Alert(Alert.AlertType.INFORMATION,"Ganaste guap@");
                            al.show();
                        }
                    }else{
                        o.setText("");
                        perdedor++;
                        panda();
                    }

                }
            });
            hb_cuadro.getChildren().add(panda[i]);

        }

    }
    public void panda(){
        ImageView cabeza=new ImageView(new Image("sample/aoracdo/cabeza.png"));
        cabeza.setFitHeight(100);
        cabeza.setFitWidth(100);
        cabeza.setLayoutX(200);
        cabeza.setLayoutY(150);
        ImageView cuerpo=new ImageView(new Image("sample/aoracdo/cuerpo.png"));
        cuerpo.setFitHeight(200);
        cuerpo.setFitWidth(100);
        cuerpo.setLayoutX(200);
        cuerpo.setLayoutY(180);
        ImageView brazoi=new ImageView(new Image("sample/aoracdo/brazo izquierdo.png"));
        brazoi.setFitHeight(100);
        brazoi.setFitWidth(100);
        brazoi.setLayoutX(250);
        brazoi.setLayoutY(180);
        brazoi.setRotate(-105);
        ImageView brazod=new ImageView(new Image("sample/aoracdo/brazo derecho.png"));
        brazod.setFitHeight(100);
        brazod.setFitWidth(100);
        brazod.setLayoutX(150);
        brazod.setLayoutY(180);
        brazod.setRotate(105);
        ImageView piei=new ImageView(new Image("sample/aoracdo/pie.png"));
        piei.setFitHeight(100);
        piei.setFitWidth(100);
        piei.setLayoutX(180);
        piei.setLayoutY(290);
        ImageView pied=new ImageView(new Image("sample/aoracdo/pie.png"));
        pied.setFitHeight(100);
        pied.setFitWidth(100);
        pied.setLayoutX(220);
        pied.setLayoutY(290);

        switch(perdedor){
            case 1:
                ap_principal.getChildren().add(pied);
                break;
            case 2:
                ap_principal.getChildren().add(piei);
                break;
            case 3:
                ap_principal.getChildren().add(brazod);
                break;
            case 4:
                ap_principal.getChildren().add(brazoi);
                break;
            case 5:
                ap_principal.getChildren().add(cuerpo);
                break;
            case 6:
                ap_principal.getChildren().add(cabeza);
                for (int e=0;e<panda.length;e++){
                    panda[e].setDisable(true);
                }
                Alert alert=new Alert(Alert.AlertType.ERROR,"Perdiste");
                alert.show();
                break;
        }



    }


}
