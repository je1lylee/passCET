package adapter;

/**
 * Created by dell on 2019/9/4.
 */

public class JieXi {

    private String code;
    private String status;
    private String id;
    public void setcode(String code){
        this.code = code;
    }
    public String getcode(String status){
        return this.code;
    }
    public void setstatus(String status){
        this.status = status;
    }
    public String getstatus(){
        return this.status;
    }
    public void setID(String id){
        this.id = id;
    }
    public String getID(){
        return this.id;
    }
    @Override
    public String toString() {
        return this.id + "~年方：" + this.id;
    }
}
