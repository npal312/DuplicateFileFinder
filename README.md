# DuplicateFileFinder
Compares the content of all files in a given directory and makes a "duplicates.log" file listing files that have the same content as others in the directory.

Assignment Description: (From SSW-315)

Design a utility class named DuplicateFileFinder.

The program should compare the contents of all files in the directory given as an argument and produce a "duplicates.log" file that lists a group of files that have the same content.
Note that file contents could be binary and you should use the appropriate File I/O.
Speed up the comparison process by comparing file sizes and then using MessageDigest to obtain a cryptographic summary of files.
Recursively process all files in subfolders of the given folder. 
 

Notes:

Assignment requirements may change before the deadline.
You can continue updating your work and submitting newer versions before the deadline. Only the last version before the deadline will be graded.
Perform a run on a folder of images where some of the images are copied. When uploading the results of your test run, make sure to include a snapshot of your code and test result within the console of your IDE.
Note that, you would need to provide path as Arguments within the "Run Configuration" menu.
You may print contents of duplicates.log. 
Below is a sample output where summary line is highlighted with preceding # symbol, and provides count of files that have the same content, their file size (closest unit of bytes, KB, MB, GB, TB with up to 2 decimal points), and the message digest output separated by tab. Then the full path of the files will be followed with an empty line.
 

Sample Output of duplicates.log:

#    2    365.20 KB     e5dadf6524624f79c3127e247f04b548 
e:/courses/ssw315/slides/week1.ppt
e:/courses/ssw315/lecture1.ppt

#    4     1,010 Bytes     6219b1bc3542949e012616059409f1cc
e:/courses/ssw315/assignments/hw1/src/DateHelper.java
e:/courses/ssw315/assignments/hw2/src/DateHelper.java
e:/courses/ssw315/excercise/week1/DateHelper.java
e:/courses/ssw315/workspace/src/DateHelper.java

...
 

Submission:

Java source code named DuplicateFileFinder.java
UML diagram to show your design.
Screenshot of your test run as pdf or image (jpg, jpeg, png)
duplicates.log file for the sample run.
