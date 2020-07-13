package edge.node.model;

public class LocBody {
    private String province;

    private String city;

    private String lon;//经度

    private String lat;//纬度

    public LocBody(String province, String city, String lon, String lat) {
        this.province = province;
        this.city = city;
        this.lon = lon;
        this.lat = lat;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLon() {
        return this.lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
