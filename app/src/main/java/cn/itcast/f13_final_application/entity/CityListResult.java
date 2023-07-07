package cn.itcast.f13_final_application.entity;

import java.util.List;

public class CityListResult {

    private String reason;
    private List<CityInfo> result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<CityInfo> getResult() {
        return result;
    }

    public void setResult(List<CityInfo> result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }


}
