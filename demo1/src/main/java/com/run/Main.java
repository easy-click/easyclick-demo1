package com.run;

import com.gibb.auto.model.Selector;
import com.gibb.auto.open.BaseCaseMain;
import com.gibb.auto.open.ExecFunc;

public class Main extends BaseCaseMain {
    @Override
    public void exec() {
        String param1 = readConfigString("param1");
        event.toast("读取参数param1: " + param1);
        event.home();
        final String pkg = "com.ss.android.article.news";
        //打开今日头条
        shell.openApp(pkg);
        //等待打开的的确是今日头条App
        Boolean openApp = thread.execFuncSync(new ExecFunc<Boolean>() {
            @Override
            public Boolean execute() {
                if (pkg.equalsIgnoreCase(event.getRunningPkg())) {
                    return true;
                }
                return false;
            }
        }, 10);
        if (!utils.isTrue(openApp)) {
            event.toast("打开今日头条失败");
            return;
        }
        event.toast("打开今日头条成功");
        //休息3秒
        event.sleep(3 * 1000);
        //点击西瓜视频

        Selector selector = Selector.builder().text("西瓜视频").clickable(true).build();

        event.click(selector);

    }

}
