# HELLO! this is my gambling simulator  

# HOW DO YOU PLAY THIS??
- in this terminal game you spin a slot machine until you win.  
- 
- that's it.  

if you rage quit because you never won...  
you're a ***loser***

# HOW DO I OPEN THIS??
- open this with java in your terminal using:
- ```bash
  java -jar gambling-simulator.jar
  ```
- inside the directory where the game file is
- **windows users:** i recommend using the windows terminal app for this

# THE SLOT EMOJIS LOOK WEIRD... HELP!!!
- you need... UTF-8 ENCODING!!! here's how to activate it:
- **for windows users:**
  - run this in powershell:
  - ```powershell
    chcp 65001
    ```
  - then run the game
- **for linux/mac users:**
  - run this in your terminal:
  - ```bash
    export LANG=en_US.UTF-8
    ```
  - then run the game
- if nothing works, you may need to force java to use utf-8 encoding, do it like this:
  - ```bash
    java -Dfile.encoding=UTF-8 -jar gambling-simulator.jar
    ```
  - this should work for any OS

i used the **jansi** library to add COLOR!!!!!!!!  

(do NOT take this game seriously, please)