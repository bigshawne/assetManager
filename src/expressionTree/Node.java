package expressionTree;

import sqlasset.assetDAO;

public class Node {
    private String str;
    private Node lhs;
    private Node rhs;

    public Node(){}

    public Node(String str){
        this.str = str;
    }

    public Node(Node lhs, Node rhs, String str){
        this.lhs = lhs;
        this.rhs = rhs;
        this.str = str;
    }

    public String getStr(){return this.str;}
    public void setStr(String str){this.str = str;}

    public Node getLhs() {return this.lhs;}
    public void setLhs(Node lhs) {this.lhs = lhs;}

    public Node getRhs(){return this.rhs;}
    public void setRhs(Node rhs){this.rhs = rhs;}

    public boolean isDigit(){
        char c = this.str.charAt(0);
        return Character.isDigit(c);
    }

    //无法实现运算字符串中包含当前表格
    public double evaulate(){
        try{
            int id = Integer.parseInt(this.str);
            assetDAO model = new assetDAO();
            model.connect();
            amount a = model.getAmount(id);
            return a.evaulate();
        }catch(NumberFormatException ex){
            return 0;
        }
    }

}
