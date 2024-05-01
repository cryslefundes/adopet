CREATE TABLE pets (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    breed VARCHAR(100) NOT NULL,
    age INTEGER,
    adopted BOOLEAN NOT NULL,
    weight DECIMAL(4,2),
    pet_type VARCHAR(20) NOT NULL,
    shelter_id UUID NOT NULL REFERENCES shelters(id)
);