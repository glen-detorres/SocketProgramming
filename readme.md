## Server-Client Exercise

## Pre-requeisites
1. Install jdk 8 or higher.
2. Maven

## Setup steps
1. Clone this repo `git clone git@github.com:glen-detorres/SocketProgramming.git`
2. Open project in your IDE.

## Steps to run
- Run `mvn clean install`.

- Run the `server` first before running the `client`.

1. Open terminal and cd to server directory
2. To run server, open terminal then run `mvn exec:java -Dexec.mainClass="com.example.Server"`

- After the `server is running`, run the client.

1. Open another terminal and cd to client directory
2. then run `mvn exec:java -Dexec.mainClass="com.example.Client"`