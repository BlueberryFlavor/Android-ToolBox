package cn.itcast.f13_final_application.entity;

public class NewsResult {
    private int error_code;
    private String reason;
    private NewsInfo result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

//    public Object getResult() {
//        return result;
//    }
//
//    public void setResult(Object result) {
//        this.result = result;
//    }


    public NewsInfo getResult() {
        return result;
    }

    public void setResult(NewsInfo result) {
        this.result = result;
    }
}
