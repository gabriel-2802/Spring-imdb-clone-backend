# My IMDB Web App

## Description
* a web app which allows user accounts, admin accounts, personalised home pages, and a movie database with ratings and reviews
* admins can add, edit, and delete movies, as well as delete user accounts
* the home page is available to all users, including guests
* users can add, update or delete reviews for movies
* if they modify their review, the rating of the movie will be updated as well
* users can also add movies to their watchlist
* if the movie is deleted from the database, it will be removed from the watchlist as well
* basic search functionality is available for all users, including quests


## Technical Details
* ```Spring Boot``` application with ```POSTGRESQL``` database
* ```Spring Security``` and ```JWT``` for user authentication and authorisation
* ```Spring Data JPA``` for database access
* ```Spring MVC``` for REST API


## API
* ```/imdb/admin/users``` - GET - get all users
* ```/imdb/admin/add-movie``` - POST - add a movie
* ```/imdb/admin/delete-movie/{id}``` - DELETE - delete a movie
* ```/imdb/admin/update-movie/{id}``` - PUT - update a movie
* ```/imdb/admin/add-movies``` - POST - add multiple movies
* ```imdb/auth/register``` - POST - register a new user
* ```imdb/auth/login``` - POST - login a user
* ```imdb/auth/register-admin``` - POST - register a new admin
* ```imdb/home/welcome``` - GET - show recommended movies
* ```imdb/home/production/{id}``` - GET - show movie details
* ```imdb/home/production/{id}/reviews``` - GET - show reviews for a movie
* ```imdb/home/search``` - GET - search for a movie
* ```imdb/users/login-info``` - GET - get user login info
* ```imdb/users/add-review/{id}``` - POST - add a review for a movie
* ```imdb/users/update-review/{id}``` - PUT - update a review for a movie
* ```imdb/users/delete-review/{id}``` - DELETE - delete a review for a movie
* ```imdb/users/reviews``` - GET - get all reviews for a user
* ```imdb/users/add-watchlist/{id}``` - POST - add a movie to the watchlist
* ```imdb/users/remove-from-watchlist/{id}``` - DELETE - delete a movie from the watchlist
* ```imdb/users/watchlist``` - GET - get all movies in the watchlist
* ```imdb/users/home``` - GET - get user home page






