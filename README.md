# SnakeGame

Name: Haojun Luo
Quest ID: hjluo
Student ID: 20528243

CS349 - Assignment 1
Snake

file: SnakeGame.java, README.txt, makefile
JRE System Library: jdk 1.8.0_91

How to run (commands):
"make" to compile files.
"make run" to run with default value.
"java SnakeGame" to run with default value.
"java SnakeGame $1 $2" to run with {$1} framerate and {$2} speed level.


Key Control:
Arrow key  --  control the movement of the snake 
Space      --  pause/unpause game
R/r        --  restart the game


Introduce:
This game is controlling the snake, and eat as many food as you can.
In each level, the food contain different score. This means higher the
level more score you can get. There are 10 levels in this game, once the 
snake has eaten 10 food, the level will grow.
So, GOOD LUCK and HAVE FUN!

Block:
1. Blue   --  Snake
2. Red    --  Food
3. Black  --  Powerup(Shorten)


Enhancement:
1. sound effect for eating a fodd and ending the game.
2. once the snake has eaten 10 food, the level is beaten and grow the level.
3. contain a power-up, which can shorten length of the snake
