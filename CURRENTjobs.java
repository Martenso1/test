/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notagain;


/**
 *
 * @author Marcin
 */
public  class CURRENTjobs{
    
        
    String currJobName;
    String currJobLoc;      
        
        
    public CURRENTjobs(String currJName,String currJlocation){
        currJobName = currJName;
        currJobLoc = currJobLoc;   
    }

           public String getCurrJobName(){
            return currJobName;
        }
        public void setCurrJobName(String currJName){
            currJobName = currJName;
        }
        public String getCurrJobLoc(){
            return currJobLoc;
        }
        public void setCurrJobLocation(String currJlocation){
            currJobLoc = currJlocation;
        }
    
    
    
    
    
    
}
