batsignal
=========

a project to send signals to an arduino/rasberry pi when twitter hashtags have been detected 

Prelimiaries
- the code is using a library called twitter4j: http://twitter4j.org/en/index.html

HOW-TO set up the java project
- open eclipse and create a new, empty project with a name you like ;-)
- create a file called "twitter4j.properties" in the eclipse project directory, hit F5 in the "Project Explorer" and make sure you see the file listed

- edit the file like this, use the keys from you twitter account:

debug=true

oauth.consumerKey=01234567890abcdefghi

oauth.consumerSecret=01234567890abcdefghijklmnopqrstuvwxyz01234

oauth.accessToken=01234567890abcdefghijklmnopqrstuvwxyz01234567890ab

oauth.accessTokenSecret=01234567890abcdefghijklmnopqrstuvwxyz012345

- right-click on your project name in the "Project Explorer" => "Build Path" => " Configure Build Path..."
- go to the TAB "Libraries" click on [Add External JARs...] => [Browse...] and select the "java_code/lib/twitter4j-core-3.0.5.jar"-file from this git-project
- then right-click again on your project name in the "Project Explorer" => "Build Path" => " Configure Build Path..."
- go to the TAB "Source" click on [Link Source...] => [Browse...] and select the "java_code/source"-folder from this git-project
- right-click the project name again or got to the menu bar "Run", choose "Run as...", select "Java Application", choose "mainClass - batsignal", if needed and have a look at the "Console"-TAB of eplise.
- done.
- if you want the ouput of your code alone, set "debug=false" in the "twitter4j.properties"-file

![text that shows instead of image](http://mywebsite.com/image.png)
