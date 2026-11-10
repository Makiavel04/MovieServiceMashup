MovieServiceMashup
==================

A distributed movie service that combines data from TMDB and a Thrift
service, exposed through a REST API and a command-line client.

Authors
-------

Killian Darras, Mathéo Rebray and Tristan Cottron.
Project created for the Distributed Architecture course.


Server
------

1. Create a "tmdb-conf.properties" file.

   Copy "tmdb-exemple.properties" and add your own API key. The file must
   contain all of these entries:

       API_KEY=YOUR_KEY
       API_BASE_URL=https://api.themoviedb.org/3/
       IMAGE_BASE_URL=https://image.tmdb.org/t/p/w500
       model=moviemashup.tmdb.TMDbClientImpl

   Do not commit your API key.

2. Compile the Thrift classes, from the root of the project:

       ./gradlew compileThrift

3. Launch the server:

       ./gradlew farmRun

Note: the "webapp/" directory of the :virtual project should already exist,
with an empty file so that it can be tracked by Git. If it doesn't, create
it and leave it empty.


Client
------

1. Build the all-in-one client:

       ./gradlew :client:shadowJar

2. Run it:

       java -jar /path/to/client-all.jar


Usage
-----

    java -jar client.jar [options]

     -a,--add <title> <year> <watch_date> <grade>   Add a film
     -f,--find <title>                              Search a film
     -h,--help                                      Show help
