DROP TABLE players;
DROP TABLE equipments;

CREATE TABLE players (
    player_id SERIAL PRIMARY KEY,
    player_name VARCHAR(100) NOT NULL,
    player_email VARCHAR(150) UNIQUE NOT NULL
);

INSERT INTO players (player_name, player_email) VALUES
('Roger Federer', 'roger@tennis.com'),
('Serena Williams', 'serena@slam.com'),
('Lionel Messi', 'leo@miami.com'),
('Tom Brady', 'tom@tb12.com'),
('Tiger Woods', 'tiger@golf.com'),
('Stephen Curry', 'steph@warriors.com'),
('Naomi Osaka', 'naomi@court.com'),
('Cristiano Ronaldo', 'cr7@alnassr.com'),
('Simone Biles', 'simone@gym.com'),
('Lewis Hamilton', 'lewis@f1.com'),
('LeBron James', 'lebron@lakers.com'),
('Alex Morgan', 'alex@uswnt.com'),
('Max Verstappen', 'max@redbull.com'),
('Mikaela Shiffrin', 'mikaela@ski.com'),
('Shohei Ohtani', 'shohei@dodgers.com');

CREATE TABLE equipments (
    eq_id SERIAL PRIMARY KEY,
    eq_type VARCHAR(100) NOT NULL,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100),
    player_id INTEGER REFERENCES players(player_id) ON DELETE CASCADE
);

INSERT INTO equipments (eq_type, brand, model, player_id) VALUES
('Tennis Racket', 'Wilson', 'Pro Staff 97', 1),
('Tennis Shoes', 'On', 'THE ROGER', 1),
('Tennis Racket', 'Wilson', 'Blade SW102', 2),
('Tennis Bag', 'Wilson', 'Super Tour', 2),
('Football Boots', 'Adidas', 'X Crazyfast', 3),
('Jersey', 'Adidas', 'Inter Miami Home', 3),
('American Football', 'Wilson', 'The Duke', 4),
('Helmet', 'Riddell', 'SpeedFlex', 4),
('Golf Club', 'TaylorMade', 'Stealth 2 Plus', 5),
('Golf Ball', 'Bridgestone', 'Tour B XS', 5),
('Basketball', 'Wilson', 'Official NBA', 6),
('Basketball Shoes', 'Under Armour', 'Curry 11', 6),
('Tennis Racket', 'Yonex', 'Ezone 98', 7),
('Tennis Skirt', 'Nike', 'Court Victory', 7),
('Football Boots', 'Nike', 'Mercurial Superfly', 8),
('Training Vest', 'Nike', 'Strike', 8),
('Gymnastics Grips', 'Reisport', 'Protec', 9),
('Leotard', 'GK Elite', 'Power Performance', 9),
('Racing Helmet', 'Bell', 'HP77', 10),
('Racing Gloves', 'Puma', 'Hyperspeed', 10),
('Tennis Racket', 'Babolat', 'Pure Drive', NULL),
('Football', 'Nike', 'Flight Official', NULL),
('Golf Driver', 'Callaway', 'Paradym Ai Smoke', NULL),
('Basketball Shoes', 'Nike', 'LeBron XXI', NULL),
('Baseball Bat', 'Louisville Slugger', 'Meta BBCOR', NULL);

SELECT * FROM players;
SELECT * FROM equipments;

SELECT
    p.player_name AS Player_Name,
    e.eq_type AS Equipment_Type,
    e.brand AS Brand
FROM players p
FULL JOIN equipments e ON p.player_id = e.player_id
ORDER BY p.player_name;