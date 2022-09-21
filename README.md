# RecipesMySQL

## Quick Start
use these commands to prepare a build:
```
./gradlew assemble
docker build . -t myimage:0.1.0
```

start an application using `docker-compose up -d` command, it runs on port `8080`

## Usage example:

*Without authorization*


GET `/api/recipe/{id}` Finds a recipe by ID

POST `/api/register` creates new user

example body:
```
{
   "email": "recipes@somewhere.com",
   "password": "qwerty12"
}
```

*Authorization required*


POST `api/recipe/new` - adds a new recipe

example body: 
```

{
   "name": "Bolognese",
   "category": "pasta",
   "description": "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.",
   "ingredients": ["Aliquam", "tincidunt", "Vestibulum auctor dapibus "],
   "directions": ["Nunc dignissim", "Prius", "Id mentus", "Cras ornare tristique elit", "Vivamus vestibulum ntulla nec ante."]
}
```
PUT `api/recipe/{id}` updates an existing recipe

example body:
```
{
   "name": "Bolognese",
   "category": "pasta",
   "description": "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.",
   "ingredients": ["E", "X", "A", "M", "P", "L", "E"],
   "directions": ["T", "E", "S", "T"]
}
```

DELETE `/api/recipe/{id}` deletes a recipe

