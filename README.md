# Solomon Blake

I have a Bachelor of Engineering in Computer Engineering from Ryerson University! Roughly halfway through my degree, I pivoted and completed a Bachelor of Science in Physics from Trent University in Peterborough, Ontario before returning to Engineering. I've had a love for Physics, Cosmology in particular, which is why I decided to pursue it. I have a passion for the bigger picture, which is where my desire to learn more about Data Engineering stems from. Being able to manipulate and organize data excites me, as I see an ever-increasing range of applications for these techniques and technologies. Having joined Jarvis has exposed me to the greater world of Data and I look forward to getting opportunities to delve deeper. Outside of data and tech, I'm fascinated by Philosophy, History, Sociology, reading Fiction novels, and I enjoy socializing with friends and finding tasty foods to try!

## Skills

**Proficient:** Java, Bash, SQL, Agile/Scrum, C, CentOS 7, RESTful APIs, Google Cloud, Slack

**Competent:** Hadoop, Maven, MATLAB

**Familiar:** Scala, Python, SystemC

## Development Projects

Project source code: https://github.com/jarviscanada/jarvis_data_eng_solomon

- **Linux & SQL**: Implemented a system resource monitor that could be used to monitor a cluster of machines using several bash scripts. The first script was used to record the hardware specification of each machine, the second script read the system resources of each machine such as current memory usage, clock frequency, and recorded the results in a PostgreSQL database as scheduled by a crontab job. The database itself was provisioned as a Docker container, which each script interacted with in order to store the relevant data in two separate tables.

  

- **Core Java Apps**: Imlemented three projects in order to solidify my Java foundation. I recreated the functionality of the bash command `egrep -r` using Java 8 Stream API to search through a file and print regular expression (RegEx) matches into a text file. I became familiar with Java database connectivity (JDBC) through the creation of various data access objects (DAOs) and their associated tables, as well as by using JDBCTemplate to execute and display PostgreSQL queries. Finally, I created a command-line application that used Twitter's REST API to post, read and delete tweets. This application made use of Spring for dependency injection and Maven for project management.

  

- **SpringBoot App**: Developed a trading microservice with Springboot that could access real-time market data from IEX Cloud. The application used Swagger as its front end, where the user must able to create new trading accounts, find real-time stock quotes, view your personal stock positions, as well as buy and sell stocks.

  

- **Hadoop:** Provided the 2016 World Development Indicator dataset through Google Cloud BigQuery, I stored the data using the Hadoop distributed file system on a three machine cluster, provisioned through Google Cloud Dataproc. I then used Apache Hive to parse through the data to answer particularly relevant questions. Once found, these answers were displayed in an Apache Zeppelin notebook with markdown descriptions.

  

- **Spark/Scala**: *(In progress)* Currently I'm in the process of learning and forming my foundational knowledge of Scala in preparation for my Spark project. Building on my Hadoop project experience, I will be using Apache Spark to perform queries using Spark SQL in order to answer relevant business questions while working with resilient distributed datasets (RDDs) and DataFrames (DFs).

  

- **Cloud & DevOps:** Not started

## Professional Experiences

**Data Engineer Trainee, Jarvis, Toronto (2019-Present):** Developed several projects in an agile environment involving technologies such as Java, Git, Springboot, Hadoop, and other well-known industry Big Data tools lead by industry experts.

## Education & Academic Projects

**Ryerson University (2010-2014, 2017-2019)**, Bachelor of Engineering, Computer Engineering

- **Content-based Image Retrieval**: Implemented and calculated multiple image analysis algorithms and metrics in order to search a collection of images and return them in order of similarity to the target image. Developed in MATLAB, making use of its built-in histogram functionality and easy graphing methods.

- **Keil Embedded Digital Media Centre**: Designed and implemented a multi-threaded media centre with an interactive menu, a photo gallery, a simple joy-stick controlled game, and USB audio streaming via a connected PC. Developed in C/C++ within the Keil IDE, which allowed for the monitoring of threads and debugging at the assembly level.

  

**Trent University (2014-2017)**, Honours Bachelor of Science, Physics

- **Predicting Galactic Close-encounters for Galaxies with Spherical Mass Distributions**: Created a galactic merger simulation in Python which applied an extended equation for Newtonian gravity using galaxy information obtained from the Sloan Digital Sky Survey (SDSS). Applied tools from the science library SciPy to simulate galaxy motion, and visualized galaxy mergers using VPython.
- **Arduino stepper motor controlled Inverted Pendulum**: Implemented a rudimentary control system in C, which allowed a stepper motor to move a cart on an air track in response to the motion of an inverted pendulum as measured by an optical encoder. Documented my research and implementation process so that those who continue the project have a solid foundation on which to build.

## Certificates & Awards & Activities

- Ryerson Student Group, Vice President of Finance, 2018-2019

- Ontario Public Interest Research Group, Working Group Leader, 2014-2016