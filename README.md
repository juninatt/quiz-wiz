# Quiz Wiz

## About the Application
**Quiz Wiz** is an interactive web-based quiz application that enables users to create, play, and share quizzes. Designed for user-friendliness and accessibility, it offers a variety of features for both quiz creators and players.

### Features
- **Quiz Creation**: Users can create quizzes with points and time limits, and save them for others to play.
- **Quiz Playing**: Play quizzes created by others directly on the website.
- **Print and Play**: Option to download or print quizzes for offline play.
- **Leaderboard**: Displays quiz results with user aliases for competitive play.
- **User Interface**: Centered around a menu containing:
    - **Welcome Page**: Current information, updates, and tips.
    - **Create Quiz Page**: For creating and saving quizzes.
    - **Quiz Selection Page**: Lists all available quizzes for playing.
    - **Leaderboard Page**: Shows scores from quizzes played online.

### Entity-Relationship Overview

**Quiz Wiz** utilizes several entities to structure and manage quizzes:

- **Quiz**: Represents a quiz with a topic. It contains multiple questions and is associated with leaderboard entries.
  - Relationships:
    - Contains multiple `Question`s.
    - Linked to several `Leaderboard` entries.

- **Question**: Each question within a quiz, including its text, time limits, points, and answer options.
  - Relationships:
    - Part of a `Quiz`.
    - Includes multiple `AnswerOption`s.

- **Leaderboard**: Records results for quiz games, including player scores and performance metrics.
  - Relationships:
    - Associated with a specific `Quiz`.

- **AnswerOption**: The possible answers for a question, indicating whether an answer is correct.
  - Relationships:
    - Attached to a `Question`.

This structure underpins the quiz creation, playing, and scoring functionalities of the application.

### Technical Structure
- **Backend**: Developed in Java.
- **Frontend**: Uses HTML, CSS, and JavaScript.
- **Database**: SQLite, with a separate database for testing to closely mimic the production environment.


### Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

#### Prerequisites
What things you need to install the software and how to install them:
- Java 17
- Maven

#### Installation
A step-by-step series of examples that tell you how to get a development environment running:
1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Use 'mvn install' to install the dependencies:
4. Enter 'java -jar path/to/quiz-wiz.jar' to start the application:


### Built With
- [Spring Boot](https://spring.io/projects/spring-boot) - The framework used for developing the application
- [Thymeleaf](https://www.thymeleaf.org/) - For server-side rendering of HTML templates
- [Hibernate](https://hibernate.org/) - For object-relational mapping and data access
- [SQLite](https://www.sqlite.org/index.html) - As the database for storing quiz data
- [MapStruct](https://mapstruct.org/) - For simplifying the mapping between Java bean types


### Author
- **Petter Bergström** - [GitHub](https://github.com/juninatt)




