# cloud-novice-to-cloud-native

Demo application for a workshop at the Ada Lovelace Festival 2018 in Berlin, Germany.

## Building
To build the Demo Ktor Application, run

```bash
$ ./gradlew build
```

To build its docker image and tag it as "ada-lovelace-application", run

```bash
$ docker build -t ada-lovelace-application .
```

## Running
To start the docker image, run

```bash
$ docker run -m512M --cpus 2 -it -p 3000:3000 --rm ada-lovelace-application
```

where

- `-m512M` sets the memory limit to 512M
- `--cpus 2` sets the number of CPUs to 2
- `-p 3000:3000` tells docker to publish port 3000 from inside the container as port 3000 on
the machine where it is run
- `-it` instructs docker to allocate a terminal (tty) to pipe the stdout and 
to respond to the interrupt key sequence
`-rm` automatically removes the container when its exits

## Acknowledgments

- The Demo Ktor Application uses Ktor (see http://ktor.io/) and is inspired by a sample application
  published by Ktor at https://github.com/ktorio/ktor-samples
- The application is set up similar to Ben Hall's Java Kotlin Http Example application published at
https://github.com/katacoda/java-kotlin-http-example  
