# KTesting
A basic testing library for testing outputs of methods using reflection
***
> ## Features
- Assert the output of a function  
- Assert the value of a function
- Throw descriptive and simple errors that are beginner-friendly when assertions fail.
***
> ## Why not just use JUnit?
Junit is great for testing your own code, but this library was developed
to be used to test the code of AP Computer Science A student's projects, where the 
exact implementation and interface of each student's project may vary, so package names of classes may vary.
This leads to the need to find classes by name, and then reflectively call their methods, which is the use case for this 
library. Errors thrown by the assertions also specify the name of each method called, making things easier to read
by beginners. 
***
> ## How to use
Statically import any assertions that you want to use. Then, anywhere that you want to assert that a function returns the correct value,
call the corresponding kAssert function. If the test fails, it will throw a KException, and kill the program, so only use assertions within
your testing environment. If the test passes, it will print out some information about the test.
> ### Example
`import static kchandra423.kTesting.KAssertion.kAssertTrue;`

`ArrayList<String> empty = new ArrayList<>();`  
`kAssertTrue("isEmpty", empty)` // Will print out success message  
`kAssertTrue("contains", empty, "hello")` // Will throw an error
***
> ## How to download
Navigate to out/artifacts/KTesting_jar/KTesting.jar within this repo and download that jar and simply add to your project.
