package tk.mybatis.simple.model;

import java.io.Serializable;

public class CpdaleTou  implements Serializable {

    private Long id;
    private String pahseNum;
    private String proLottery;
    private String backLettery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPahseNum() {
        return pahseNum;
    }

    public void setPahseNum(String pahseNum) {
        this.pahseNum = pahseNum;
    }

    public String getProLottery() {
        return proLottery;
    }

    public void setProLottery(String proLottery) {
        this.proLottery = proLottery;
    }

    public String getBackLettery() {
        return backLettery;
    }

    public void setBackLettery(String backLettery) {
        this.backLettery = backLettery;
    }
}
