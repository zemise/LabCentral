package io.github.labcentral.custom;

import io.github.labcentral.util.FontSizeConverter;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ComboBox;
import io.github.labcentral.util.FxUtil;

import java.util.*;

@Deprecated
public class FontSizeComBox extends ComboBox {
    private final SimpleObjectProperty<Double> selectedFontSize = new SimpleObjectProperty<>(10.5);

    public SimpleObjectProperty<Double> fontSizeProperty() {
        return selectedFontSize;
    }

    public FontSizeComBox() {
        setEditable(true);

        // 添加字号选项
        // 汉字字号由到到小
        List<String> numFontSizes = new ArrayList<>();
        Set<String> strings = FontSizeConverter.fontSizeMapping.keySet();
        strings.forEach(fontsize->{
            getItems().add(fontsize);
            numFontSizes.add(FontSizeConverter.getFontSize(fontsize));
        });

        // 数字字号由小到大
        Collections.reverse(numFontSizes);
        numFontSizes.forEach(numFontsize -> {
            getItems().add(numFontsize);
        });

        // 设置默认选中项
        setValue("五号");


        setOnAction(event -> {
            // 获得用户输入的字体号，需考虑无效输入，如不存在的中文字号，若是数字必须介于 1 和 1638 之间
            String inputSize = getValue().toString();
            double fontSize;

            String fontSelect = FontSizeConverter.getFontSize(inputSize);
            if (fontSelect != null){
                fontSize = Double.parseDouble(fontSelect);
            }else {
                try {
                    // 尝试将输入字体号转为double类型
                    fontSize = Double.parseDouble(inputSize);
                    // 数字则需介于1 和 1638 之间
                    if( fontSize < 1 || fontSize >1638){
                        FxUtil.warningDialogFx("数字必须介于 1 和 1638 之间");
                        // 确定为超出，恢复到原来的字体号
                        // todo setValue 此处存在的问题是，如果原本是中文字体号，经过一折腾就变了阿拉伯数字了
                        setValue(selectedFontSize.getValue());
                        return;
                    }
                }catch (NumberFormatException e){
                    // 确定为无效输入，恢复到原来的字体号
                    setValue(selectedFontSize.getValue());
                    return;
                }
            }
            selectedFontSize.set(fontSize);
        });


    }
}
