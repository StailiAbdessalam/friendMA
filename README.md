

[![MIT License](https://img.shields.io/apm/l/atomic-design-ui.svg?)](https://github.com/tterb/atomic-design-ui/blob/master/LICENSEs) ![GitHub last commit](https://img.shields.io/github/last-commit/devnart/friendMA)

# Friend.ma

Friend.ma is a web app that help you find roommates fast and easy it’s free to use and with a simple UI so you don’t get distracted, it gives you the ability to check possible roommates by their profile and get the necessary information (city, budget, availability …) without the need to contact them and a lot of other features

This is the server-side repository for the client-side check : [Friend.ma Front-end](https://github.com/devnart/friend.ma-)



## Notice
This app is not completed yet (see todos below) contributions are very welcome!
## Features

- Built using multi-layer architect
- Authentication/Authorization using Firebase
- implemented DTO
- Clean code
## Tech Stack

**Client:** Angular 13, Firebase, TailwindCSS - [Front-end Repository](https://github.com/devnart/friend.ma-)

**Server:** Java, Spring Boot, Firebase


## Installation

Navigate to the root of the project. For building the project using command line, run below command :



```bash
  mvn clean install
```
    
## API Reference
Full APIs refrence available at : \
http://localhost:8080/swagger-ui/index.html
#### Get all products (offers + demands)

```http
  GET /api/products
```

| Parameter | Type     | Description                | Default value |
| :-------- | :------- | :------------------------- | :---- |
| `page` | `string` | Page number to fetch | 0
| `size` | `string` | size of the page | 10

#### Get offers

```http
  GET /api/offer/all
```

| Parameter | Type     | Description                | Default value |
| :-------- | :------- | :------------------------- | :---- |
| `page` | `string` | Page number to fetch | 0
| `size` | `string` | size of the page | 10

#### Get demands

```http
  GET /api/demand/all
```

| Parameter | Type     | Description                | Default value |
| :-------- | :------- | :------------------------- | :---- |
| `page` | `string` | Page number to fetch | 0
| `size` | `string` | size of the page | 10


## Support

For support, email the.staili.abdessalam@gmail.com

