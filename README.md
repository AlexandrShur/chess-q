# Application which solves 1000x1000 queens problem.
## How to run:
* Run gradle assemble.
* Then run generated jar file.
Note: Please specify proper heap size for large boards.
For 1000 board please use -Xms1024m -Xmx2048m.

**Note:** Default board dimension is 1000.
If you want to specify any other dimension just pass integer argument to the application.

## Arguments list which application accept (arguments order insensitive):
* Any integer value: will be used as board dimension.
* draw: Draws board in a console. Please don't use for large boards))
* without: Doesn't apply start queens positions. In other words search will be performed on empty board.
You can use it to compare execution time.

## Application run example:
java -Xms1024m -Xmx2048m -jar .\queen-1.0-SNAPSHOT.jar draw 10 without