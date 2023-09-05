package io.github.zemise.view.config;

import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import io.github.zemise.domain.SysDept;
import io.github.zemise.domain.SysUser;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/20
 */
public class UserInfoViewModel implements ViewModel {

    private Command command;
    private StringProperty roles = new SimpleStringProperty();
    private StringProperty dept = new SimpleStringProperty();
    private ModelWrapper<SysUser> wrapper = new ModelWrapper<SysUser>();



    public UserInfoViewModel() {
        command = new DelegateCommand(() -> new Action() {
            @Override
            protected void action() throws Exception {
                getUserInfo();
            }
        }, true);
        command.execute();
    }

    public void setPerson(SysUser sysUser){
        wrapper.set(sysUser);
        wrapper.reload();
    }

    public StringProperty userNameProperty(){
        return wrapper.field("userName",SysUser::getUserName, SysUser::setUserName,"");
    }

    public StringProperty phoneNumberProperty() {
        return wrapper.field("phoneNumber", SysUser::getPhoneNumber, SysUser::setPhoneNumber, "");
    }

    public StringProperty emailProperty() {
        return wrapper.field("email", SysUser::getEmail, SysUser::setEmail, "");
    }

    public ObjectProperty createTimeProperty() {
        return wrapper.field("createTime", SysUser::getCreateTime, SysUser::setCreateTime, null);
    }

    public ObjectProperty<SysDept> deptProperty() {
        return wrapper.field("dept", SysUser::getDept, SysUser::setDept, null);
    }

    public String getRoles() {
        return roles.get();
    }
    public void setRoles(String roles) {
        this.roles.set(roles);
    }
    public StringProperty rolesProperty() {
        return roles;
    }

    private void getUserInfo() {
        // todo

    }
}
