#CSV Library
A simple library for processing csv files in java.

Documentation :<Br>
Class csv has two over loaded constructors 
1. Csv(String filename) : takes the file abs path.
2. CSV(String filename, int limit) : takes the file abs path, and an integer to determine how many rows should be read.

>Csv cs = new Csv("Example.csv"); OR :<br>
>Csv cs = new Csv("Example.csv",4);

*If file doesn't exist, it will be created automatically.<br><br>
Methods :<br>
1. `toCsvStructure(String[] row)` : This method takes a string array and returns a string which is the array converted into csv row (data,data,data).
2. `getTitle() ` : This method returns the first row of the csv file as a string array.
3. `getRowsLen()` : Returns the number of the rows.
4. `getRow(int row)` : Returns the specified row as a string array.(0 based and also takes negative number which starts from the end).
5. `getRowMap(int row)` : Return the specified row as a HashMap (passing 0 and 1 will do the same since row number 0 is titles).
5. `getRowsArray()` : This method returns the whole csv file as a 2d String array.
6. `getRowsArrayList()` : Same as `getRowsArray()` but as 2d ArrayLists.
7. `getRowsMap()` : Returns the whole csv file as HashMap which keys are based on the first row of the file.
8. `writeCsv(String[][] rows, boolean append)` : Writes the given 2d string array into the file given to constructor.If append is true the content of the current file will not be erased, otherwise it will truncate file.
9. `writeCsvMap(Map[] rows, boolean append)` : Same as `writeCsv` but takes a map array (each row in map format (TreeMap,HashMap,...)).