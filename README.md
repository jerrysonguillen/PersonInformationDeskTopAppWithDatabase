#Person information java application

This project is Person Information desktop application made in java-SE with database(MySQL)

//the purpose of the project is to show my implementation of some component in swing and the use of MVC design pattern then save the collected data in database and the use of serialization to save the collected data in your machine and back in again in the program

how to use

-clone this repository 

-open your xampp start the apache and mysql (for xampp instruction.)

-import persondb.sql in your database located inside the folder

<<<<<<< HEAD
-open the App.java then run (PersonInformationDeskTopAppWithDatabase>src>gui>App)

---------------------------------------------------*function*---------------------------------------------------------------------

=======
------------------function-------------------
>>>>>>> 269187e7ba5f307c952923af008a043eca1530ae
Add Person panel (left side) 

note:
always refresh and make sure you save the collection of data
all data in the table once it's save the database will update

fill up the fields in add person panel and click ok button and save it in table(right side)
it will save in the table with incremented id number

Save button(upper left side) 
 
 -will save the collected data in the database

Refresh button(beside Save button)
 
 -will get the data in database and put it in table(right side)

delete row
 
 -right click the row that you want to delete then click the pupup menu(delete row) if you click ok the data in the data base will be deleted permanently

Edit row
 
 -you can edit the row that are editable, not all column is editable, select column and row that you want to edit by double click that row and column then save it to make changes

JSplitter
 
 -the Add Person panel and table is added to jsplitter so that you can resize the two column

------------------------------------------------------*MENU*----------------------------------------------------------------------

File

 -it contain 3 menu item

Export data
 
 -save your data in your machine, by now you can save it in any type of file

Import data
 
 -get only your save file and you can retrieve the data in you machine

Exit
 
 -exiting the program

Window
 
 -it has Show menu item that will visible or hide your Add Person Panel

-------------------------------------------------*ACCELATOR*----------------------------------------------------------------------

ctrl+i  = import file
ctrl+e = export file
ctrl+x = exit the program



