package cn.itcast.f13_final_application.entity;
import java.util.List;
public class Province {
    private String province;
    private List<City> cities;

    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public List<City> getCities() {
        return cities;
    }
    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
