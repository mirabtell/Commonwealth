package sample;

import java.io.Serializable;

public class test implements Serializable {

    private static final long serialVersionUID = 1L;
    static String projectName;
    static String name;
    static String location;
    static int numOfHelpers;
    private static test instance = null;
    private test(){

    }

    public static test getInstance(){
        if (instance == null) {
            instance = new test();
        }
        return instance;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public void setNumOfHelpers(int numOFHelper){
        this.numOfHelpers = numOFHelper;
    }
    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

}
