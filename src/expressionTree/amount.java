package expressionTree;

public class amount {
    private double amount;

    public amount(double amount){
        this.amount = amount;
    }

    public amount(String form){
        Tree tree = new Tree(form);
        this.amount =  tree.evaulate();
    }

    public double evaulate(){
        return this.amount;
    }
}
