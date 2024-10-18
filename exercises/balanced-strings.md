# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

1. We identified 3 characteristics: the size of the string (empty, one character, multiple characters), the "balanceness" of the string (too many opening, too many closing, balanced), and the nestedness of the string (good nesting, bad nesting).
That leads us to the following initial set of inputs: 
- empty string : ""
- one char, too many closed: "("
- one char, too many opened: ")"
- one char, balanced: "a"
- multi char, too many open: "({}"
- multi char, too many closed: "{}]"
- multi char, balanced, good nesting: "({{([])}})[{}]"
- multi char, balanced, bad nesting: "(([)){]}"

2. We evaluated test coverage with jacoco. The coverage report indicated a coverage of 100%.

3. We evaluated test coverage with jacoco. The coverage report reported a branch coverage of 89% with three partially covered lines in an if case. However, we are not sure what is causing this, as the corresponding tests seem to have been implemented.

4. We ran mutation tests with PIT. We achieved a mutation score of 100% with 0 live mutants.