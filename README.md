# Music Quiz App Server

This repository contains the backend code for the Music Quiz App. The app is built using Java Spring and provides API endpoints for managing game sessions, user profiles, high scores, and song information.

## Table of Contents

- [Installation](#installation)
- [Endpoints](#endpoints)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Installation

1. Clone the repository:

   ```sh
   git clone https://github.com/The-A-Listers/music-quiz-app-server.git
   cd music-quiz-app-server
   ```

2. Install dependencies using Maven:

   ```sh
   mvn clean install
   ```

3. Run the application:

   ```sh
   mvn spring-boot:run
   ```

The application will start and be accessible at `http://localhost:8080`.

## Endpoints

### 1. Game Controller

#### GET `/game`

- **Parameters**:
    - `numberOfSongs` (integer, required): Number of songs for the game session.

- **Response**: Returns JSON object containing song names, URLs, and artist names for the game session.

#### POST `/game`

- **Request Body**:
    - `userId` (string, required): Unique identifier for the user.
    - `songName` (array of strings, required): Array containing correct song names in the order presented during the game.
    - `userTimeTaken` (integer, required): Time taken by the user to complete the game session.

- **Response**: Returns the updated game result with user position, correct song names, URLs, artist names, and user score.

### 2. High Score Controller

#### GET `/highscores`

- **Parameters**:
    - `limit` (integer, required): Number of top high scores to fetch.

- **Response**: Returns a list of high scores in descending order.

#### GET `/highscores/user`

- **Parameters**:
    - `userId` (string, required): Unique identifier for the user.
    - `limit` (integer, required): Number of top user scores to fetch.

- **Response**: Returns a list of top user scores with positions.

### 3. User Profile Controller

#### GET `/userprofile`

- **Parameters**:
    - `userId` (string, required): Unique identifier for the user.

- **Response**: Returns user profile information.

#### POST `/userprofile`

- **Request Body**:
    - `userId` (string, required): Unique identifier for the user.
    - `userName` (string, optional): User's display name.

- **Response**: Returns the created or existing user profile information.

### 4. Root Controller

#### GET `/`

- **Response**: Returns a welcome message indicating the API's purpose.

## Usage

- Use the provided endpoints to interact with the Music Quiz App backend.
- Ensure proper error handling and validation in your requests.
- Refer to the [API documentation](#endpoints) for detailed information on request and response formats.

## Contributing

1. Fork the repository on GitHub.
2. Clone your forked repository (`git clone https://github.com/The-A-Listers/music-quiz-app-server.git`).
3. Create a new branch for your feature (`git checkout -b feature-name`).
4. Commit your changes and push to the branch (`git commit -am 'Add new feature'`).
5. Create a new pull request and describe your changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```
