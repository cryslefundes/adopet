CREATE TYPE ANIMAL AS ENUM('dog', 'cat');

CREATE TABLE pets (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    breed VARCHAR(100) NOT NULL,
    age DECIMAL(4,2) NOT NULL,
    adopted BOOLEAN NOT NULL,
    pet_type ANIMAL NOT NULL,
    shelter_id UUID NOT NULL REFERENCES shelters(id)
);