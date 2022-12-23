# ImgurDemo
An demo application which fetch the top week images form the imgur according to user's choice and show it in list and grid mode.

### Run Instruction
- Clone the master branch or download it 
- Build the project
- goto NetworkConstant.kt file -- at app\src\main\java\com\pratik\imgurdemo\model\networkAPI\ and replace the text "paste here your Client-Id" with your Client-Id
- Run the project.

### List of Assumptions made 
- Initial mode is List Mode
- App should work on both the orientation
- App's  minSdk is set to be 23

### Application Design 
- Application is desiged with clean architecture with kotlin

### Libraries used 
- Retrofit: for network calling
- Glide: for Image processing
- Hilt: Dependency Injection
- Other Jetpack components like Jetpack Navigation, LiveData.

### Demo APK
Download and install the demo apk. [Download Apk](https://github.com/PratikVishwakarma/ImgurDemo/blob/main/app.apk) 

### Resource
- Used https://flaticon.com/ for icons

### Images
![List View] (https://github.com/PratikVishwakarma/ImgurDemo/blob/main/List_view.png?raw=true)
![Grid View] (https://github.com/PratikVishwakarma/ImgurDemo/blob/main/grid_view.png?raw=true)
![Grid-Land View] (https://github.com/PratikVishwakarma/ImgurDemo/blob/main/Grid_land.png?raw=true)
