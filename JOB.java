/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notagain;

import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author Marcin
 */
public class JOB {
    

    String jobName;
    String jobLocation;

        
    public JOB (String jName, String jLocation){
        jobName = jName;
        jobLocation = jLocation;
    }
        
        
    public String getJobName(){
        return jobName;
    }
    public void setJobName (String jName){
        jobName = jName;
    }
    public String getJobLocation(){
        return jobLocation;
    }
    public void setJobLocation (String jLocation){
        jobLocation = jLocation;
    }
        
}
