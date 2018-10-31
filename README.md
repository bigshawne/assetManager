# Asset Manager Java Program
This is a simple implementation of basic functions of Excel. 
![b20cfe21d50dc5b4bdc20b15b20b6db](https://user-images.githubusercontent.com/24923284/47767548-1d593b80-dca2-11e8-8831-1c059c615078.png)
This program is using MySQL as database to store the data. User can use the interface to do the operation such as add/delete rows and change the content in each cell (except the first column, which is automatically augmented. Column Item, Description will store the content as String in the database. The amount is stored as double (So there is risk of overflow). The formula will take basic arithmetic such +, -, *, /, between cells, and this is implemented by parsing binary tree.
