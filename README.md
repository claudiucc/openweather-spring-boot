Openweather Spring Boot
---

Welcome to the Openweather Spring Boot!

### Introduction

This is a simple application that requests its data from OpenWeather and persists it into H2 database. 
Access current weather data for any city. We collect and process weather data from OpenWeather.

**Prerequisites:**
[Java 11](https://adoptopenjdk.net/),
[Maven](https://maven.apache.org/),
[Git](https://github.com/git-guides/install-git).

* [Getting Started](#getting-started)
* [Help](#help)
* [Links](#links)

## Getting Started

To run this application, run the following commands:

```bash
git clone https://github.com/claudiucc/openweather-spring-boot.git
cd openweather-spring-boot
mvn clean install
```

## Call current weather data for one location

# By city name
You can call by city name. It retrieves the date from OpenWeather and stores the weather data into the H2 database.

# Example
```
/openweather-api/v1/weather?city=Vienna
```

### The task

As the new engineer leading this project, your first task is to make it production-grade, feel free to refactor any piece
necessary to achieve the goal.

### How to deliver the code

Please send an email containing your solution with a link to a public repository.

>**DO NOT create a Pull Request with your solution** 

### Footnote
It's possible to generate the API key going to the [OpenWeather Sign up](https://openweathermap.org/appid) page.
