# Android Calculator
This is a basic calculator app for Android. 

The source code builds the following APKs:
- ```app-debug.apk```
- ```app-release unsigned.apk```

Features include:
- Addition
- Subtraction
- Multiplication
- Division
- Sine (In beta)
- Cosine (In beta)
- Tangent (In beta)

This app also includes various code projects I've worked on during my spare time, such as a sandbox with random primitive shapes in a canvas, and a gravity simulator. There are also links to other projects hosted on the web that I've included. Feel free to ignore them.

**Note**: all versions prior to v2.0 are signed with a debug key, which expires after one year from its creation date, ```1/4/2018```. (More  information can be found [here](https://developer.android.com/studio/publish/app-signing#expdebug).) That means app versions prior to v2.0 may no longer work starting from ```1/4/2019```. Version 2.0 is signed with a legitimate certificate, which is guaranteed to last 25 years from *its* creation date, ```8/6/2018```. Also, future versions of this app will continue to be signed with the same certificate.

How to build
----------------
Android Calculator uses the [Gradle](https://gradle.org/) build system. If you don't already have Android Studio installed on your computer, follow these instructions.
1. Download the project's source files either through ZIP file or Github Desktop.
2. Download Android Studio, which includes the Android SDK and Gradle, from [here](https://developer.android.com/studio/). Note: Android Studio and the SDK do take up a large amount of storage space, so make sure you have enough on your ```C:\ ``` drive. The installation might take a few minutes depending on your current computer specs or bandwidth speed.
3. Once Android Studio is downloaded, you should see a welcome screen for Android Studio. Click on *Import project (Eclipse ADT, Gradle, etc...)*.
4. Navigate to the directory where the project's files are located, and select the project to build it. (It should have a Gradle icon next to the name.)

Otherwise, follow these instructions.
1. Download the project's source files either through ZIP file or Github Desktop.
2. Start Android Studio and make sure to close any open Android Studio projects.
3. From the Android Studio menu bar, select **File >> New >> Import Project**.
4. Navigate to the directory where the project's files are located, and select the project to build it. (It should have a Gradle icon next to the name.)

Contributing
----------------
If you see any bug that can be fixed, or improvement that can be made, fork the repository and make the change! Anyone is welcome to submit a pull request, which I will look over, and either approve, provide constructive criticism, or improve upon them myself before merging.

If you decide to re-enable the *unused methods* warning, and see any methods that are marked as *unused* by Android Studio, please don't remove them. Those are methods I have implemented so I can use them in the future.  

#### Known bugs:
- Settings don't save when the user quits the app.
- Trigonometric functions on the calculator don't have full support with the four arithmetic operations.
