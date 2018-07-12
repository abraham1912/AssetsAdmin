package com.shuimuhuatong.assetsadmin.bean;

import java.util.List;

/**
 * Created by wangchong on 2018/6/26 11:31
 */
public class Task {

    /**
     * content : {"queryVehicleList":[{"orderDetailsContent":"about zhe order detial ...","orderNo":"2775001037163520","orderOperateId":"87","orderOperateName":"陈小帅","orderStatusId":"4","orderStatusName":"运营处理中","orderTypeId":"1","orderTypeName":"故障","orderUpdateTime":"2018-07-09 16:52:44","vehicleId":"77","vehicleLiscense":"京QV8U00","vehiclePlusSelected":"1","vehicleTypeId":"15","vehicleTypeName":"长安奔奔新EV"}],"queryVehicleNum":"1"}
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
         * queryVehicleList : [{"orderDetailsContent":"about zhe order detial ...","orderNo":"2775001037163520","orderOperateId":"87","orderOperateName":"陈小帅","orderStatusId":"4","orderStatusName":"运营处理中","orderTypeId":"1","orderTypeName":"故障","orderUpdateTime":"2018-07-09 16:52:44","vehicleId":"77","vehicleLiscense":"京QV8U00","vehiclePlusSelected":"1","vehicleTypeId":"15","vehicleTypeName":"长安奔奔新EV"}]
         * queryVehicleNum : 1
         */

        private String queryVehicleNum;
        private List<QueryVehicleListBean> queryVehicleList;

        public String getQueryVehicleNum() {
            return queryVehicleNum;
        }

        public void setQueryVehicleNum(String queryVehicleNum) {
            this.queryVehicleNum = queryVehicleNum;
        }

        public List<QueryVehicleListBean> getQueryVehicleList() {
            return queryVehicleList;
        }

        public void setQueryVehicleList(List<QueryVehicleListBean> queryVehicleList) {
            this.queryVehicleList = queryVehicleList;
        }

        public static class QueryVehicleListBean {
            /**
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
             * vehicleLiscense : 京QV8U00
             * vehiclePlusSelected : 1
             * vehicleTypeId : 15
             * vehicleTypeName : 长安奔奔新EV
             */

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
            private String vehiclePlusSelected;
            private String vehicleTypeId;
            private String vehicleTypeName;

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
