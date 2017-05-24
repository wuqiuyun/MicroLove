package com.mmt.microlove.model.callback;

import com.baidu.location.BDLocation;

/**
 * Created by wuqiuyun on 2017/4/16.
 */
public interface BdLocationCallBack {
    /**
     * 当定位结束时调用
     * @param location
     */
    void onCompletePosition(BDLocation location);
}
