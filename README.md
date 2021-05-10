# ChessGameJavaFX

### Overview 
This is a chess game application built using Java and the JavaFX client application platform. It incorporates an interactive GUI to allow two human players to play chess simultaneously, implements next available move highlighting and timer functionality, and heavily uses OOP and SOLID software development principals. 
### Purpose 
The purpose for building this application is to gain experience developing JavaFX applications and to gain experience building applications using OOP and SOLID software development principles.
### Platform: 
This application requires a Java IDE(NetBeans, Eclipse for Enterprise Java, IntelliJ IDEA, etc.) and a JavaFX SDK. It was built on Windows, but can run on MacOSX and popular Linux distros(Ubuntu, Debian, Fedora, etc.)
### Local Deployment Usage:
* Clone repo 
* open project folder in your Java IDE 
* Download JavaFX SDK available in [this link](https://gluonhq.com/products/javafx/), pick the version that is right for you 
* Extract JavaFX folder into a folder of your preference 
* If your using Eclipse, go to the 'Window' tab and click on Preferences 
* Then go to 'Java' -> 'Build Path' -> 'User Libraries' 
* Now click on 'New', give a name to this user library such as 'JavaFX15'
* Click on the new created user library then click on 'Add External JARs...'
* In the file selection window, find the location of your extracted JavaFX SDK folder, and add all the jar files in that folder 
* Now, right on your project in the Package Explorer view and click on 'Properties'
* In the 'Libraries' tab, click on 'Class Path', and finally on 'Add Library'
* In the prompt that shows, select 'User Library' then select the user library we just created 
* Finally, right click on the project, hover over 'Run As' and select 'Run Configurations' 
* Here, go to the 'Arguments' tab and in the 'VM arguments' text field paste in the follow command:
```
--module-path "C:\Users\sheha\OneDrive\Documents\GitHub\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml,javafx.media
```
* Now give your run configuration a unique name and make sure to select that configuration whenever you want to run the program in the future 
* Finally, click 'Run' and select 'Main' in the prompt that follows 

### Screenshots:
![Welcome screen](/ChessApplication/static/images/screenshot_1.png)

![Black player to move](/ChessApplication/static/images/screenshot_2.png)

![White player wins!](/ChessApplication/static/images/screenshot_3.png)

![Black knight is pinned](/ChessApplication/static/images/screenshot_4.png)

### Contributing:
Please feel free to contribute to this project however possible by forking this repo, making changes and initiating pull requests. Thanks!
