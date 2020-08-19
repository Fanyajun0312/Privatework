package com.example.three.bean;

/**
 * @date：2020/8/19
 * @describe：
 * @author：FanYaJun
 */
public class RegBean {
    /**
     * status : 0
     * message : 登录成功
     * data : {"id":4,"userName":"15133951473","userMobile":"15133951473","pushId":"11"}
     */

    private int status;
    private String message;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4
         * userName : 15133951473
         * userMobile : 15133951473
         * pushId : 11
         */

        private int id;
        private String userName;
        private String userMobile;
        private String pushId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserMobile() {
            return userMobile;
        }

        public void setUserMobile(String userMobile) {
            this.userMobile = userMobile;
        }

        public String getPushId() {
            return pushId;
        }

        public void setPushId(String pushId) {
            this.pushId = pushId;
        }
    }
}
