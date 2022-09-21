INSERT INTO users (id, email, password, role) VALUES (1, 'homelander@gmail.com', '$2a$12$hOj3lQmDg75P0d6OVtjNaOVH.Xi.Dls6CKHh7mjk8543oaEfGK6bW', 'ADMIN');
INSERT INTO users (id, email, password, role) VALUES (2, 'starlight@gmail.com', '$2a$12$dXqPuA.SDbPxIHr25nkmGeRVBYTrjkCPsNVjNSCzBVE2m.lHtpg9C', 'USER');
INSERT INTO users (id, email, password, role) VALUES (3, 'atrain22@gmail.com', '$2a$12$wYAIHoSw3wowl1i.EssOnu/SgXyN1PPRqwuLvjWxh8FZXuUBmsP2e', 'USER');
INSERT INTO users (id, email, password, role) VALUES (4, 'butcher4@gmail.com', '$2a$12$2ALzPY7c5ACn59xZlJ6xzeEqrOdul7XU0BxUMB0O/PqhxBGVpkAX6', 'USER');
INSERT INTO users (id, email, password, role) VALUES (5, 'hughie55@gmail.com', '$2a$12$pqy/7y8gBBGKViU5m1m52.9tzfKRmoNRTJ9cLW3h8sqGV2Ju0Lr72', 'USER');
INSERT INTO users (id, email, password, role) VALUES (6, 'gusfring@gmail.com', '$2a$12$QbRl63hLY0XYUiL72cHxLOjnR2LbryjFZS5P2EqqJyipOeYhRct7C', 'USER');
INSERT INTO users (id, email, password, role) VALUES (7, 'thedeep7@gmail.com', '$2a$12$JS8H94dOi98K7.7en0p7j.qlEQLV1v43oN0T6ZZeJUxfQk9Re5PMa', 'USER');
INSERT INTO users (id, email, password, role) VALUES (8, 'queenmaeve@gmail.com', '$2a$12$ELrQvChqwE.Dj4m42alLlOyBCHtDw55qozxnoF0pwHBl/OuPAfisS', 'USER');
INSERT INTO users (id, email, password, role) VALUES (9, 'kimiko99@gmail.com', '$2a$12$t5VkGvizZedJj//s0fTx1unHhsDV8540H57Fq0EEw2IWgxhurkU3.', 'USER');
INSERT INTO users (id, email, password, role) VALUES (10, 'soldierboy@gmail.com', '$2a$12$ZgULszUpjLB2CAXNXOgDGu1GEvQSRl1oI.M3mvlgbD/FEHowXkDo6', 'ADMIN');

INSERT INTO recipes (id, category, date, description, name, user_id) VALUES (1, 'pasta', '2019-03-21T03:47:08.644', 'A meat-based sauce in Italian cuisine', 'bolognese', 10);
INSERT INTO directions(id, direction) VALUES (1, "take a pan");
INSERT INTO directions(id, direction) VALUES (1, "turn on the heat");
INSERT INTO directions(id, direction) VALUES (1, "cook");
INSERT INTO directions(id, direction) VALUES (1, "done");
INSERT INTO ingredients(id, ingredient) VALUES (1, "spaghetti");
INSERT INTO ingredients(id, ingredient) VALUES (1, "beef");
INSERT INTO ingredients(id, ingredient) VALUES (1, "tomato sauce");
INSERT INTO ingredients(id, ingredient) VALUES (1, "olive oil");

INSERT INTO recipes (id, category, date, description, name, user_id) VALUES (2, 'breakfast', '2021-06-29T19:47:08.644', 'a dish made from eggs (usually chicken eggs) stirred while being gently heated', 'scrambled eggs', 1);
INSERT INTO directions(id, direction) VALUES (2, "heat up the pan and melt butter");
INSERT INTO directions(id, direction) VALUES (2, "break the eggs in the pan");
INSERT INTO directions(id, direction) VALUES (2, "stir");
INSERT INTO ingredients(id, ingredient) VALUES (2, "eggs");
INSERT INTO ingredients(id, ingredient) VALUES (2, "butter");

INSERT INTO recipes (id, category, date, description, name, user_id) VALUES (3, 'dinner', '2020-04-14T16:47:08.644', 'Sausages in rich-tomato sauce - an American-Italian special', 'sausages', 10);
INSERT INTO directions(id, direction) VALUES (3, "take the pot and heat it up");
INSERT INTO directions(id, direction) VALUES (3, "put onion and garlic inside");
INSERT INTO directions(id, direction) VALUES (3, "after 2 minuts add sausages");
INSERT INTO directions(id, direction) VALUES (3, "pour in tomato souce and boil for 20 minutes");
INSERT INTO ingredients(id, ingredient) VALUES (3, "sausages");
INSERT INTO ingredients(id, ingredient) VALUES (3, "tomato sauce");
INSERT INTO ingredients(id, ingredient) VALUES (3, "garlic");
INSERT INTO ingredients(id, ingredient) VALUES (3, "onion");

INSERT INTO recipes (id, category, date, description, name, user_id) VALUES (4, 'beverage', '2021-11-05T09:47:08.644', 'A sweetened lemon-flavored beverage', 'lemonade', 5);
INSERT INTO directions(id, direction) VALUES (4, "put some ice into glass");
INSERT INTO directions(id, direction) VALUES (4, "combine sugar with water and pour into glass");
INSERT INTO directions(id, direction) VALUES (4, "squeeze a lemon and stir");
INSERT INTO ingredients(id, ingredient) VALUES (4, "water");
INSERT INTO ingredients(id, ingredient) VALUES (4, "ice");
INSERT INTO ingredients(id, ingredient) VALUES (4, "sugar");
INSERT INTO ingredients(id, ingredient) VALUES (4, "lemons");

INSERT INTO users_favourite_recipes(user_id, favourite_recipes_id) values (10, 1);
INSERT INTO users_favourite_recipes(user_id, favourite_recipes_id) values (10, 3);
INSERT INTO users_favourite_recipes(user_id, favourite_recipes_id) values (10, 4);

INSERT INTO users_favourite_recipes(user_id, favourite_recipes_id) values (1, 2);








