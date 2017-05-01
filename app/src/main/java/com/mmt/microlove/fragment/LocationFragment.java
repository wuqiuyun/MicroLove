package com.mmt.microlove.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.mmt.microlove.R;
import com.mmt.microlove.model.callback.BdLocationCallBack;
import com.mmt.microlove.utils.BaiduLocatUtil;
import com.mmt.microlove.utils.Constants;
import com.mmt.microlove.utils.LogUtil;
import com.mmt.microlove.utils.ToastUtil;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

//假如用到位置提醒功能，需要import该类
public class LocationFragment extends Fragment {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;
    private BitmapDescriptor mCurrentMarker;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_location, null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    private void initData() {
        initBaiduMap();
    }

    //初始化百度地图
    private void initBaiduMap() {
        // 获取地图控件引用
        mMapView = (MapView) view.findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMyLocationEnabled(true);// 开启定位图层
        //注册监听函数
        BaiduLocatUtil.initLocation();
        BaiduLocatUtil.setListener(new BdLocationCallBack() {
            @Override
            public void onCompletePosition(BDLocation location) {
                Log.e("joey", location + "  回调");
                showMyLocation(location);
                showLocationInfo(location);
            }
        });
    }

    //在地图上定位当前位置
    private void showMyLocation(BDLocation location) {
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 在百度地图上进行定位数据显示;
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        mBaiduMap.setMyLocationData(locData);
        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.mipmap.icon_geo);
        MyLocationConfiguration.LocationMode mCurrentMode = Constants.LOCATIONMODE_FOLLOWING;
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfigeration(config);
        // 当不需要定位图层时关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
    }

    //Log打印当前定位信息
    private void showLocationInfo(BDLocation location) {
        String addr = location.getAddrStr();
        StringBuffer sb = new StringBuffer();
        sb.append("lat:");
        sb.append(location.getLatitude());
        sb.append("\nlng:");
        sb.append(location.getLongitude());
        sb.append("\nradius:");
        sb.append(location.getRadius());
        sb.append("\naddr:");
        sb.append(location.getAddrStr());
        Log.e("joey", sb.toString());
    }

    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
           // mBaseMessageText.setText("onComplete:");
            doComplete(response);
            LogUtil.i(Constants.TAG,"BaseUiListener-->onComplete-->response:"+response.toString());
        }
        protected void doComplete(Object values) {

        }
        @Override
        public void onError(UiError e) {
            ToastUtil.shortShow("onError:"+"code:" + e.errorCode + ", msg:"
                    + e.errorMessage + ", detail:" + e.errorDetail);
        }
        @Override
        public void onCancel() {
            ToastUtil.shortShow("onCancel");
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        // 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        // 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
