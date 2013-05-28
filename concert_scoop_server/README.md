Concert Scoop Server
===============
Setup 
-------
1. Rename the config/example.application.yml file to applicaiton.yml  
2. Fill in the blank values with appropriate OAuth information 
3. Run the following commands

bundle install *--Installs ruby gems*

bundle exec rake db:migrate *--sets up the database*

bundle exec rake db:seed *--use the seed data*

rails server *--starts the rails server*
