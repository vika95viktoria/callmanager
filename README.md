Application allows users to save information about the calls in txt files with LASTNAME_FIRSTNAME.txt fileName. 
The telephone number will be validated for the allowed number characters, -, (,), + and space.
The number is comprised of the local compulsory part of 9 numbers and the optional
international part of 5 numbers. The saved number will be normalized into the uniform format
00YYY XXX XXX XXX, where the X block is the local part and the Y block is the international
part. If no international part is specified on input, it will be automatically saved with the
international area code 420. The + character at the beginning is converted to 00. The +
character can only be at the beginning. The number of start and end brackets must be the
same. The file will contain the number entry time in the HH:mm:ss
format as well as the normalized telephone number. This information will each be placed in
separate rows. The telephone number will be placed in the first row.


## How to run

Simply execute mvn tomcat7:deploy to run the application.

## Available endpoints

There is only one available endpoint: POST /callmanager/calls. Endpoints accept json representation of the call, 
containing information about first and second name and the phone number. Example: 

{"firstName":"Mary", "lastName":"Brown", "telephone":"+351234567554"}

## How to change folder for the files with call information

By default all information will be written into D:/callmanager/callinfo/ folder. If you want to change the folder, just change the property "file.folder" in application.properties file
