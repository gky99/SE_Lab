# Bank System
## Usage
1. Make sure you have installed java 1.8 or later on your and set the "PATH" properly.
2. Extract the source code to a desired path.
3. Open the Command Line Tool or terminal.
4. Change the working directory to upper directory of Bank folder.
5. Create a folder named out.
6. run `javac -d out Bank\Main.java` to compile source code.
7. run `cd out`
8. run `jar cf Bank.jar Bank` to create jar file.
9. run `java -cp Bank.jar Bank.Main` to run the `Main` class.

Mac or Unix like system users please change the path separator in the commands by your self.

## Interface
> Input 1 to log into your account.
> Input 2 to open a new account.
> Input 3 to exit.

You will get prompt like this.
Follow the instruction of the prompt, you can use all the function of this program.
Illegal input will be eaten, and program will ask you to input again.

## More
Current version of the program doesn't contain save and load function.
So you either need to realize the save and load function by yourself or keep the program not stopped to avoid data lost.

In order to try all the functions of the functions of the program at the first glance, you may run the program from the `main` in `Test.class` in Test folder.