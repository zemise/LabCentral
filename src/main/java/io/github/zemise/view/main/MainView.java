package io.github.zemise.view.main;

import atlantafx.base.controls.Popover;
import cn.hutool.json.JSONObject;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import io.github.zemise.view.config.UserInfoView;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/20
 */
public class MainView implements FxmlView<MainViewModel>, Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void showPersonalInformation(){
        new Popover(FluentViewLoader.fxmlView(UserInfoView.class).load().getView());
    }

    private void loadTab(JSONObject obj){
        var title = obj.getJSONObject("meta").getStr("title");

    }
}
