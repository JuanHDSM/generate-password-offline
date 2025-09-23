# Generate Password Offline Application

## Requirements

- Java installed
  

## Clone repository

- run follow command on terminal:
```
git clone https://github.com/JuanHDSM/generate-password-offline.git
```

- go to directory:
```
cd generate-password-offline/src
```

## Compile Class

- run the follow command

```
javac Main.java
```

## Generate jar file

-  run the follow command:

```
jar cfm Main.jar Manifest.txt Main.class
```

## Run jar file

```
java -jar Main.jar <size-of-passoword>
```
