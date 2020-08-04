package edge.node.model;

public class IpApi {
    public String status;
    public String country;
    public String countryCode;
    public String region;
    public String regionName;
    public String city;
    public String zip;
    public String lat;//纬度
    public String lon;//经度
    public String timezone;
    public String isp;
    public String org;
    public String as;
    public String query;

    @Override
    public String toString(){
        return country+" "+regionName+" "+city;
    }
}
