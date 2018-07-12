package com.shuimuhuatong.assetsadmin.bean;

import java.util.List;

/**
 * Created by wangchong on 2018/7/11 17:09
 */
public class CarProblem {

    /**
     * content : {"vehicleProblem":[{"CODE_NAME":"整形","CODE_VALUE":"1"},{"CODE_NAME":"喷漆","CODE_VALUE":"2"},{"CODE_NAME":"损坏","CODE_VALUE":"3"},{"CODE_NAME":"其他","CODE_VALUE":"4"}]}
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
        private List<VehicleProblemBean> vehicleProblem;

        public List<VehicleProblemBean> getVehicleProblem() {
            return vehicleProblem;
        }

        public void setVehicleProblem(List<VehicleProblemBean> vehicleProblem) {
            this.vehicleProblem = vehicleProblem;
        }

        public static class VehicleProblemBean {
            /**
             * CODE_NAME : 整形
             * CODE_VALUE : 1
             */

            private String CODE_NAME;
            private String CODE_VALUE;

            public String getCODE_NAME() {
                return CODE_NAME;
            }

            public void setCODE_NAME(String CODE_NAME) {
                this.CODE_NAME = CODE_NAME;
            }

            public String getCODE_VALUE() {
                return CODE_VALUE;
            }

            public void setCODE_VALUE(String CODE_VALUE) {
                this.CODE_VALUE = CODE_VALUE;
            }
        }
    }
}
