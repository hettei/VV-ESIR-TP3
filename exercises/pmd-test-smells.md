# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

The PMD rule **UseAssertSameInsteadOfAssertTrue** captures the **test smell of ineffective assertions**. This test smell occurs when assertions like `assertTrue(a == b)` are used to compare object references, whereas using `assertSame(a, b)` would be more appropriate.

we have also The **JUnitTestsShouldIncludeAssert** rule implements the **Useless Test** or **Empty Test** smell. It identifies unit tests that do not contain any assertions and, therefore, are considered useless.

The PMD rule **JUnitTestContainsTooManyAsserts** implements the test smell **piggybacking**. This rule detects test cases that contain an excessive number of assertions, which may indicate that multiple aspects or behaviors are being tested within a single test, making it less clear and harder to maintain.

The PMD rule **UnnecessaryBooleanAssertion** detects trivial or unnecessary assertions in tests, such as the use of `assertTrue(true)` or `assertFalse(false)`, which don't add any real value to the test.

We found a test that lacks both assert() and fail() at line 111 of BasicParserTest.java. Indeed, the issue flagged by PMD in the test file related to BasicParserTest is that the test testMissingArgWithBursting() does not include any assertions or a fail() statement to validate the expected behavior. In other words, the test is either empty or incomplete and doesn't verify anything.

As an improvement to the testMissingArgWithBursting() test case in BasicParserTest.java, we could add a simple assertion to ensure that the call to flatten() (executed in BasicParser.java) returns the same arguments, in line with the logic of this class. Here's a simplified example of the improved test:

````java
@Test
public void testMissingArgWithBursting() throws Exception {
    BasicParser parser = new BasicParser();
    Options options = new Options();
    options.addOption("b", false, "burst mode");

    // Arguments to be tested
    String[] args = new String[]{"-b"};

    // Call to flatten()
    String[] result = parser.flatten(options, args, false);

    // Check that the result matches the input arguments
    assertArrayEquals(args, result);
}


````
