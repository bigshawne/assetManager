package parsing;

public class amount {
    int id;
    String form;
    double amount;
    public amount(int id, String form, double amount){
        this.id = id;
        this.form = form;
        this.amount = amount;
    }

    public double evaulate(){
        double result = 0;
        if(form.isEmpty())
            return amount;
        parsing p  = new parsing(form);
        //statement s = p.statement();
        //result = s.evaulate();
        return result;

    }



}
