/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlasset;

/**
 *
 * @author user
 */
public class cell {
    private String content;
    private int id;
    private double no;
    
    public cell(){
        this.content = "";
    }
    
    public cell(int id){
        this.id = id;
        this.content = ""+id;
    }
    
    public cell(String content){
        this.content = content;
    }
    
    public cell(double no){
        this.no = no;
        this.content = ""+no;
    }
    
    @Override
    public String toString() {return this.content;}
    
    public int getID(){return this.id;}
    
    public double getNo(){return this.no;}
    
    public void setContent(String s){this.content = s;}
    public void setNo(double no){this.no = no;}


}
