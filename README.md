Audonizr
========


### What is it?

Audonizr is a command-line utility to reverse the audio files. It is useful for backmasking (a recoding technique used to hide messages in reverse order, on a track which is meant to play forward.)

This utility accepts various command-line arguments to customize the conversion process.
The Audonizr project is still in early development phase. You can read more about the development roadmap here.


### How to Run 

In order to download, use the HTTPS link to clone the project repository on your system:
  
    git clone https://github.com/ashish-chopra/Audonizr.git audonizrsource

This project is developed in Eclipse IDE environment. The downloaded source code can be imported in the Eclipse, and then compile it. In order to run right-click Audonizr in src folder and "run as Java Application".

In order to run,

    java Audonizr -in <path-to-input-file> -out <path-to-output-file>

In order to run tests, in tests/com folder, find UnitTester.java and AcceptanceTester.java which runs the test suites as, 

    java -ea UnitTester            // to run unit test suite of Audonizr
    java -ea AcceptanceTester     // to run acceptance tests suite
    
    
### License

This project is licensed under MIT License.

