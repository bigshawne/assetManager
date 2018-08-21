package expressionTree;

public class Node {
    private String str;
    private Node lhs;
    private Node rhs;

    public Node(){}

    private Node(String str){
        this.str = str;
    }

    public Node(Node lhs, Node rhs, String str){
        this.lhs = lhs;
        this.rhs = rhs;
        this.str = str;
    }

    private String getStr(){return this.str;}
    private void setStr(String str){this.str = str;}

    private Node getLhs() {return this.lhs;}
    private void setLhs(Node lhs) {this.lhs = lhs;}

    private Node getRhs(){return this.rhs;}
    private void setRhs(Node rhs){this.rhs = rhs;}

    private boolean isDigit(){
        char c = this.str.charAt(0);
        return Character.isDigit(c);
    }



}
