CREATE TYPE ADOPTION_STATUS AS ENUM('DENIED', 'APPROVED', 'WAITING_EVALUATION');

CREATE TABLE adoptions (
    id UUID NOT NULL PRIMARY KEY,
    date_of DATE NOT NULL,
    purpose TEXT,
    justification TEXT,
    status ADOPTION_STATUS NOT NULL,
    tutor_id UUID NOT NULL REFERENCES tutors(id),
    pet_id UUID NOT NULL REFERENCES pets(id)
);