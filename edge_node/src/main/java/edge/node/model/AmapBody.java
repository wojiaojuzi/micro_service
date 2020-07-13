package edge.node.model;

public class AmapBody {
    public String status;

    public String info;

    public String infocode;

    public String province;

    public String city;

    public String accode;

    public String rectangle;

    @Override
    public String toString(){
        return "省份:"+this.province+" 城市:"+this.city+" 经纬度对:"+rectangle;
    }
}
