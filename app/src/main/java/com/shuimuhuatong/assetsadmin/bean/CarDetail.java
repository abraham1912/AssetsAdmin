package com.shuimuhuatong.assetsadmin.bean;

/**
 * Created by wangchong on 2018/7/11 17:04
 */
public class CarDetail {


    /**
     * content : {"orderDetailedInfo":{"cruisingRadius":"150.0","currentStationAddress":"地铁天通苑北站","currentStationId":"1385","currentStationName":"有车V4.0测试","distance":"400.0","latitude":40.084233,"longtitude":116.407287,"orderDetailsContent":"about zhe order detial ...","orderNo":"2775001037163520","orderOperateId":"87","orderOperateName":"陈小帅","orderStatusId":"4","orderStatusName":"运营处理中","orderTypeId":"1","orderTypeName":"故障","orderUpdateTime":"2018-07-09 16:52:44","vehicleId":"77","vehicleLiscense":"\t京QV8U00","vehicleOnlineStatus":"1","vehiclePlusSelected":"1","vehicleTypeId":"15","vehicleTypeName":"长安奔奔新EV"}}
     * msg : 请求成功
     * ret : 0
     */

    private ContentBean content;
    private String msg;
    private int ret;

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public static class ContentBean {
        /**
         * orderDetailedInfo : {"cruisingRadius":"150.0","currentStationAddress":"地铁天通苑北站","currentStationId":"1385","currentStationName":"有车V4.0测试","distance":"400.0","latitude":40.084233,"longtitude":116.407287,"orderDetailsContent":"about zhe order detial ...","orderNo":"2775001037163520","orderOperateId":"87","orderOperateName":"陈小帅","orderStatusId":"4","orderStatusName":"运营处理中","orderTypeId":"1","orderTypeName":"故障","orderUpdateTime":"2018-07-09 16:52:44","vehicleId":"77","vehicleLiscense":"\t京QV8U00","vehicleOnlineStatus":"1","vehiclePlusSelected":"1","vehicleTypeId":"15","vehicleTypeName":"长安奔奔新EV"}
         */

        private OrderDetailedInfoBean orderDetailedInfo;

        public OrderDetailedInfoBean getOrderDetailedInfo() {
            return orderDetailedInfo;
        }

        public void setOrderDetailedInfo(OrderDetailedInfoBean orderDetailedInfo) {
            this.orderDetailedInfo = orderDetailedInfo;
        }

        public static class OrderDetailedInfoBean {
            /**
             * cruisingRadius : 150.0
             * currentStationAddress : 地铁天通苑北站
             * currentStationId : 1385
             * currentStationName : 有车V4.0测试
             * distance : 400.0
             * latitude : 40.084233
             * longtitude : 116.407287
             * orderDetailsContent : about zhe order detial ...
             * orderNo : 2775001037163520
             * orderOperateId : 87
             * orderOperateName : 陈小帅
             * orderStatusId : 4
             * orderStatusName : 运营处理中
             * orderTypeId : 1
             * orderTypeName : 故障
             * orderUpdateTime : 2018-07-09 16:52:44
             * vehicleId : 77
             * vehicleLiscense : 	京QV8U00
             * vehicleOnlineStatus : 1
             * vehiclePlusSelected : 1
             * vehicleTypeId : 15
             * vehicleTypeName : 长安奔奔新EV
             */

            private String cruisingRadius;
            private String currentStationAddress;
            private String currentStationId;
            private String currentStationName;
            private String distance;
            private double latitude;
            private double longtitude;
            private String orderDetailsContent;
            private String orderNo;
            private String orderOperateId;
            private String orderOperateName;
            private String orderStatusId;
            private String orderStatusName;
            private String orderTypeId;
            private String orderTypeName;
            private String orderUpdateTime;
            private String vehicleId;
            private String vehicleLiscense;
            private String vehicleOnlineStatus;
            private String vehiclePlusSelected;
            private String vehicleTypeId;
            private String vehicleTypeName;

            public String getCruisingRadius() {
                return cruisingRadius;
            }

            public void setCruisingRadius(String cruisingRadius) {
                this.cruisingRadius = cruisingRadius;
            }

            public String getCurrentStationAddress() {
                return currentStationAddress;
            }

            public void setCurrentStationAddress(String currentStationAddress) {
                this.currentStationAddress = currentStationAddress;
            }

            public String getCurrentStationId() {
                return currentStationId;
            }

            public void setCurrentStationId(String currentStationId) {
                this.currentStationId = currentStationId;
            }

            public String getCurrentStationName() {
                return currentStationName;
            }

            public void setCurrentStationName(String currentStationName) {
                this.currentStationName = currentStationName;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public double getLongtitude() {
                return longtitude;
            }

            public void setLongtitude(double longtitude) {
                this.longtitude = longtitude;
            }

            public String getOrderDetailsContent() {
                return orderDetailsContent;
            }

            public void setOrderDetailsContent(String orderDetailsContent) {
                this.orderDetailsContent = orderDetailsContent;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getOrderOperateId() {
                return orderOperateId;
            }

            public void setOrderOperateId(String orderOperateId) {
                this.orderOperateId = orderOperateId;
            }

            public String getOrderOperateName() {
                return orderOperateName;
            }

            public void setOrderOperateName(String orderOperateName) {
                this.orderOperateName = orderOperateName;
            }

            public String getOrderStatusId() {
                return orderStatusId;
            }

            public void setOrderStatusId(String orderStatusId) {
                this.orderStatusId = orderStatusId;
            }

            public String getOrderStatusName() {
                return orderStatusName;
            }

            public void setOrderStatusName(String orderStatusName) {
                this.orderStatusName = orderStatusName;
            }

            public String getOrderTypeId() {
                return orderTypeId;
            }

            public void setOrderTypeId(String orderTypeId) {
                this.orderTypeId = orderTypeId;
            }

            public String getOrderTypeName() {
                return orderTypeName;
            }

            public void setOrderTypeName(String orderTypeName) {
                this.orderTypeName = orderTypeName;
            }

            public String getOrderUpdateTime() {
                return orderUpdateTime;
            }

            public void setOrderUpdateTime(String orderUpdateTime) {
                this.orderUpdateTime = orderUpdateTime;
            }

            public String getVehicleId() {
                return vehicleId;
            }

            public void setVehicleId(String vehicleId) {
                this.vehicleId = vehicleId;
            }

            public String getVehicleLiscense() {
                return vehicleLiscense;
            }

            public void setVehicleLiscense(String vehicleLiscense) {
                this.vehicleLiscense = vehicleLiscense;
            }

            public String getVehicleOnlineStatus() {
                return vehicleOnlineStatus;
            }

            public void setVehicleOnlineStatus(String vehicleOnlineStatus) {
                this.vehicleOnlineStatus = vehicleOnlineStatus;
            }

            public String getVehiclePlusSelected() {
                return vehiclePlusSelected;
            }

            public void setVehiclePlusSelected(String vehiclePlusSelected) {
                this.vehiclePlusSelected = vehiclePlusSelected;
            }

            public String getVehicleTypeId() {
                return vehicleTypeId;
            }

            public void setVehicleTypeId(String vehicleTypeId) {
                this.vehicleTypeId = vehicleTypeId;
            }

            public String getVehicleTypeName() {
                return vehicleTypeName;
            }

            public void setVehicleTypeName(String vehicleTypeName) {
                this.vehicleTypeName = vehicleTypeName;
            }
        }
    }
}
