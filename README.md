# GitHub Repo Fetcher

A simple Spring Boot application that exposes a REST API for fetching **non-fork GitHub repositories** for a given user.  
For each repository, the API returns all branches along with the latest commit SHA.

## Features

- Lists **only non-fork** repositories of a given GitHub user.
- Includes:
  - Repository name
  - Owner login
  - Branches: name and last commit SHA
- Returns a structured `404` JSON response if the GitHub user does not exist.
- Uses GitHub REST API v3 as the backing API.
- Includes a single **integration test** covering both:
  - the **happy path**
  - the **404 Not Found** scenario

## Requirements

- Java 21
- Spring Boot 3.5
- Maven

## Run Locally

To run the application, you need to provide the following system properties:

```bash
-DgithubToken=gh_<your_token>
-DappPort=<port_number>
```

Example:

```bash
mvn clean package
java -DgithubToken=<your_token> -DappPort=<your_port> -jar target/gh-repo-fetcher-1.0.jar
```

> ⚠️ `githubToken` is required to authenticate with GitHub API.  
> ✅ Port is configurable via `-DappPort`, but not required in tests.

## API Endpoint

### `GET /repositories/{username}`

Fetches all **non-fork** repositories of the user with the given username.

#### Success Response (200 OK)

```json
[
  {
    "repositoryName": "my-repo",
    "ownerLogin": "someuser",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "abc123..."
      },
      {
        "name": "dev",
        "lastCommitSha": "def456..."
      }
    ]
  },
  ...
]
```

#### Error Response (404 Not Found)

If the GitHub user does not exist:

```json
{
  "status": 404,
  "message": "GitHub user not found"
}
```

## Testing

The project includes a single **integration test** that:

- Verifies the **happy path**, including repository and branch structure
- Checks the **404 response** for a non-existent GitHub user
- Avoids mocks or uses them minimally, following task requirements
