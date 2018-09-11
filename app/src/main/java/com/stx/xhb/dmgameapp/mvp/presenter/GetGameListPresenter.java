package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.data.callback.LoadTaskCallback;
import com.stx.xhb.dmgameapp.data.entity.GameListBean;
import com.stx.xhb.dmgameapp.data.entity.HotGameBean;
import com.stx.xhb.dmgameapp.data.entity.NewsContent;
import com.stx.xhb.dmgameapp.data.remote.TasksRepositoryProxy;
import com.stx.xhb.dmgameapp.mvp.contract.GetGameListContract;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Author：xiaohaibin
 * Time：2017/9/20
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */

public class GetGameListPresenter extends BasePresenter<GetGameListContract.getGameListDataView> implements GetGameListContract.getGameListModel {
    @Override
    public void getGameListData() {
        if (getView() == null) {
            return;
        }
        TasksRepositoryProxy.getInstance().getHotGame(new LoadTaskCallback<GameListBean>() {
            @Override
            public void onStart() {
                getView().showLoading();
            }

            @Override
            public void onTaskLoaded(GameListBean data) {
                getView().getGameListDataSuccess(data);
            }

            @Override
            public void onDataNotAvailable(String msg) {
                getView().getGameListDataFailed(msg);
            }

            @Override
            public void onCompleted() {
                getView().hideLoading();
            }
        });
    }
}
