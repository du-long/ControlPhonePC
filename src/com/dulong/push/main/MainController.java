package com.dulong.push.main;

import com.dulong.push.utils.RetrofitUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController {
    MainService mainService = RetrofitUtils.retrofit.create(MainService.class);
    Alert alert = new Alert(Alert.AlertType.WARNING);
    @FXML
    private Button send;
    @FXML
    private TextField ip;
    @FXML
    private TextField id;
    @FXML
    private TextField resultText;

    @FXML
    protected void sendClick() throws Exception {


        String ipAddress = ip.getText();
        String registrationID = id.getText();

        if (ipAddress == null || ipAddress.isEmpty()) {
            alert.setTitle("提示：");
            alert.setHeaderText("请输入IP地址");
            alert.show();
            return;
        }


        if (registrationID == null || registrationID.isEmpty()) {
            alert.setTitle("提示：");
            alert.setHeaderText("请输入JpushID");
            alert.show();
            return;
        }


        String url = " http://" + ipAddress + ":8081/control/send";
        Observable<String> video = mainService.video(url, ipAddress, registrationID);
        video.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
                resultText.setText(s);
            }

            @Override
            public void onError(Throwable throwable) {
                alert.setTitle("请求失败");
                alert.setHeaderText(throwable.getMessage());
                alert.show();
            }

            @Override
            public void onComplete() {
            }
        });


    }

    @FXML
    void onlineClick() {
        String ipAddress = ip.getText();
        String registrationID = id.getText();

        if (ipAddress == null || ipAddress.isEmpty()) {
            alert.setTitle("提示：");
            alert.setHeaderText("请输入IP地址");
            alert.show();
            return;
        }
        String url = "http://" + ipAddress + ":8081/control/online";
        Observable<String> video = mainService.online(url, ipAddress, "1");
        video.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(String s) {
                resultText.setText(s);
                System.out.println(s);
            }

            @Override
            public void onError(Throwable throwable) {
                alert.setTitle("请求失败");
                alert.setHeaderText(throwable.getMessage());
                alert.show();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
