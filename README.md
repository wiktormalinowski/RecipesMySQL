# RecipesMySQL

## Quick Start

start an application using `docker-compose up -d` command

**Usage example:**

*Without authentication*


GET `/api/recipe/{id}` Finds recipe by ID

POST `api/register` creates user


*Authentication required*


POST `api/recipe/new` - add new recipe
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

