package parsing;

import sqlasset.assetDAO;

public class parsing {
    String stmt;
    public parsing(String stmt){
        assetDAO model = new assetDAO();
        model.connect();
        this.stmt = stmt.trim();

    }




}
