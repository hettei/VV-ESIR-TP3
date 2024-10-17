# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. **assertTrue(3* .4 == 1.2)**
The assertion  may fail due to floating-point precision issues in Java.In fact java uses the IEEE 754 standard to store floating-point numbers, which can lead to inaccuracies in calculations.
This type of verification should be performed using the approach with a margin of error. We could use an epsilon (a small value) that will serve as the acceptable tolerance margin for comparisons.

2. **difference between assertEquals and assertSame**
The difference between `assertEquals` and `assertSame` lies in the fact that `assertEquals` checks if two values are equal, 
while `assertSame` verifies that two references point to the same object.

`assertEquals` and `assertSame` produce different results when the two objects being compared have the same content but are distinct 
instances in memory.
Example:

````java
String str1 = new String("hello");  // Création d'une nouvelle instance
String str2 = new String("hello");  // Une autre instance avec le même contenu

assertEquals(str1, str2);  // Test réussi car le contenu est identique ("hello")
assertSame(str1, str2);    // Test échoué car ce ne sont pas la même référence
````

`assertEquals` and `assertSame` produce the same result when the two references being compared point to the same object in memory.

Example:

````java
String str1 = "hello";
String str2 = str1;  // str1 et str2 pointent vers le même objet

assertEquals(str1, str2);  // Test réussi : même contenu
assertSame(str1, str2);    // Test réussi : même référence

````
3. 
-f`fail()` can be used to mark code blocks as unreachable. In fact, it can be used to ensure that certain branches of code are never 
reached, for example, in a default block of a switch statement or after an instruction that should always trigger a return.

Example:
````java
public void processInput(int input) {
    switch (input) {
        case 1:
            // traitement pour input 1
            break;
        case 2:
            // traitement pour input 2
            break;
        default:
            fail("Input non pris en charge : " + input);  // Le test échoue si un input non prévu est reçu
    }
}

````
-`fail()` can be used to stop a test and signal an error if a prerequisite condition necessary for the test to run correctly is not met.
 This helps avoid testing code in an incorrect state.

Example:
````java
Copier le code
@Test
public void testPrecondition() {
    if (!somePreconditionIsMet()) {
        fail("La condition préalable n'est pas respectée, le test ne peut pas continuer");
    }
    // Suite du test uniquement si la condition préalable est respectée
}
````
-`fail()` can be used to capture unexpected cases that should never occur when writing tests for complex logic.

example:
````java
@Test
public void testComplexLogic() {
    boolean result = complexMethod();
    
    if (result) {
        // Cas logique prévu
    } else if (otherCondition()) {
        // Autre cas logique prévu
    } else {
        fail("Condition inattendue : le test a échoué");  // Échec si aucune des conditions n'est remplie
    }
}

````
4. 
The advantages of `assertThrows()` in JUnit 5 compared to `@Test(expected)` in JUnit 4 lie in the fact that `assertThrows()` allows you 
to specify exactly where the exception should be thrown, preventing errors from being masked. This avoids situations where an exception 
is thrown at a different point in the test, which could lead to unnoticed errors. Additionally, it allows for capturing the exception 
and checking properties such as error messages.

Another advantage is that in methods with complex logic or multiple branches, `assertThrows()` enables precise verification of the parts
 of the code that should throw exceptions, without interfering with other parts of the test.