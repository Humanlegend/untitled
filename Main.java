package sample;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class Main extends Application {

    int i = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("模拟磁盘文件操作系统");
        primaryStage.setScene(new Scene(root, 300, 275));

//        primaryStage.setAlwaysOnTop(true);//设置置顶
//        primaryStage.setOpacity(0.5);//设置透明度
        primaryStage.getIcons().add(new Image("/picture/duck.png"));
//        primaryStage.setFullScreen(true);// 设置全屏
        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                System.out.println("当前高度是 = " + t1.doubleValue());
            }
        });
        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                System.out.println("当前宽度是 = " + t1.doubleValue());
            }
        });
        primaryStage.xProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                System.out.println("当前x位置是 = " + t1.doubleValue());
            }
        });
        primaryStage.yProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                System.out.println("当前y位置是 = " + t1.doubleValue());
            }
        });

//        primaryStage.initStyle(StageStyle.UTILITY);//设置窗口模式
//        Stage s1 = new Stage();
//        s1.show();
//        Stage s2 = new Stage();
//        s2.initOwner(s1);
//        s2.initModality(Modality.WINDOW_MODAL);//模态窗口
//        s2.show();

//        Platform.runLater(new Runnable() {
//            /**
//             * 在同UI线程中稍后处理某些事情，可以用来刷新
//             */
//            @Override
//            public void run() {
//                System.out.println("afa");
//            }
//        });

//        Platform.setImplicitExit(true);//设置将所有窗口关闭后是不是不退出虚拟机

//        System.out.println(Platform.isSupported(ConditionalFeature.CONTROLS));//测试是否支持xx特性

        Screen screen = Screen.getPrimary();
        Rectangle2D rec1 = screen.getBounds();
        Rectangle2D rec2 = screen.getVisualBounds();

        System.out.println("左上角x = " + rec1.getMinX() + "左上角y = " + rec1.getMinY());
        System.out.println("右下角x = " + rec1.getMaxX() + "右下角y = " + rec1.getMaxY());
        System.out.println("高度 = " + rec1.getHeight() + "宽度 = " + rec1.getWidth());//全部屏幕

        System.out.println("左上角x = " + rec2.getMinX() + "左上角y = " + rec2.getMinY());
        System.out.println("右下角x = " + rec2.getMaxX() + "右下角y = " + rec2.getMaxY());
        System.out.println("高度 = " + rec2.getHeight() + "宽度 = " + rec2.getWidth());//可以看见的屏幕

        double dpi = Screen.getPrimary().getDpi();
        System.out.println("当前屏幕dpi（每英寸像素数） = " + dpi);

        Button button0 = new Button();
        button0.setText("00");
        button0.setFont(Font.font("微软雅黑",10));

//        button0.setPrefHeight(50);
//        button0.setPrefWidth(50);
//        button.setCursor(Cursor.CLOSED_HAND);//设置鼠标进入按钮的样式

        Button button1 = new Button("11");
//        button1.setPrefHeight(50);
//        button1.setPrefWidth(50);

        Button button2 = new Button("22");
//        button2.setPrefHeight(100);
//        button2.setPrefWidth(100);

        button0.setLayoutX(0);
        button0.setLayoutY(0);
        button1.setLayoutX(50);
        button1.setLayoutY(0);
        button2.setLayoutX(100);
        button2.setLayoutY(0);

        Group group = new Group();
        group.getChildren().addAll(button0,button1,button2);

//        group.getChildren().remove(button);//删除组件
//        group.getChildren().clear();//清空组件

//        group.setOpacity(0.5);//设置整个画布透明度
//        group.setAutoSizeChildren(false);//设置子控件是否上场

//        group.getChildren().add(button);
//        group.getChildren().add(button1);
//        group.getChildren().add(button2);

        Object[] obj = group.getChildren().toArray();
//        System.out.println(obj.length);//检测子控件的数量

        for(Object o : obj)
        {
            //改变子控件的大小
            Button bu = (Button) o;
            bu.setPrefWidth(50);
            bu.setPrefHeight(50);
        }

        group.getChildren().addListener(new ListChangeListener<Node>() {
            /**
             * 监听子组件数量变化
             * @param change
             */
            @Override
            public void onChanged(Change<? extends Node> change) {
                System.out.println("当前子组件数量是 = " + change.getList().size());
            }
        });

        button0.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * 点击00按钮产生新的按钮并加入画布中
             * @param actionEvent
             */
            @Override
            public void handle(ActionEvent actionEvent) {

                i = i + 23;

                Button button3 = new Button("33");
                button3.setLayoutX(200);
                button3.setLayoutY(i);
                group.getChildren().add(button3);
            }
        });

        Scene scene = new Scene(group);
//        scene.setCursor(Cursor.CLOSED_HAND);//设置鼠标进入窗口的样式
//        URL url = getClass().getClassLoader().getResource("picture/duck.png");
//
//        String path = url.toExternalForm();
//
//        scene.setCursor(Cursor.cursor(path));//设置图片为鼠标样式

        primaryStage.setScene(scene);

//        HostServices hostServices = getHostServices();
//        hostServices.showDocument("www.baidu.com");//用默认浏览器打开指定网址




        primaryStage.show();

//        Platform.exit();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
