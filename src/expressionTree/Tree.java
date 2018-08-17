package expressionTree;
import java.util.ArrayList;
public class Tree {
    private String s = "";
    private Node root;
    public Tree(String str){
        ArrayList<String> operator = new ArrayList();
        ArrayList<Node> numList = new ArrayList();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(Character.isDigit(c)){
                s+=c;
            }else{
                numList.add(new Node(s));
                s = "";
                operator.add(c + "");
            }
        }
        numList.add(new Node(s));
        while(!operator.isEmpty()){
            Node lhs = numList.remove(0);
            Node rhs = numList.remove(0);
            String op = operator.remove(0);
            Node node = new Node(lhs, rhs, op);
            numList.add(0, node);
        }
        root = numList.get(0);
    }
    
}
