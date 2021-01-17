package sample;

import java.io.Serializable;

public class createProject implements Serializable {

    private static final long serialVersionUID = 1L;
    static String projectName;
    static String name;
    static String location;
    static int numOfHelpers;
    static int numOfHelpersAvailable;
    private static createProject instance = null;
    private createProject(){

    }

    public static createProject getInstance(){
        if (instance == null) {
            instance = new createProject();
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
    public void addToHelpers(){
        this.numOfHelpersAvailable = numOfHelpersAvailable + 1;
    }

}
