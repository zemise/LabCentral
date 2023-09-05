package io.github.zemise.event.listener;

import io.github.zemise.event.EventHandler;
import io.github.zemise.event.Listener;
import io.github.zemise.event.entity.UserLoginEvent;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/24
 */
public class onUserLogin implements Listener {
    @EventHandler
    public static void afterUserLogin(UserLoginEvent event){
        // do something
        System.out.println("收到用户已经成功登陆，现在开始处理下一步");
    }
}
