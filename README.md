# RecipesMySQL

## Quick Start

start an application using `docker-compose up -d` command, it runs on port `8080`

**Usage example:**

*Without authentication*


GET `/api/recipe/{id}` Finds a recipe by ID

POST `/api/register` creates new user


*Authentication required*


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
   "ingredients": ["X", "Y", "Z", "A"],
   "directions": ["Nunc dignissim", "Prius", "Id mentus", "Cras ornare tristique elit", "Vivamus vestibulum ntulla nec ante."]
}
```

DELETE `/api/recipe/{id}` deletes a recipe

