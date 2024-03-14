# SMIT take home assignment (Library)

## Usage

### Running the Backend

1. Set up the database using Docker Compose:

    ```bash
    cd backend
    docker-compose up -d
    ```

2. Navigate to the `backend` directory: `cd backend`
3. Build the Micronaut application:

    ```bash
    ./gradlew build
    ```

4. Start the Micronaut application:

    ```bash
    ./gradlew run
    ```


### Running the Frontend

1. Navigate to the `frontend` directory: `cd frontend`
2. Run the frontend application:

    ```bash
    npm install
    npm run serve
    ```

   The frontend application will be accessible at `http://localhost:5713`.
