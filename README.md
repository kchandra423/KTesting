[![CircleCI](https://circleci.com/gh/kchandra423/KTesting/tree/master.svg?style=svg)](https://circleci.com/gh/kchandra423/KTesting/tree/master)
# KTesting
[![](https://jitpack.io/v/kchandra423/KTesting.svg)](https://jitpack.io/#kchandra423/KTesting)


A basic testing library for testing outputs of methods using reflection
***

## Features

- Assert the output of a function
- Assert the value of a function
- Throw descriptive and simple errors that are beginner-friendly when assertions fail.

***

## Why not just use JUnit?

Junit is great for testing your own code, but this library was developed to be used to test the code of AP Computer
Science A student's projects with a GitHub action. This would compile the tester file with every student's project so
you don't know the exact fully qualified name of each student's project, so this library finds it for you. Once it is
found, you could just use Junit, but since GitHub actions are shown as a tree, you only really need to run one test at a
time. Junit is good for running multiple tests at a time, but a little much for just running one, as every test would
need to be put in a separate class without the use of a build tool like ant. Finally the last reason to use this library
over JUnit is that the feedback it gives is much easier for beginner programmers to understand.
***

## How to use

Statically import any assertions that you want to use. Then, anywhere that you want to assert that a function returns
the correct value, call the corresponding kAssert function. If the test fails, it will throw a KException, and kill the
program, so only use assertions within your testing environment. If the test passes, it will print out some information
about the test.

### Example

> `import static kchandra423.kTesting.kAssertions.KAssert.kAssertTrue;`
>
> `ArrayList<String> empty = new ArrayList<>();`  
> `kAssertTrue("isEmpty", empty)` // Will print out success message  
> `kAssertTrue("contains", empty, "hello")` // Will throw an error More examples can be found in the [examples folder](src/test/java/examples)
***

### Quirks

When using assertions, asserting field variables will be able to view variables regardless of their access modifier.
However, it will not be able to view inherited fields. On the contrary, asserting values of methods will only be able to
view public methods. However it can view inherited methods.

## How to download

Navigate to [the dist directory](dist/KTesting.jar)
within this repo and download that jar and simply add to your project.
