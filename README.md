# Software-Extractor

## Purpose

Software to parse and display vital information form Java Source Code. 

Information includes:
- Classes
- Methods
- Parameters
- Class Hierarchies

Your project is to design and implement a computer program that can
 read source files containing C++, Java, C# (all pre-approved) or some other object-oriented programming language (must be pre-approved but probably won’t be)
 take a “snapshot” the system. 

Extract and maintain in some type of model information about the classes and methods in the source code present the extracted model in a reviewable form either in text or graphical user interface
OO-PIES must present at least the following information for version 1: the name of all classes declared in the source files the names and types and access delimiter of all data members of the class the name and types and access delimiter of all methods of the class and return type
class hierarchy, aggregates and association relationships with other classes
 for each method, a list of the methods it calls along with the object reference

---

## Table of Contents

> If your `README` has a lot of info, section headers might be nice.

- [Installation](#installation)
- [Features](#features)
- [Team](#team)
- [FAQ](#faq)
- [License](#license)

---

## Installation

### Clone

- Clone this repo to your local machine using 

`git clone https://github.com/Mcurci95/Software-Extractor.git`

### Setup
> Using IntelliJ IDEA
- Install [ANTLR v4 grammar plugin](https://plugins.jetbrains.com/plugin/7358-antlr-v4-grammar-plugin)

---

## Features

> Use ANTLR to parse Java file
- Use maven goal antlr:antlr in Maven plugins to build ANTLR recognizer classes
- Put java file into "resources" directory
- Put filename in the command line argument
- Run Gen.main()

> Run Spring
- Execute Cecs547Application.main()
- Navigate to localhost.com:8080 to view pages

---

## Team

- Dennis Lo
- Liam Nguyen
- Marco Curci

---

## FAQ

---

## Support

---

## License

[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)

- **[MIT license](http://opensource.org/licenses/mit-license.php)**


