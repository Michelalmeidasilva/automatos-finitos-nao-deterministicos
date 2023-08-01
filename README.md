# Sprint 1 - Non-Deterministic Finite Automata by Group 1

## Introduction

This project is a part of Sprint 1, where we focus on Non-Deterministic Finite Automata (NFA). An NFA is a mathematical model used in computer science and linguistics to describe pattern matching and recognition tasks. This project aims to implement an NFA and provide utilities for working with NFAs.

## Installation

Before running the project, ensure that you have the following prerequisites installed on your machine:

1. Java JDK: [Download Java JDK](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
2. Junit 4.1.3: [Download Junit 4.1.3](https://search.maven.org/remotecontent?filepath=junit/junit/4.13/junit-4.13.jar)
3. Gson 2.8.6: [Download Gson 2.8.6](https://search.maven.org/remotecontent?filepath=com/google/code/gson/gson/2.8.6/gson-2.8.6.jar)

After downloading Junit and Gson, import the JAR files into the libraries folder of your project.

## Folder Structure

```
├── /src
|   ├── /domain
|   ├── /exceptions
|   ├── /utils

├── /test
    ├── /java
```

The project is structured with the following directories:

- `/src/domain`: Contains the main classes representing the NFA domain objects.
- `/src/exceptions`: Holds custom exception classes related to the NFA implementation.
- `/src/utils`: Includes utility classes to assist with NFA operations.
- `/test/java`: Contains test classes to verify the functionality of the NFA implementation.

## Non-Deterministic Finite Automata (NFA)

A non-deterministic finite automaton is a mathematical model consisting of states, transitions, and an input alphabet. NFAs can be used to recognize patterns and process strings in various applications like lexical analysis, regular expression matching, and more.

In this project, we aim to provide an NFA implementation that allows users to define states, transitions, and recognize patterns based on input strings.

## Contributing

Contributions to this project are highly encouraged. If you find any issues, want to add new features, or enhance the existing ones, feel free to create a pull request.

Let's work together to make this project better!
