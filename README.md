Concert Scoop
===========
The concept for of this project was to create a platform that helps consolidate social media content pertaining to a concert. We are only implementing the rudiments of that concept

The RoR app is not really the focus of this project, it is more of a black-box supporting application. It exists to provide centralized storage for concert related information send json data to an android client on request. 

The android app should be able to list all concerts available on the server, all attendees for a given concert, and allow a user to create a new concert on the server.  

### Contributors ###
 * Andrew Showers
 * Benedict Fischer


Requirements
-------------------
There are two components in this repo.  A RoR web app (concert scoop server) and an android app (concert scoop) which interacts with the web app.  

For android development you'll need need the android SDK. 

For RoR development you'll need ruby, ruby gems, and bundler.  Bundler will resolve all the other package dependencies for you.

Authentication
--------------------
We experimented with OAuth to allow users to authenticate themselves using Facebook, Twitter, or Google. Ultimately authentication was sidelined because it wasn't working correctly and it was consuming too much time for a feature that is only tangentially related to our main functionality.
