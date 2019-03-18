# Sample App for STEP Technical Interviews

This application is comprised of a Java / Spring Boot API (using a file-based H2 database), and a React front-end (found in /web).

It implements a primitive title register called "LandOnLite". There is a single database table called "title", which records titles. Like 
a real title, these titles give people ownership rights to some land. A title in LandOnLite has a description (e.g. "Lot 1 on Block 1") 
and an owner.

Users can look up a title by its title number to view it, and (as LandOnLite runs on a trust-based model) anyone can change the ownership 
of a title, though only conveyancing lawyers should.

## Running the application

### Running the API

The Java API has no external dependencies. It can be run from inside an IDE such as IntelliJ IDEA, or from the command line with 
`gradlew bootRun`.

### Running the Web Front-end

The React front-end is in the `web` folder of this repo. `cd` into this folder and run `npm install` to install required libraries.

Start the web client using `npm start`. It will automatically pick up any changes that are made while it's running.
