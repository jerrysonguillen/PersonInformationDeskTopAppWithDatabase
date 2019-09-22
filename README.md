					Title:Person information java application

//this is my simple program for portfolio purposes

-----------------------------------------------About--------------------------------------------------------------------
This project will have many user with unique email address and every user can have their own set of information/contacts of the person list that they save in database they can also save they person list to they machine and put back again by the use of IO in java

-----------------------------------------------Technologies-------------------------------------------------------------
* Java 5 SE
* Swing framework
* Eclipse IDE
* Mysql
* MVC design pattern
* GitHub

-----------------------------------------------How to use--------------------------------------------------------------

-clone/pull/download this repository 

-open your xampp start the apache and mysql (for xampp instruction.) 

-create a database name persondb then import persondb.sql in your database located inside the folder, for not xampp user import the persondb.sql to your database manager

-open the LogInForm.java then run (PersonInformationDeskTopAppWithDatabase>src>gui>LogInForm.java)

-create a user account by going to register form 

-then log-in

-you can now have a own set of person information


-----------------------------------------------*functionality*---------------------------------------------------------------------

-Have a Log-in form

-Have a Registration form

-User can have own set of person information/contact

-Every user must have a unique email address

-BREAD(Brows, Retrieve, Edit,Add, Delete) is implemented

-Username and password must be 8-30 character

-It has a local server(Mysql database)

-----------------------------------------------User Interface-----------------------------------------------
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



