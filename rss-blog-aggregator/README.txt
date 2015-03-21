links
jetty local: http://localhost:8080/
tomcat local: http://localhost:8080/rss-blog-aggregator
http://rss-blog-aggregator.herokuapp.com/


deploy: 
on eclipse: maven clean and package

on shell: 
add postgres to heroku:> heroku addons:add heroku-postgresql --app rss-blog-aggregator
heroku login
D:\gitRepository\rss-blog-aggregator\rss-blog-aggregator\target> heroku deploy:war --war rss-blog-aggregator-0.0.1-SNAPSHOT.war --app rss-blog-aggregator

=========================================================================================================
Microsoft Windows [Version 6.3.9600]
(c) 2013 Microsoft Corporation. All rights reserved.

C:\Users\Mauricio>heroku login
Enter your Heroku credentials.
Email: mauricio.ghilardi@gmail.com
Password (typing will be hidden):
Authentication successful.
updating...done. Updated to 3.30.2

C:\Users\Mauricio>d:

D:\>cd D:\gitRepository\rss-blog-aggregator\rss-blog-aggregator\target

D:\gitRepository\rss-blog-aggregator\rss-blog-aggregator\target>heroku deploy:war --war rss-blog-aggregator-0.0.1-SNAPSHOT.war --app rss-blog-aggregator
Uploading rss-blog-aggregator-0.0.1-SNAPSHOT.war....
---> Packaging application...
     - app: rss-blog-aggregator
     - including: webapp-runner.jar
     - including: rss-blog-aggregator-0.0.1-SNAPSHOT.war
     - installing: OpenJDK 1.8
---> Creating slug...
     - file: slug.tgz
     - size: 74MB
---> Uploading slug...
     - stack: cedar-14
     - process types: [web]
---> Releasing...
     - version: 3
---> Done

=========================================================================================================
Generate key to add to run plan named: heroku deploy-war rss-blog-aggregator
goal: clean heroku:deploy-war
key: HEROKU_API_KEY
D:\gitRepository\rss-blog-aggregator\rss-blog-aggregator\target>heroku auth:token
7314f25d-993f-4563-8b29-0ce8e837739c

D:\gitRepository\rss-blog-aggregator\rss-blog-aggregator\target>heroku plugins:install https://github.com/heroku/heroku-deploy
Installing https://github.com/heroku/heroku-deploy... done

D:\gitRepository\rss-blog-aggregator\rss-blog-aggregator\target>cd..

D:\gitRepository\rss-blog-aggregator\rss-blog-aggregator>heroku addons:add heroku-postgresql --app rss-blog-aggregator
Adding heroku-postgresql on rss-blog-aggregator... done, v6 (free)
Attached as HEROKU_POSTGRESQL_PURPLE_URL
Database has been created and is available
 ! This database is empty. If upgrading, you can transfer
 ! data from another database with pgbackups:restore.
Use `heroku addons:docs heroku-postgresql` to view documentation.

D:\gitRepository\rss-blog-aggregator\rss-blog-aggregator>