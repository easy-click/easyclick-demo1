package com.run;


import com.gibb.api.ApiStarter;
import com.gibb.auto.open.model.NodeSelectors;
import com.gibb.auto.open.testcase.BaseCaseMain;
import com.gibb.auto.open.testcase.ExecFunc;

public class Main extends BaseCaseMain {
    @Override
    public void exec() {
        String param1 = readConfigString("param1");

        //代理模式
        if (ApiStarter.getInstance(getContext()).getRunningMode() == 1) {
            runAgent();
        } else if (ApiStarter.getInstance(getContext()).getRunningMode() == 2) {
            //无障碍模式
            runAcc();
        }

    }

    private void runAgent() {

        event.home();
        final String pkg = "com.ss.android.article.news";
        //打开今日头条
        utils.openApp(pkg);
        //等待打开的的确是今日头条App, 等待10秒
        Boolean openApp = thread.execFuncSync(new ExecFunc<Boolean>() {
            @Override
            public Boolean execute() {
                return pkg.equalsIgnoreCase(event.getRunningPkg());
            }
        }, 10);
        if (!utils.isTrue(openApp)) {
            event.toast("打开今日头条失败");
            return;
        }
        event.toast("打开今日头条成功");
        //休息3秒
        event.sleep(3 * 1000);

        //滚动测试
        thread.execFuncSync(new ExecFunc() {
            @Override
            public Object execute() {
                event.swipeFromDownToUpInScreen(150, 10);
                event.sleep(1 * 1000);
                return null;
            }
        }, 10);

        event.sleep(1 * 1000);
        //点击西瓜视频
        event.click(NodeSelectors.get().text("西瓜视频"));
    }


    private void runAcc() {

        acEvent.home();
        final String pkg = "com.ss.android.article.news";
        //打开今日头条
        utils.openApp(pkg);
        //等待打开的的确是今日头条App, 等待10秒
        Boolean openApp = thread.execFuncSync(new ExecFunc<Boolean>() {
            @Override
            public Boolean execute() {
                return pkg.equalsIgnoreCase(acEvent.getRunningPkg());
            }
        }, 10);
        if (!utils.isTrue(openApp)) {
            acEvent.toast("打开今日头条失败");
            return;
        }
        acEvent.toast("打开今日头条成功");
        //休息3秒
        acEvent.sleep(3 * 1000);

        //滚动测试
        thread.execFuncSync(new ExecFunc() {
            @Override
            public Object execute() {
                acEvent.swipeFromDownToUpInScreen(150, 30);
                acEvent.sleep(1 * 1000);
                return null;
            }
        }, 10);
        acEvent.sleep(1 * 1000);

        //点击西瓜视频
        acEvent.click(NodeSelectors.get().text("西瓜视频"));

    }

}
