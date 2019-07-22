Person information java application

This project is Person Information desktop application made in java-SE in eclipse IDE with database(MySQL)

//Purpose of the project is to show my implementation of some component in swing, using MVC design pattern then save the collected data in database and the use of serialization to save the collected data in your machine by exporting it and back to the program by importing the file



how to use

-clone this repository 

-open your xampp start the apache and mysql (for xampp instruction.) 

-create a database name persondb then import persondb.sql in your database located inside the folder, for not xampp user import the persondb.sql to your database manager

-open the LogInForm.java then run (PersonInformationDeskTopAppWithDatabase>src>gui>LogInForm.java)

-create a user account by going to registerform 

-then log-in

-you can now have a own set of person information


---------------------------------------------------*functionality*---------------------------------------------------------------------

note:

-user can have own set of person information

-every user must have a unique email address

-can insert/view/update/delete row


Add Person panel (left side) 

fill up the fields in add person panel and click ok button and save it in table(right side)

Save button(upper left side) 
 
 -will save the collected data in the database

Update Button(beside Save button)

-will update the data in the database

Refresh button(beside Update button)
 
 -will get the data in database and put it in table(right side)

delete row
 
 -right click the row that you want to delete then click the pop-up menu(delete row) if you click ok the data in the data base will be deleted permanently

Edit row
 
 -you can edit the column that are editable, Name,CP number,Occupation,Tax ID,and notes are editable, select column and row that you want to edit by double click then save it to make changes

JSplitter
 
 -the Add Person panel and table is added to jsplitter so that you can resize the two column
 
 

------------------------------------------------------*MENU*----------------------------------------------------------------------

File

 -it contain 3 menu item

Export data
 
 -save your data in your machine, its ok if you dont put a file extension to save your file

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



