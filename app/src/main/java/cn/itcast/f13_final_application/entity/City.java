package cn.itcast.f13_final_application.entity;

import java.util.List;

public class City {
    private String city;
    private List<District> districts;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
