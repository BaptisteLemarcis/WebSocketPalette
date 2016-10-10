WebSocket Palette: A WebSocket Plugin for BeepBeep 3 software
=============================================================

[![Build Status](https://travis-ci.com/BaptisteLemarcis/WebSocketPalette.svg?token=rmMSz5HLBt2FasGy3PnZ&branch=master)](https://travis-ci.com/BaptisteLemarcis/WebSocketPalette)

Repository structure                                           {#structure}
--------------------

The repository is separated across the following folders.

- `src`: main source files
- `release`: Official output release zip files

Compiling the project contained in the present repository generates the
file `WebSocketPalette.jar`, which is the minimal file you need to implement our palette to BeepBeep on
your system.

Compiling and Installing BeepBeepExplore                              {#install}
-----------------------------------
First this project have some dependencies:

- The Java Development Kit (JDK) to compile. This project was developped under the version 8.
- [Ant](http://ant.apache.org) to automate the compilation and build process

Download the sources for BeepBeepExplore from
[GitHub](https://github.com/BaptisteLemarcis/WebSocketPalette) or clone the
repository using Git:

    git clone https://github.com/BaptisteLemarcis/WebSocketPalette

### Compiling

Compile the sources by simply typing:

    ant

This will produce a file called `WebSocketPalette.jar` binary in the folder. This file
is runnable and stand-alone including BeepBeep 3.