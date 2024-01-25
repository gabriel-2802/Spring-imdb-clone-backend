# My IMDB Web App

## Description
* a web app which allows user accounts, admin accounts, personalised home pages, and a movie database with ratings and reviews
* admins can add, edit, and delete movies, as well as delete user accounts
* the home page is available to all users, including quests
* users can add, update or delete reviews for movies
* if they modify their review, the rating of the movie will be updated as well
* users can also add movies to their watchlist
* if the movie is deleted from the database, it will be removed from the watchlist as well


## Technical Details
* ```Spring Boot``` application with ```POSTGRESQL``` database
* ```Spring Security``` and ```JWT``` for user authentication and authorisation
* ```Spring Data JPA``` for database access
* ```Spring MVC``` for RESTful API

## To Be Added
* a search bar for the movie database
* a movie recommendation system
* home page - movie recommendations