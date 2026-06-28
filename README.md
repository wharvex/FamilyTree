# Hello

## Steps to run locally

Download and install PostgreSQL.

Add the "C:\Program Files\PostgreSQL\18\bin" folder (or the equivalent for your installation) to your PATH.

Run the following commands in powershell:

```
psql -U postgres -c "CREATE USER familytree WITH PASSWORD 'familytree';"
psql -U postgres -c "CREATE DATABASE familytree OWNER familytree;"
```

Clone this Git repo to your local machine.

Build and deploy locally by running the following command in the repo root in powershell:

```
./gradlew quarkusDev
```

Then visit http://localhost:8080/hello to view the app.
