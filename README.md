# Scripts
Scripts used once in a while

## CopyPaste

This is a small Java utility to copy/paste text using a robot. The motivation to develop this is that sometimes `Ctrl-C` `Ctrl-V` is not available. The robot will read content from the clipboard or a text file and type it on whatever window is focused. Therefore, care must be taken when using it because there is no way (for now) to stop it from typing.

### Usage

```Batchfile
java -jar cp.jar [options]
```

Some examples:

1. Type the content of a text file:
```Batchfile
java –jar cp.jar –datasource "c:/temp/test.txt"
```
2. The robot waits 2 seconds for the user to focus on the window that it will write on. To change the delay to another value (e.g. 10 seconds):
```Batchfile
java –jar cp.jar –delay 10
```
3. The robot waits 1 ms between key strokes. Sometimes the target environment cannot process key strokes so fast and thus we might need to increase this value (e.g. 10 ms):
```Batchfile
java –jar cp.jar –interval 10
```
4. Options can be combined:
```Batchfile
java –jar cp.jar –interval 10 –delay 10 -datasource "c:/temp/teste.txt"
```
5. Finally, to print a (not very useful) quick guide: 
```Batchfile
java –jar cp.jar –help 
```
