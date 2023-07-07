package cn.itcast.f13_final_application.entity;

public class CityInfo {
    //城市编号
    private String id;
    //该城市所在的省份
    private String province;
    //城市的名称
    private String city;
    //城区（北京是海淀区 ，北京朝阳区）
    private String district;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
