package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller {

    @FXML Button boton_cuadrado,boton_linea,boton_circular,boton_borrador,boton_pincel,boton_guardar;
    @FXML ColorPicker col_pik;
    GraphicsContext graphics;
    @FXML Canvas camvi;
    Color color= Color.WHITESMOKE;
    @FXML Slider slider_m;
    @FXML ComboBox cmb_cambio;

    @FXML public void initialize(){
        graphics=camvi.getGraphicsContext2D();
        boton_cuadrado.setGraphic(new ImageView(new Image((getClass().getResource("Imagenes/img-cuadrado.png")).toString(),25,25,true,true)));
        boton_linea.setGraphic(new ImageView(new Image((getClass().getResource("Imagenes/img-linea.png")).toString(),25,25,true,true)));
        boton_circular.setGraphic(new ImageView(new Image((getClass().getResource("Imagenes/img-circulo.png")).toString(),25,25,true,true)));
        boton_borrador.setGraphic(new ImageView(new Image((getClass().getResource("Imagenes/img-borrador.png")).toString(),25,25,true,true)));
        boton_pincel.setGraphic(new ImageView(new Image((getClass().getResource("Imagenes/img-brocha.png")).toString(),25,25,true,true)));
        boton_guardar.setGraphic(new ImageView(new Image((getClass().getResource("Imagenes/img-guardar.png")).toString(),25,25,true,true)));
        cmb_cambio.getItems().addAll("Cuadricula","Ajedrez","Estrella");
        slider_m.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                dibujo(newValue.intValue());
            }
        });
    }
    public  void dibujo(int valor){
        graphics.setFill(Color.WHITESMOKE);
        graphics.fillRect(0,0,camvi.getWidth(),camvi.getHeight());
        color= col_pik.getValue();
        graphics.setFill(color);
        if(cmb_cambio.getSelectionModel().getSelectedIndex()==0){//cuadricula
            int distancia=(int)camvi.getWidth()/valor;
            for (int i =1;i<=valor;i++){
                graphics.strokeLine(0,distancia*i,camvi.getWidth(),distancia*i );
                graphics.strokeLine(distancia*i,0,distancia*i,camvi.getHeight() );
            }

        }else if(cmb_cambio.getSelectionModel().getSelectedIndex()==1){//ajedrez
            boolean bb=true;
            boolean b=bb;
            double distancia=camvi.getWidth()/valor;
            for (int i =0;i<valor;i++){
                for (int j=0; j<valor;j++){
                    if (bb==true){
                        graphics.setFill(Color.BLACK);
                        bb=false;
                    }else {
                        graphics.setFill(Color.WHITE);
                        bb=true;
                    }

                    graphics.fillRect(distancia*j,distancia*i,(distancia*j)+distancia,(distancia*i)+distancia);
                }
                if(bb==b){
                    bb=!bb;
                    b=bb;
                }else{
                    b=bb;
                }

            }
        }else if(cmb_cambio.getSelectionModel().getSelectedIndex()==2){//estrella
            double m=(camvi.getWidth()/2)/valor;
            graphics.strokeLine(camvi.getWidth()/2,0,camvi.getWidth()/2,camvi.getHeight());
            graphics.strokeLine(0,camvi.getHeight()/2,camvi.getWidth(),camvi.getHeight()/2);

            for (int i = 0;i<valor;i++){
                graphics.strokeLine(camvi.getWidth()/2,m*i,(camvi.getWidth()/2)+(m*i),camvi.getHeight()/2);
                graphics.strokeLine(camvi.getWidth()/2, m*i, (camvi.getWidth()/2)-(m*i),camvi.getHeight()/2);
                graphics.strokeLine(camvi.getWidth()/2,camvi.getHeight()-(m*i),(camvi.getWidth()/2)+(m*i),camvi.getHeight()/2);
                graphics.strokeLine(camvi.getWidth()/2,camvi.getHeight()-(m*i),(camvi.getWidth()/2)-(m*i),camvi.getHeight()/2);
            }


        }
    }
    public void Borrar(){

        graphics.setFill(Color.WHITESMOKE);
        graphics.fillRect(0,0,camvi.getWidth(),camvi.getHeight());
    }
    public void Cambiar_color(ActionEvent event){
        color= col_pik.getValue();
    }
    public void Arrastrar(MouseEvent event){
        graphics.setFill(color);
        graphics.fillOval(event.getX(),event.getY(),10,10);
    }


}
