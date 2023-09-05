package io.github.zemise.view.main;

import de.saxsys.mvvmfx.SceneLifecycle;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/20
 */
public class MainViewModel implements ViewModel, SceneLifecycle {
    private Command getRoutersCommand;

    public void initialize(){

    }
    @Override
    public void onViewAdded() {

    }

    @Override
    public void onViewRemoved() {
        //getRoutersCommand = new DelegateCommand(() -> {get})
    }
}
