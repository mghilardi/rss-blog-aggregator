links
jetty local: http://localhost:8080/
tomcat local: http://localhost:8080/rss-blog-aggregator
http://blog-aggregator-rss.herokuapp.com/

add postgres to heroku:> heroku addons:add heroku-postgresql --app blog-aggregator-rss

deploy: 
on eclipse: maven clean and package
on shell: d:sts-bundle\sts-3.6.3.SR1\workspace\rss-blog-aggregator\target> heroku deploy:war --war rss-blog-aggregator-0.0.1-SNAPSHOT.war --app blog-aggregator-rss
