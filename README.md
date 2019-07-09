#Person information java application

This project is Person Information made in java swing desktop application with database

//the purpose of the project is to show my implementation of some component in swing and use MVC structure pattern and save the collected data in database

how to use
-clone this repository 
-open your xampp start the apache and mysql (xampp)
-import persondb in your database located inside the folder
-open the App.java then run

*********************function**************************
Add Person panel (left side) 

note:
always refresh and make sure you save the collection of data
all data in the table once it save the data in the database will updated/insert

fill up the fields in add person panel and click ok button and save it in table(right side)
it will save in the table with incremented id number

Save button(upper left side) 
-will save the collected data in the database

Refresh button(beside Save button)
-will get the data in database and put it in table(right side)

delete row
-right click the row that you want to delete then click the pupup menu(delete row) if you click ok the data in the data base will be deleted

Edit row
-you can edit the row that are editable, not all column is editable, select column and row that you want to edit by double click that row and column then save it to make changes

JSplitter
-the Add Person panel and table is added to jsplitter so that you can resize the two column

----------------MENU---------------------------

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

----------------accelator--------------

ctrl+i  = import file
ctrl+e = export file
ctrl+x = exit the program



