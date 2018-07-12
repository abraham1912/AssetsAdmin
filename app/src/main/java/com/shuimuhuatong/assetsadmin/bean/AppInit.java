package com.shuimuhuatong.assetsadmin.bean;

import java.util.List;

/**
 * Created by wangchong on 2018/7/9 16:37
 */
public class AppInit {
    public Content content ;
    public class Content {
        public Integer alarmNum ;
        public List<OrderStatus> orderStatus ;
        public List<OrderStatus> orderType ;
        public List<OrderStatus> vehicleStatus ;
        public List<OrderStatus> problemArea ;

        public List<VehicleType> vehicleType ;
        public List<WaitProcess> waitProcess ;
        public List<WaitProcess> waitReceive ;

    }

    public class WaitProcess{
        public String ORDER_NUM ;
        public Integer ORDER_TYPE ;
        public boolean isChecked ;
    }

}
