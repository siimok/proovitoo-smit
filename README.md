# SMIT take home assignment (Library)

Purpose was to create a simple library with following requriements.

Loo väike rakendus, kus on raamatukogu funktsionaalsus (lisamine, kataloog, laenutus ja tagastamine). Rakendus koosneb kahest osast – UI (Vue) ja backend Micronaut(Groovy). Backend peab pakkuma andmeid JSON formaadis üle REST endpointide. UI võib asuda ka serveri komponendi sees, aga peab kasutama andmete kuvamiseks ainult vastavaid REST teenuseid. REST teenused peavad olema kaetud integratsioonitestidega ning omama healthchecki.
Lahendus peab kasutama andmebaasi Postgresql.

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

   The frontend application will be accessible at `http://localhost:8080`.