package expressionTree;

import java.util.LinkedList;
import java.util.ListIterator;

public class Tree {
    private String s = "";
    private Node root = null;
    private String str;
    private LinkedList<String> exp;

    public Tree(String str) {
        exp = toList(str);
        ListIterator<String> itr = exp.listIterator();
        while(itr.hasNext()){
            String s = itr.next().toString();
            char c = s.charAt(0);
            if(root == null){
                root = new Node(s);
            }
            else if(s.matches("[0-9]+")){
                Node node = getMostRhs();
                node.setRhs(new Node(s));
            }else{
                if(c == 43 || c == 45){
                    Node temp = new Node(s);
                    temp.setLhs(root);
                    root = temp;
                }else if(c == 42 ||c == 47){
                    Node r = getMostRhs();
                    String temp = r.getStr();
                    r.setStr(s);
                    r.setLhs(new Node(temp));
                }
            }
        }
    }

    public double evaulate() {
        return evaulate(root);
    }

    public double evaulate(Node node) {
        if (node.isDigit())
            return node.evaulate();
        else {
            switch (node.getStr()) {
                case "+":
                    return evaulate(node.getLhs()) + evaulate(node.getRhs());
                case "-":
                    return evaulate(node.getLhs()) - evaulate(node.getRhs());
                case "*":
                    return evaulate(node.getLhs()) * evaulate(node.getRhs());
                case "/":
                    return evaulate(node.getLhs()) / evaulate(node.getRhs());
            }
        }
        return 0;
    }

    private LinkedList<String> toList(String str) {
        LinkedList<String> exp = new LinkedList<String>();
        str = str.trim();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                s += c;
            } else if (c == ' ') {
                s += "";
            } else {
                exp.add(s);
                s = "";
                exp.add(c + "");
            }
        }
        exp.add(s);
        return exp;
    }

    private Node getMostRhs(){
        Node node = root;
        while(node.getRhs() != null){
            node = node.getRhs();
        }
        return node;
    }
}
