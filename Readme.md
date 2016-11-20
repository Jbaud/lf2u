Jules Baud - lf2u


1 )Configuration instructions

    1.1) Install JAVA


  
                  $ sudo add-apt-repository ppa:webupd8team/java
                  $ sudo apt-get update
                  $ sudo apt-get install oracle-java8-installer
                  
                Verify Java version:

                  
                  $ java -version
                  
                3. Configure Java environment


                  $ sudo apt-get install oracle-java8-set-default



    1.2) Install Tomcat
    
                 $ cd ~/Downloads
                  $ wget http://mirrors.gigenet.com/apache/tomcat/tomcat-8/v8.0.33/bin/apache-tomcat-8.0.33.zip
                  $ unzip apache-tomcat-8.0.33.zip
                  $ sudo mv apache-tomcat-8.0.33 /opt/tomcat
                  $ cd /opt/tomcat/bin
                  $ chmod 744 *sh
                  $ ./startup.sh
                    

                Verify that tomcat has started by pointing your browser at http://localhost:8080 ... you should see the Tomcat banner page. Now shutdown the server:


                 $ ./shutdown.sh


     1.3) Install maven

            run 
            $ sudo apt-get install maven
                   
              
2) Build and deploy instructions

            The  project delivered is a maven project

            to build the .war file please type in the root of the project

            $ mvn package

            you will then find the .war file under

            $ cd target/

            under the name lf2u.war


            Then deploy the war file on the tomcat server


            Test will be run with the package command but you may also run

            $ mvn test

            To see the project code  you can of course import the lf2u as a maven project inside an IDE



3) Copyright and licensing instructions

    The sofware is published under MIT licence

    The MIT License (MIT)
    Copyright (c) <2016> <cJules Baud>

    Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


4) Known bugs



5) Credits and acknowledgements


    I would like to thank you for the example Lamp Rest  code provided.
    I would also like to thanks the numerous people provinding help on stackOverflow.com

