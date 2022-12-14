# Letter Boxed puzzle solver

![GitHub](https://img.shields.io/github/license/gmarmstrong/letter-boxed)
[![Gradle build](https://github.com/gmarmstrong/letter-boxed/actions/workflows/gradle.yml/badge.svg)](https://github.com/gmarmstrong/letter-boxed/actions/workflows/gradle.yml)
[![codecov](https://codecov.io/gh/gmarmstrong/letter-boxed/branch/main/graph/badge.svg)](https://codecov.io/gh/gmarmstrong/letter-boxed)

_letter-boxed_ is a puzzle solver for the New York
Times's game, [_Letter Boxed_](https://www.nytimes.com/puzzles/letter-boxed).

## Usage

_letter-boxed_ is a command-line program. It takes a list of letters
as arguments and treats them as the structure of a Letter Boxed puzzle.
Some examples:

```bash
$ ./gradlew run --quiet --args='RME,WCL,KGT,IPA'
[wigwam, marketplace]
$ ./gradlew run --quiet --args='LWC,GTK,ERM,PIA'
[wigwam, marketplace]
$ ./gradlew run --quiet --args='BGY,ULN,MIS,TOK'
[yolks, stumbling]
$ ./gradlew run --quiet --args='ABC,DEF,GHI,JKL'
$ # No solutions using the words.txt vocabulary, so no output is produced.
$ ./gradlew run --quiet --args='RAP,TZV,INE,OLH'
[helper, revitalization]
[pother, revitalization]
[philter, revitalization]
[telpher, revitalization]
[virilize, Ethiopian]
[heliozoan, nonrepetitive]
[perihelion, novelization]
```
