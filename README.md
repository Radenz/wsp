<div id="top"></div>
<br />
<div align="center">
  <a href="https://github.com/Radenz/wsp">
    <img src="wsp.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Word Search Puzzle (Solver)</h3>
<h4 align="center">By Raden Rifqi Rahman (13520166)</h4>
 <p align="center">
    A simple word search puzzle solver written entirely in Java
    <br />
    <a href="examples">Examples</a>
    ·
    <a href="https://wsp-docs.web.app/">Documentation</a>
</div>

## About The Project
This is a simple program written entirely in Java to solve word search puzzle game, i.e., to find all words hidden within the puzzle. This project is made to fulfill `Tugas Kecil 1 IF2211 Strategi Algoritma`.

## Getting Started

### Prerequisites

You will need [Java](https://www.java.com/en/download/) and JDK 17 which can be downloaded [here](https://www.oracle.com/java/technologies/downloads/) to build the program from source. You may also want to use [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) for easier build process.

### Installation
To build from source, you will need to do the following steps.

#### Building using IntelliJ
1. Open the folder as IntelliJ IDEA project.
2. Go to `Build` then select `Build Module 'wsp'`.
3. Go to `Build` again then select `Build artifacts...`. Choose `Build`.
4. Wait until the building process is finished.

#### Building using Java CLI
1. Move to `src` directory
```
cd src
```
2. Compile all source files using `javac`.
```
javac -cp . id/ac/itb/stei/informatika/wsp/*.java -d ../bin
```
3. Move to the newly created `bin` directory
```
cd ../bin
```
4. Build the artifact using `jar`.
```
jar cmvf ../src/META-INF/MANIFEST.mf wsp.jar .
```

#### Building using other build tools
You may also want to consider to build with another tools like [Ant](https://ant.apache.org/), [Gradle](https://gradle.org/), or [Maven](https://maven.apache.org/) for easier building process.

## Usage

To solve a word search puzzle, create a new text file for the input in the following format.
```
X T H I S X
X X I S X X
X T H E X X
P U Z Z L E

THESE
ARE
WORDS
TO
SEARCH
```
Then run either the java archive
```
java -jar bin/wsp.jar [input-file-path [--optimize]]
```
or the compiled class file straight away.
```
cd bin
java id.ac.itb.stei.informatika.wsp.Main [input-file-path [--optimize]]
```
However, make sure that you are in the `bin` directory if you
want to run using the compiled class files. Also note that the
`[input-file-path]` will be relative to the `bin` directory as
well. You can use `--optimize` option to optimize searching process using heuristic technique.

For example, you can run the program and pass the path to the input
file as an argument
```
java -jar bin/wsp.jar test/wsp-large-1.txt
```
or
```
java -jar bin/wsp.jar
```
and specify the input file path inside the program instead.
