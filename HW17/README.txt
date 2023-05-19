Running the code
Step 1: Navigate to the project folder using your terminal.
Step 2: Make sure that Java 16 version is used.
Step 3: If you want to run the deadlock version, then uncomment the code in PowerCalculator's main function.
Step 3: If you want to run the non-deadlock version, then comment the code in PowerCalculator's main function.
Step 4: Run "ant", that's it.

Output
- For the deadlocked code, it can be seen that one thread has the value of "a", waiting for the value of "b"
and the other thread has the value of "b" but waiting for value of "a" which leads to deadlock.
- For the code where the deadlock condition is prevented, lock ordering is used based on the values of "a" and "b".
If the value of "a" is less than "b", then lockA will be taken followed by lockB, else lockB will be taken first
followed by lockA which resolves the deadlock.